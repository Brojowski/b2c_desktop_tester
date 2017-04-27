import com.example.b2c_core.City;
import com.example.b2c_core.DraftTransferObject;
import com.example.b2c_core.PlaceTransferObject;
import com.example.b2c_core.Routes;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by alex on 4/13/17.
 */
public class App
{
    private static Socket socket = null;
    public static void main(String[] args)
    {
        try
        {
            socket = IO.socket("http://10.1.1.191:8000");
        } catch (URISyntaxException e)
        {
            e.printStackTrace();
        }

        if (socket == null)
        {
            return;
        }

        final RemoteTesterClass remoteTester = new RemoteTesterClass();

        socket.on(Socket.EVENT_CONNECT, objects -> System.out.println("Connected"));

        socket.on(Routes.FromServer.BEGIN_DRAFT, objects ->
        {
            for (Object o : objects)
            {
                ObjectMapper mapper = new ObjectMapper();
                try
                {
                    DraftTransferObject c = mapper.readValue(o.toString(), DraftTransferObject.class);
                    System.out.println(c);
                    remoteTester.draftTiles(c);
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        });

        socket.on(Routes.FromServer.BEGIN_PLACE, objects ->
        {
            for (Object o : objects)
            {
                ObjectMapper mapper = new ObjectMapper();
                try
                {
                    PlaceTransferObject c = mapper.readValue(o.toString(), PlaceTransferObject.class);
                    System.out.println(c);
                    remoteTester.placeTile(c);
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        });

        socket.connect();
        startGUI(socket);
    }

    public static Socket getSocket()
    {
        return socket;
    }

    public static void startGUI(Socket server)
    {
        JFrame gui = new JFrame("Client");
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LayoutManager lm = new GridLayout(2, 1);
        JPanel content = new JPanel(lm);
        final TextField route = new TextField();
        Button send = new Button("Send");
        send.addActionListener(new SendActionListener(server, route));
        content.add(route);
        content.add(send);
        gui.setContentPane(content);
        gui.setVisible(true);
        gui.setSize(300, 300);
    }
}


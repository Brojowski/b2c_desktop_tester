import io.socket.client.IO;
import io.socket.client.Socket;

import java.net.URISyntaxException;

/**
 * Created by alex on 4/13/17.
 */
public class App
{
    public static void main(String[] args)
    {
        Socket socket = null;
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

        socket.on(Socket.EVENT_CONNECT, objects -> System.out.println("Connected"));

        socket.connect();
    }
}

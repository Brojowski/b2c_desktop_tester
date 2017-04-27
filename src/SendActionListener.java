import io.socket.client.Socket;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendActionListener implements ActionListener
{
    private Socket _socket;
    private TextField _route;
    public SendActionListener(Socket socket, TextField t)
    {
        _socket = socket;
        _route = t;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        String route = _route.getText();
        _socket.emit(route);
    }
}

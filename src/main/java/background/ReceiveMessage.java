package background;

import net.Message;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ReceiveMessage extends Thread{

    private Socket socket;
    private ObjectInputStream objectInputStream;
    private DataInputStream dataInputStream;

    public ReceiveMessage(Socket socket) {
        this.socket = socket;
        try {
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true) {
            try {
                String message = dataInputStream.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

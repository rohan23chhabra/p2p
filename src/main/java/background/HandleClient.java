package background;

import file.SharedDirectory;
import json.MyGson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HandleClient extends Thread {
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    public static final Logger LOGGER = Logger.getLogger(HandleClient.class.getName());

    public HandleClient(Socket socket) {
        this.socket = socket;
        try {
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            String message = dataInputStream.readUTF();
            LOGGER.log(Level.INFO, message);
            String dir = MyGson.toJson(SharedDirectory.getDirectory());
            dataOutputStream.writeUTF(dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

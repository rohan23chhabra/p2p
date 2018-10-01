package background;

import core.User;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListenThread extends Thread {

    public static final Logger LOGGER = Logger.getLogger(ListenThread.class.getName());

    private User user;

    public ListenThread(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket socket = user.serverSocket.accept();
                LOGGER.log(Level.INFO, "Communicated.");
                Thread thread = new HandleClient(socket);
                thread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}

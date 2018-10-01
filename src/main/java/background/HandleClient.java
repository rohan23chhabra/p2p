package background;

import java.net.Socket;

public class HandleClient extends Thread {
    private Socket socket;

    public HandleClient(Socket socket) {
        this.socket = socket;
    }


}

package net;

import java.net.Socket;

public class Session {
    private Socket clientSocket;

    public Session(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
}

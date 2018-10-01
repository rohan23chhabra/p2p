package core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class User {
    private List<Socket> socketList;
    private ServerSocket serverSocket;
    private int port;

    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, int port) {
        this(username, password);
        this.port = port;
        socketList = new ArrayList<Socket>();
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean connect(String ipaddress, int port) {
        return false;
    }

    public void listen() {

    }


}

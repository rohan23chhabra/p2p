package core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class User {
    public List<Socket> socketList;
    public ServerSocket serverSocket;
    public int port;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean connect(String ipaddress, int port) {
        return false;
    }

    public void listen() {

    }


}

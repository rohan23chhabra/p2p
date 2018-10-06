package core;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Peer {

    public File directory;
    public Map<InetSocketAddress, Socket> connectedPeers;
    public ServerSocket serverSocket;
    public int port;

    private String username;
    private String password;

    private List<File> remoteDirectories;

    public Peer(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Peer(String username, String password, int port) {
        this(username, password);
        this.port = port;
        this.connectedPeers = new HashMap<>();
        this.remoteDirectories = new ArrayList<>();
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getDirectory() {
        return directory;
    }

    public void setDirectory(File directory) {
        this.directory = directory;
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

    public Map<InetSocketAddress, Socket> getConnectedPeers() {
        return connectedPeers;
    }

    public void setConnectedPeers(
            HashMap<InetSocketAddress, Socket> connectedPeers) {
        this.connectedPeers = connectedPeers;
    }

    public List<File> getRemoteDirectories() {
        return remoteDirectories;
    }
}

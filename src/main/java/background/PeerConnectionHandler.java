package background;

import core.Peer;
import json.MyGson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PeerConnectionHandler extends Thread {
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    public static final Logger LOGGER = Logger.getLogger(
            PeerConnectionHandler.class.getName());

    private Peer peer;

    private InetSocketAddress clientAddress;

    public PeerConnectionHandler(Peer peer, InetSocketAddress clientAddress) {
        this.clientAddress = clientAddress;
        this.peer = peer;
        this.socket = peer.getConnectedPeers().get(clientAddress);
        try {
            this.dataInputStream =
                    new DataInputStream(socket.getInputStream());
            this.dataOutputStream =
                    new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            String message = dataInputStream.readUTF();
            LOGGER.log(Level.INFO, message);
            String dir = MyGson.toJson(peer.getDirectory());
            dataOutputStream.writeUTF(dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

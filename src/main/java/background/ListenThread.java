package background;

import core.Peer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListenThread extends Thread {

    public static final Logger LOGGER =
            Logger.getLogger(ListenThread.class.getName());

    private Peer peer;

    public ListenThread(Peer peer) {
        this.peer = peer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket socket = peer.serverSocket.accept();
                InetSocketAddress inetSocketAddress =
                        new InetSocketAddress(socket.getInetAddress
                                (), socket.getPort());
                peer.getConnectedPeers().put(inetSocketAddress,
                        socket);
                LOGGER.log(Level.INFO, socket.toString());
                LOGGER.log(Level.INFO, "Communicated.");
                Thread thread =
                        new PeerConnectionHandler(peer, inetSocketAddress);
                thread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}

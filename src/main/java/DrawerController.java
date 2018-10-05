import com.jfoenix.controls.JFXButton;
import core.Peer;

public class DrawerController {

    public JFXButton viewSession;
    public JFXButton sharedDirectory;
    public JFXButton changeName;
    public JFXButton logout;

    private Peer peer;

    public Peer getPeer() {
        return peer;
    }

    public void setPeer(Peer peer) {
        this.peer = peer;
    }
}

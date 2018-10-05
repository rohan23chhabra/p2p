import com.jfoenix.controls.JFXButton;
import core.Peer;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

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

    public void viewSessionOnAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        
    }
}

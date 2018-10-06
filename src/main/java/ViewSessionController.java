import com.jfoenix.controls.JFXButton;
import core.Parents;
import core.Peer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.InetSocketAddress;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewSessionController implements Initializable {
    public Label nameLabel;
    public Label portLabel;
    public Label dirLabel;
    public ListView connectedPeersView;
    public JFXButton backButton;
    private ObservableList<InetSocketAddress> observableList;

    private Peer peer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.observableList =
                FXCollections.observableArrayList();
    }

    public Peer getPeer() {
        return peer;
    }

    public void setPeer(Peer peer) {
        this.peer = peer;
    }


    public void setData() {
        nameLabel.setText(peer.getUsername());
        portLabel.setText(peer.getPort() + "");
        dirLabel.setText(peer.getDirectory().getAbsolutePath());
        observableList.addAll(peer.getConnectedPeers().keySet());
        connectedPeersView.setItems(observableList);
    }

    public void backOnAction(ActionEvent actionEvent) {
        Parents.getRootStack().pop();
        Main.getPrimaryStage().getScene().setRoot(Parents
                .getRootStack().peek());
    }
}

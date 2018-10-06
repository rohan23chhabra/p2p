import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import core.Parents;
import core.Peer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class ViewSessionController implements Initializable {
    public Label nameLabel;
    public Label portLabel;
    public Label dirLabel;
    public JFXButton backButton;
    public JFXButton expandButton;
    public JFXListView connectedPeersView;
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


    public void updateData() {
        nameLabel.setText(peer.getUsername());
        portLabel.setText(peer.getPort() + "");
        dirLabel.setText(peer.getDirectory().getAbsolutePath());
        observableList.addAll(peer.getConnectedPeers().keySet());
        //connectedPeersView.setItems(observableList);
    }

    public void backOnAction(ActionEvent actionEvent) {
        Parents.getRootStack().pop();
        Main.getPrimaryStage().getScene().setRoot(Parents
                .getRootStack().peek());
    }

    public void expandOnAction(ActionEvent actionEvent) {
        if (!connectedPeersView.isExpanded()) {
            connectedPeersView.setExpanded(true);
            connectedPeersView.depthProperty().set(1);
            JFXButton button = (JFXButton) actionEvent.getSource();
            button.setText("COLLAPSE");
        } else {
            connectedPeersView.setExpanded(false);
            connectedPeersView.depthProperty().set(0);
            JFXButton button = (JFXButton) actionEvent.getSource();
            button.setText("EXPAND");
        }
    }


    public void populateListView() {
        peer.getConnectedPeers().keySet().forEach(
                inetSocketAddress -> {
                    Socket socket = peer.getConnectedPeers().get
                            (inetSocketAddress);
                    String ip = socket.getInetAddress().getHostName();
                    Label label =
                            new Label("IP : " + ip);
                    label.setStyle("-fx-font-weight: bold;\n" +
                            "    -fx-font-family: Roboto;\n" +
                            "    -fx-font-size: 15px;");
                    label.setGraphic(
                            new ImageView(new Image(getClass()
                                    .getResourceAsStream(
                                            "images/computer" +
                                                    ".jpg"))));
                    connectedPeersView.getItems().add(label);
                });
    }
}

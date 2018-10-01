import background.ListenThread;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import core.Peer;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {

    public JFXButton connectButton;
    public JFXTextField portField;
    public JFXTextField usernameField;
    public JFXPasswordField passwordField;

    private Peer peer;
    public static final Logger LOGGER = Logger.getLogger(Controller.class.getName());

    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        int userPort = Integer.parseInt(portField.getText());
        String username = usernameField.getText();
        String password = passwordField.getText();
        Stage primaryStage = (Stage) connectButton.getParent().getScene().getWindow();
        primaryStage.setTitle("Welcome - " + username);
        Peer peer = new Peer(username, password, userPort);
        this.peer = peer;
        Thread listenThread = new ListenThread(peer);
        listenThread.start();
        LOGGER.log(Level.INFO, "Listening thread started.");
        loadConnectController();
    }

    private void loadConnectController() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("connect.fxml"));
        Parent root = fxmlLoader.load();

        ConnectController connectController = fxmlLoader.getController();
        connectController.setIp("localhost");
        connectController.setPort(portField.getText());
        connectController.setNetworkLabels();

        Stage stage = Main.getPrimaryStage();
        stage.setTitle("Connect to another peer - " + peer.getUsername());
        stage.setScene(new Scene(root, 700, 575));
        stage.show();
    }
}

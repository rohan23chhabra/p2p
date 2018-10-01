import background.ListenThread;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import core.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements Initializable {

    public JFXButton connectButton;
    public JFXTextField portField;
    public JFXTextField usernameField;
    public JFXPasswordField passwordField;

    private User user;
    public static final Logger LOGGER = Logger.getLogger(Controller.class.getName());


    public void initialize(URL location, ResourceBundle resources) {

    }


    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        int userPort = Integer.parseInt(portField.getText());
        String username = usernameField.getText();
        String password = passwordField.getText();
        Stage primaryStage = (Stage) connectButton.getParent().getScene().getWindow();
        primaryStage.setTitle("Welcome - " + username);
        User user = new User(username, password, userPort);
        this.user = user;
        Thread listenThread = new ListenThread(user);
        listenThread.start();
        LOGGER.log(Level.INFO, "Listening thread started.");
        loadConnectController();
    }

    private void loadConnectController() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("connect.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Connect to another peer - " + user.getUsername());
        stage.setScene(new Scene(root, 700, 575));
        stage.show();
    }
}

import background.ListenThread;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import core.User;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public JFXButton connectButton;
    public JFXTextField portField;
    public JFXTextField usernameField;
    public JFXPasswordField passwordField;

    private User user;


    public void initialize(URL location, ResourceBundle resources) {

    }


    public void loginOnAction(ActionEvent actionEvent) {
        int userPort = Integer.parseInt(portField.getText());
        String username = usernameField.getText();
        String password = passwordField.getText();
        User user = new User(username, password, userPort);
        this.user = user;
        Thread listenThread = new ListenThread(user);
        listenThread.start();
    }
}

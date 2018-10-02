import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TreeView;
import net.Session;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectController {
    public JFXButton connectButton;
    public JFXTextField ipField;
    public JFXTextField portField;
    public static final Logger LOGGER = Logger.getLogger(ConnectController.class.getName());
    public Label thisPort;

    private String ip;
    private String port;

    private TreeView<String> treeView = new TreeView<String>();


    public void connectOnAction(ActionEvent actionEvent) throws IOException {
        String ip = ipField.getText();
        int port = Integer.parseInt(portField.getText());
        Socket clientSocket = new Socket(ip, port);
        LOGGER.log(Level.INFO, "Trying to establish.");
        Session session = new Session(clientSocket);
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(String port) {
        this.port = port;
    }


    public void setNetworkLabels() {
        thisPort.setText(port);
    }
}

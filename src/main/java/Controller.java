import background.ListenThread;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import core.Peer;
import file.FileUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ui.UIUtils;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {

    public JFXButton connectButton;
    public JFXTextField portField;
    public JFXTextField usernameField;
    public JFXPasswordField passwordField;
    public JFXButton chooseButton;

    private Peer peer;

    private File sharedDirectory;

    public static final Logger LOGGER =
            Logger.getLogger(Controller.class.getName());

    private TreeView<File> fileTreeView = new TreeView<File>();

    public void loginOnAction(ActionEvent actionEvent)
            throws IOException {
        int userPort = Integer.parseInt(portField.getText());

        String username = usernameField.getText();
        String password = passwordField.getText();

        Stage primaryStage =
                (Stage) connectButton.getParent().getScene()
                        .getWindow();
        primaryStage.setTitle("Welcome - " + username);

        this.peer = new Peer(username, password, userPort);
        peer.setDirectory(sharedDirectory);

        Thread listenThread = new ListenThread(peer);
        listenThread.start();

        LOGGER.log(Level.INFO, "Listening thread started.");

        loadConnectController();
    }

    private void loadConnectController() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("connect.fxml"));
        Parent root = fxmlLoader.load();

        ConnectController connectController =
                fxmlLoader.getController();
        connectController.setPort(portField.getText());
        connectController.setNetworkLabels();

        Stage stage = Main.getPrimaryStage();
        stage.setTitle(
                "Connect to another peer - " + peer.getUsername());
        stage.setScene(new Scene(root, 700, 575));
        stage.show();
    }

    public void chooseActionPerformed(ActionEvent actionEvent) {
        File dir = FileUtils.chooseDirectory(Main.getPrimaryStage());

        if (dir == null || !dir.isDirectory()) {
            FileUtils.actionOnNullDirectory();
        } else {
            this.sharedDirectory = dir;
            fileTreeView.setRoot(FileUtils.getNodesForDirectory(dir));
            //treeViewOnClickListener(fileTreeView);
            setFileDisplayLayout();
        }
    }

    private void setFileDisplayLayout() {
        UIUtils.displayTreeView(fileTreeView, "Shared files.");
    }


}

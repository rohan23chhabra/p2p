import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger
        .HamburgerBackArrowBasicTransition;
import com.jfoenix.transitions.hamburger
        .HamburgerNextArrowBasicTransition;
import core.Peer;
import core.Parents;
import file.FileUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import net.Session;
import ui.UIUtils;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectController implements Initializable {

    public JFXButton connectButton;
    public JFXTextField ipField;
    public JFXTextField portField;
    public static final Logger LOGGER =
            Logger.getLogger(ConnectController.class.getName());
    public Label thisPort;
    public JFXHamburger hamburger;
    public JFXDrawer drawer;
    public JFXButton sharedRemoteDirectories;
    private String port;

    private Peer peer;

    private TreeView<String> treeView = new TreeView<String>();

    private HamburgerBackArrowBasicTransition
            backArrowBasicTransition;

    private HamburgerNextArrowBasicTransition backButton;

    public static final String CONNECTION_STRING =
            "Send shared file data";

    public static final String FILE_DOWNLOAD_PATH =
            "/home/rohan/Desktop";

    public static final String TITLE_REMOTE_SHARED_DIRECTORY =
            "Files shared by remote user.";

    public void connectOnAction(ActionEvent actionEvent)
            throws IOException {

        String ip = ipField.getText();
        int port = Integer.parseInt(portField.getText());

        Socket clientSocket = new Socket(ip, port);
        LOGGER.log(Level.INFO, "Trying to establish.");

        Session session = new Session(clientSocket);
        session.communicate(CONNECTION_STRING);

        peer.getRemoteDirectories().add(session
                .getOtherSharedDirectory());

        displayOtherSharedDirectories(
                session.getOtherSharedDirectory());
    }

    public void setPort(String port) {
        this.port = port;
    }


    public void setNetworkLabels() {
        thisPort.setText(port);
    }

    private void displayOtherSharedDirectories(File directory) {
        TreeView<File> fileTreeView = new TreeView<File>();
        fileTreeView
                .setRoot(FileUtils.getNodesForDirectory(directory));
        setTreeViewOnClickListener(fileTreeView);
        UIUtils.displayTreeView(fileTreeView,
                TITLE_REMOTE_SHARED_DIRECTORY);
    }

    private void setTreeViewOnClickListener(
            final TreeView<File> fileTreeView) {
        fileTreeView.setOnMouseClicked(
                event -> {
                    if (event.getClickCount() == 2) {
                        TreeItem<File> selectedItem =
                                fileTreeView.getSelectionModel
                                        ().getSelectedItem();
                        File fileToDownload = selectedItem
                                .getValue();
                        try {
                            FileUtils.storeFileOnDisk
                                    (fileToDownload,
                                            FILE_DOWNLOAD_PATH);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.backArrowBasicTransition
                = new HamburgerBackArrowBasicTransition(hamburger);
        backArrowBasicTransition.setRate(-1);
    }

    public void hamburgerOnAction(MouseEvent mouseEvent) {
        backArrowBasicTransition
                .setRate(backArrowBasicTransition.getRate() * (-1.0));
        backArrowBasicTransition.play();

        if (drawer.isOpened()) {
            drawer.close();
        } else {
            drawer.open();
        }
    }

    public void setPeer(Peer peer) {
        this.peer = peer;
    }

    public void backButtonOnAction(MouseEvent mouseEvent) {
        Parents.getRootStack().pop();
        Main.getPrimaryStage().getScene().setRoot(Parents
                .getRootStack().peek());
    }

    public void sharedRemoteDirectoriesOnAction(
            ActionEvent actionEvent) {

    }

    public void initDrawerController() throws IOException {
        FXMLLoader fxmlLoader =
                new FXMLLoader(getClass().getResource
                        ("drawerContent.fxml"));
        VBox vBox = fxmlLoader.load();
        DrawerController drawerController = fxmlLoader
                .getController();
        drawerController.setPeer(this.peer);
        drawer.setSidePane(vBox);
        drawer.close();

    }
}

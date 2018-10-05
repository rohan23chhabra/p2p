import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import file.FileUtils;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import net.Session;
import ui.UIUtils;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectController {
    public JFXButton connectButton;
    public JFXTextField ipField;
    public JFXTextField portField;
    public static final Logger LOGGER =
            Logger.getLogger(ConnectController.class.getName());
    public Label thisPort;

    private String port;

    private TreeView<String> treeView = new TreeView<String>();

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
}

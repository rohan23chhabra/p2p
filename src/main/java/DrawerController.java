import com.jfoenix.controls.JFXButton;
import core.Parents;
import core.Peer;
import file.FileUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

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

    public void viewSessionOnAction(ActionEvent actionEvent)
            throws IOException {
        Stage stage = Main.getPrimaryStage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("viewSession.fxml"));
        Parent root = fxmlLoader.load();
        Parents.getRootStack().push(root);
        ViewSessionController controller = fxmlLoader.getController();
        controller.setPeer(this.peer);
        stage.getScene().setRoot(root);
        controller.updateData();
        controller.populateListView();
    }

    public void sharedDirectoryOnAction(ActionEvent actionEvent) {
        TreeView<File> fileTreeView = new TreeView<File>();
        File sharedDirectory = peer.getDirectory();
        if (sharedDirectory == null ||
                !sharedDirectory.isDirectory()) {
            FileUtils.actionOnNullDirectory();
        } else {
            fileTreeView.setRoot(
                    FileUtils.getNodesForDirectory(sharedDirectory));
            FileUtils.setFileDisplayLayout(fileTreeView);
        }
    }

    public void changeNameOnAction(ActionEvent actionEvent) {

    }
}

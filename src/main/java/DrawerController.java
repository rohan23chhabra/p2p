import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import core.Parents;
import core.Peer;
import file.FileUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TreeView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import static ui.StyleConstants.*;

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

    public void changeNameOnAction(ActionEvent actionEvent)
            throws IOException {
        StackPane stackPane = FXMLLoader.load(getClass()
                .getResource("dialog.fxml"));

        Main.getPrimaryStage().getScene().setRoot(stackPane);

        Label heading = new Label("Enter new name");
        heading.setStyle(LABEL_STYLE);

        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(heading);

        JFXTextField input = new JFXTextField();
        input.setStyle(TEXTFIELD_STYLE);

        JFXButton okayButton = new JFXButton("Done");
        JFXButton cancelButton = new JFXButton("Cancel");

        okayButton.setStyle(BUTTON_STYLE);
        cancelButton.setStyle(BUTTON_STYLE);


        content.setActions(input, okayButton, cancelButton);

        JFXDialog dialog =
                new JFXDialog(stackPane, content,
                        JFXDialog.DialogTransition.CENTER);

        Parent root = Parents.getRootStack().peek();
        okayButton.setOnAction(event -> {
            JFXDialogLayout content1 =
                    (JFXDialogLayout) dialog.getContent();
            JFXTextField field =
                    (JFXTextField) content1.getActions().get(0);
            peer.setUsername(field.getText());
            dialog.close();
            Main.getPrimaryStage().getScene().setRoot(root);
        });

        cancelButton.setOnAction(event -> {
            dialog.close();
            Main.getPrimaryStage().getScene().setRoot(root);
        });

        dialog.show();
    }
}

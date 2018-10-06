package ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import core.Parents;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class DialogUtils {
    public static void prepareAndShowDialog(JFXDialogLayout content,
                                                 StackPane stackPane) {
        JFXDialog dialog = new JFXDialog(stackPane, content,
                JFXDialog.DialogTransition.CENTER);
        JFXButton button = (JFXButton) content.getActions().get(0);
        button.setStyle(StyleConstants.BUTTON_STYLE);
        button.setOnAction(event -> {
            dialog.close();
            stackPane.getScene().setRoot(Parents.getRootStack().peek());
        });
        dialog.show();
    }

    public static JFXDialogLayout prepareContent(Node heading) {
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(heading);
        JFXButton button = new JFXButton("OKAY");
        content.setActions(button);
        return content;
    }
}

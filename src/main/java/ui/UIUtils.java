package ui;

import javafx.scene.Scene;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class UIUtils {
    public static void displayTreeView(TreeView<?> treeView,
                                       String title) {
        Stage stage = new Stage();
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(treeView);
        Scene scene = new Scene(borderPane, 650, 550);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
}

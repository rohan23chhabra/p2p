import core.Parents;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        Parent root = FXMLLoader
                .load(getClass().getResource("init.fxml"));
        stage.setTitle("Welcome to P2P");
        Scene primaryScene = new Scene(root);
        stage.setScene(primaryScene);
        Parents.getRootStack().push(root);
        stage.show();
    }

    private void adjustPaneSize(Parent root) {
        ObservableList<Node> observableList = root
                .getChildrenUnmodifiable();
        Pane pane = (Pane) observableList.get(0);
        pane.setLayoutX(root.getLayoutX() / 2.0);
        pane.setLayoutY(root.getLayoutY() / 2.0);
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

}

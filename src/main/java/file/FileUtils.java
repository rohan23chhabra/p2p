package file;

import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import ui.UIUtils;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileUtils {

    public static final Logger LOGGER =
            Logger.getLogger(FileUtils.class.getName());

    public static File chooseDirectory(Stage stage) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser
                .setTitle("Select folder to share with other peers");
        directoryChooser.setInitialDirectory(
                new File(System.getProperty("user.home")));

        File dir = directoryChooser.showDialog(stage);
        return dir;
    }

    public static TreeItem<File> getNodesForDirectory(File dir) {
        TreeItem<File> root = new TreeItem<File>(dir);
        for (File f : dir.listFiles()) {
            LOGGER.log(Level.INFO, "Loading...");
            if (f.isDirectory()) {
                root.getChildren().add(getNodesForDirectory(f));
            } else {
                root.getChildren().add(new TreeItem<>(f));
            }
        }
        return root;
    }

    public static void actionOnNullDirectory() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Could not open directory");
        alert.setContentText("The file is invalid.");
        alert.showAndWait();
    }

    public static void storeFileOnDisk(File remoteFile, String
            path) throws IOException {
        String name = remoteFile.getName();
        path = path + "/" + name;
        File file = new File(path);
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(remoteFile);
            outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int length;

            while ((length = inputStream.read()) > 0) {
                outputStream.write(buffer, 0, length);
            }
        } finally {
            inputStream.close();
            outputStream.close();
        }
    }

    public static void setFileDisplayLayout(TreeView<File> treeView) {
        UIUtils.displayTreeView(treeView, "Shared files.");
    }
}

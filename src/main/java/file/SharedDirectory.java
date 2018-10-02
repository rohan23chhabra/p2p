package file;

import java.io.File;

public class SharedDirectory {
    public static File directory;

    public static File getDirectory() {
        return directory;
    }

    public static void setDirectory(File directory) {
        SharedDirectory.directory = directory;
    }
}

package core;

import javafx.scene.Scene;

import java.util.Stack;

public class Scenes {
    private static Stack<Scene> scenes = new Stack<Scene>();

    public static Stack<Scene> getScenes() {
        return scenes;
    }

    public static void setScenes(Stack<Scene> scenes) {
        Scenes.scenes = scenes;
    }
}

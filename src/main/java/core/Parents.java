package core;

import javafx.scene.Parent;

import java.util.Stack;

public class Parents {
    private static Stack<Parent> rootStack = new Stack<>();

    public static Stack<Parent> getRootStack() {
        return rootStack;
    }

    public static void setRootStack(Stack<Parent> rootStack) {
        Parents.rootStack = rootStack;
    }
}

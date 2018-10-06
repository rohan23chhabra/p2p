package ui;

import com.jfoenix.controls.JFXTextField;
import javafx.scene.layout.Region;

public class DialogInput extends Region {
    JFXTextField textField;

    public DialogInput(JFXTextField textField) {
        this.textField = textField;
    }

    public JFXTextField getTextField() {
        return textField;
    }
}

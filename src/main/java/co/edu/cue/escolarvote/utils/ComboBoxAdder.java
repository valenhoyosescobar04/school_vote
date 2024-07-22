package co.edu.cue.escolarvote.utils;

import javafx.scene.control.ComboBox;

import java.util.List;

public class ComboBoxAdder {
    public static void addComboBoxOptions(ComboBox comboBox, List<String> options) {
        comboBox.getItems().clear();
        comboBox.getItems().addAll(options);
    }
}

module co.edu.cue.escolarvote {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.datatransfer;
    requires java.desktop;
    requires java.sql;

    opens co.edu.cue.escolarvote to javafx.fxml;
    exports co.edu.cue.escolarvote;

    exports co.edu.cue.escolarvote.controller;
    opens co.edu.cue.escolarvote.controller to javafx.fxml;
}
package co.edu.cue.escolarvote.utils;

import javafx.animation.PauseTransition;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.Optional;

public class AlertGenerator {
    public static void alertError(String message,String header){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(header);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void alertInformation(String message,String header){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(header);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void showVotingConfirmation(Runnable onConfirmation) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Voting Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Your vote has been recorded. Please wait...");
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getButtonTypes().clear();
        alert.show();
        PauseTransition delay = new PauseTransition(Duration.seconds(5));
        delay.setOnFinished(event -> {
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.close();
            if (onConfirmation != null) {
                onConfirmation.run();
            }
        });
        delay.play();
    }


    public static boolean alertConfirmation(String message, String header) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(header);
        alert.setTitle("Confirmation");
        alert.setContentText(message);
        alert.initStyle(StageStyle.UTILITY); // Optional: use this to style the alert

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }


}

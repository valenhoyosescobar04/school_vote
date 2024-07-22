package co.edu.cue.escolarvote.utils;

import co.edu.cue.escolarvote.domain.entities.Candidate;
import co.edu.cue.escolarvote.domain.enums.Grade;
import co.edu.cue.escolarvote.domain.enums.StudyingDay;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;
import java.util.Objects;

public class InputsSelectionController {
    public static void  showInInputs(Candidate candidate, TextField nameCandidatesTxt, ImageView selectedImageView, ComboBox<String> cbStudyingDay, ComboBox<String> cbGrade){
        if (candidate != null) {
            nameCandidatesTxt.setText(candidate.getName());
            selectedImageView.setImage(new Image(new ByteArrayInputStream(candidate.getProfileImage())));
            cbStudyingDay.setValue(candidate.getStudyingDay().getLabel());
            cbGrade.setValue(candidate.getGrade());
        }
    }

    public static void cancelSelection(TextField nameCandidatesTxt, ImageView selectedImageView, ComboBox<String> cbStudyingDay, ComboBox<String> cbGrade){
        nameCandidatesTxt.setText("");
        selectedImageView.setImage(new Image(Objects.requireNonNull(InputsSelectionController.class.getResourceAsStream("/co/edu/cue/escolarvote/images/galeria-de-imagenes.png"))));
        cbGrade.setValue(null);
        cbStudyingDay.setValue(null);
        cbStudyingDay.setPromptText("Jornada");
        cbGrade.setPromptText("Grado");
    }
}

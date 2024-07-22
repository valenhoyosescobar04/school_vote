package co.edu.cue.escolarvote.controller;

import co.edu.cue.escolarvote.domain.entities.Candidate;
import co.edu.cue.escolarvote.domain.enums.Grade;
import co.edu.cue.escolarvote.domain.enums.StudyingDay;
import co.edu.cue.escolarvote.utils.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminCandidatesViewController implements Initializable {

    private final ModelFactoryController mfc=ModelFactoryController.getInstance();

    @FXML
    private Button cancelBtn;

    @FXML
    private VBox candidateVbox;

    @FXML
    private ComboBox<String> cbGrade;

    @FXML
    private ComboBox<String> cbStudyingDay;

    @FXML
    private Button createCandidateBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private Button imageSelectorBtn;

    @FXML
    private Button menuOptionCandidates;

    @FXML
    private Button menuOptionStatistics;

    @FXML
    private ImageView selectedImageView;

    @FXML
    private TextField nameCandidatesTxt;

    private byte[] imageData;

    @FXML
    private Button updateBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ComboBoxAdder.addComboBoxOptions(cbGrade, Grade.gradesList());
        ComboBoxAdder.addComboBoxOptions(cbStudyingDay, StudyingDay.studyingDayList());
        List<Candidate> candidates= mfc.getSchool().candidateService.getCandidates();
        if (!candidates.isEmpty()) {
            DynamicCreator.putControlCandidates(candidates,candidateVbox,nameCandidatesTxt,selectedImageView,cbStudyingDay,cbGrade);
        }
    }



    @FXML
    void cancelCandidateSelection(ActionEvent event) {
        InputsSelectionController.cancelSelection(nameCandidatesTxt,selectedImageView,cbStudyingDay,cbGrade);
        imageData=null;
        mfc.candidate=null;
    }

    @FXML
    void changeToCandidatesView(ActionEvent event) throws IOException {
        ChangerFXMLController.sceneChange(event,"views/admin/admin_candidates_view.fxml");
    }

    @FXML
    void changeToStatisticsView(ActionEvent event) throws IOException {
        ChangerFXMLController.sceneChange(event,"views/admin/admin_statistics_view.fxml");
    }

    @FXML
    void createCandidate(ActionEvent event) throws IOException {
        String name = nameCandidatesTxt.getText();
        String grade = cbGrade.getValue();
        byte[] image = imageData;
        StudyingDay studyingDay = StudyingDay.fromLabel(cbStudyingDay.getValue());
        if (mfc.candidate != null) {
            AlertGenerator.alertError("Candidato ya seleccionado", "error");
            return;
        }
        if (!Validator.validateCandidate(name, grade, imageData, studyingDay)) {
            AlertGenerator.alertError("Falló la validación del candidato", "error");
            return;
        }
        List<Candidate> candidates = mfc.getSchool().candidateService.filterByStudyingDay(studyingDay);
        if (candidates.size() >= 8) {
            AlertGenerator.alertError("No puedes crear más candidatos en la jornada de " + studyingDay.getLabel(),
                    "error");
            return;
        }
        mfc.getSchool().candidateService.createCandidate(name, grade, image, studyingDay);
        AlertGenerator.alertInformation("Candidato creado exitosamente", "success");
        changeToCandidatesView(event);
    }


    @FXML
    void deleteCandidate(ActionEvent event) throws IOException {
        if (mfc.candidate != null) {
            boolean confirmed = AlertGenerator.alertConfirmation("¿Está seguro de que desea eliminar este candidato?", "Eliminar candidato");
            if (confirmed) {
                mfc.getSchool().candidateService.deleteCandidate(mfc.candidate.getId());
                changeToCandidatesView(event);
            }
        } else AlertGenerator.alertError("Candidato no seleccionado","error");
    }

    @FXML
    void exitMainView(ActionEvent event) throws IOException {
        ChangerFXMLController.sceneChange(event,"views/main_view.fxml");
    }

    @FXML
    void selectImage(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Imagen");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Archivos de Imagen", "*.png", "*.jpg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            imageData = ImageUtil.convertFileToByteArray(selectedFile);
            selectedImageView.setImage(
                    imageData.length>0
                        ? new Image(new ByteArrayInputStream(imageData))
                        : new Image(Objects.requireNonNull(getClass().getResourceAsStream("/co/edu/cue/escolarvote/images/default-user-image.png")))
            );
        }
    }

    @FXML
    void updateCandidate(ActionEvent event) throws IOException {
        if (mfc.candidate != null) {
            String name = nameCandidatesTxt.getText();
            String grade = cbGrade.getValue();
            byte[] image = mfc.candidate.getProfileImage();
            StudyingDay studyingDay = StudyingDay.fromLabel(cbStudyingDay.getValue());
            boolean confirmed = AlertGenerator.alertConfirmation("¿Está seguro de que desea actualizar este candidato?", "Actualizar candidato");
            if (confirmed && Validator.validateCandidate(name, grade, image, studyingDay)) {
                mfc.getSchool().candidateService.updateCandidate(mfc.candidate.getId(), new Candidate(name,image,grade,studyingDay));
                changeToCandidatesView(event);
            }
        } else AlertGenerator.alertError("Candidato no seleccionado","error");
    }





}

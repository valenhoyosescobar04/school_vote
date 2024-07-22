package co.edu.cue.escolarvote.controller;

import co.edu.cue.escolarvote.domain.entities.Administrator;
import co.edu.cue.escolarvote.domain.entities.Candidate;
import co.edu.cue.escolarvote.domain.entities.Voter;
import co.edu.cue.escolarvote.domain.enums.Grade;
import co.edu.cue.escolarvote.domain.enums.StudyingDay;
import co.edu.cue.escolarvote.utils.AlertGenerator;
import co.edu.cue.escolarvote.utils.ChangerFXMLController;
import co.edu.cue.escolarvote.utils.ComboBoxAdder;
import co.edu.cue.escolarvote.utils.DynamicCreator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class VoterLoginController implements Initializable {
    private final ModelFactoryController mfc=ModelFactoryController.getInstance();

    @FXML
    private Button LoginBtn;

    @FXML
    private ComboBox<String> cbStudyingDay;

    @FXML
    private TextField nidLoginTxt;

    @FXML
    private Button returnBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ComboBoxAdder.addComboBoxOptions(cbStudyingDay, StudyingDay.studyingDayList());
    }


    @FXML
    void Login(ActionEvent event) throws IOException {
        String nid = nidLoginTxt.getText();
        String studyingDay = cbStudyingDay.getValue();
        if (nid.isEmpty() || studyingDay == null || studyingDay.isEmpty()) {
            AlertGenerator.alertError("Empty inputs", "Login failed");
            return;
        }
        Voter voter = new Voter(nid, StudyingDay.fromLabel(studyingDay));
        if (mfc.getSchool().voterLoginService.login(voter)) {
            mfc.voter = mfc.getSchool().voterService.getVoterByNID(nid).get();
            ChangerFXMLController.sceneChange(event, "views/voter/voter_view.fxml");
        } else {
            AlertGenerator.alertError("The person is not valid", "Login failed");
        }
    }

    @FXML
    void returnToMain(ActionEvent event) throws IOException {
        ChangerFXMLController.sceneChange(event,"views/main_view.fxml");
    }

}

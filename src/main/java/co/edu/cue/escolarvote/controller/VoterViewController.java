package co.edu.cue.escolarvote.controller;

import co.edu.cue.escolarvote.domain.entities.Candidate;
import co.edu.cue.escolarvote.utils.ChangerFXMLController;
import co.edu.cue.escolarvote.utils.DynamicCreator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class VoterViewController implements Initializable{

    private final ModelFactoryController mfc=ModelFactoryController.getInstance();

    @FXML
    private VBox candidatesVbox;

    @FXML
    private Button returnBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Candidate> candidates= mfc.getSchool().candidateService.getCandidates().stream()
                .filter(x->x.getStudyingDay().equals(mfc.voter.getStudyingDay())).toList();
        if (!candidates.isEmpty()) {
            DynamicCreator.putCandidates(candidates, candidatesVbox);
        }
    }

    @FXML
    void returnToMain(ActionEvent event) throws IOException {
        ChangerFXMLController.sceneChange(event,"views/main_view.fxml");
    }

}

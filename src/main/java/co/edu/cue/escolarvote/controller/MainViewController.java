package co.edu.cue.escolarvote.controller;
import co.edu.cue.escolarvote.utils.ChangerFXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainViewController {


    @FXML
    private Button adminBtn;

    @FXML
    private Button voterBtn;

    @FXML
    void AdminLogin(ActionEvent event) throws IOException {
        ChangerFXMLController.sceneChange(event,"views/login/administrator_login_view.fxml");
    }

    @FXML
    void VoterLogin(ActionEvent event) throws IOException {
        ChangerFXMLController.sceneChange(event,"views/login/voter_login_view.fxml");
    }

}

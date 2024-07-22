package co.edu.cue.escolarvote.controller;

import co.edu.cue.escolarvote.domain.entities.Administrator;
import co.edu.cue.escolarvote.utils.AlertGenerator;
import co.edu.cue.escolarvote.utils.ChangerFXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AdminLoginController {
    private final ModelFactoryController mfc=ModelFactoryController.getInstance();

    @FXML
    private Button LoginBtn;

    @FXML
    private Button returnBtn;

    @FXML
    private TextField usernameLoginTxt;

    @FXML
    private TextField passwordLoginTxt;

    @FXML
    void Login(ActionEvent event) throws IOException {
        String username=usernameLoginTxt.getText();
        String password=passwordLoginTxt.getText();
        if (!(username.isEmpty() || password.isEmpty())){
            mfc.getSchool().adminLoginService.login(new Administrator(usernameLoginTxt.getText(), passwordLoginTxt.getText()));
            ChangerFXMLController.sceneChange(event,"views/admin/admin_candidates_view.fxml");
        } else AlertGenerator.alertError("The person does not exits","Login failed");

    }

    @FXML
    void returnToMain(ActionEvent event) throws IOException {
        ChangerFXMLController.sceneChange(event,"views/main_view.fxml");
    }

}

package co.edu.cue.escolarvote.controller;

import co.edu.cue.escolarvote.domain.enums.StudyingDay;
import co.edu.cue.escolarvote.utils.ChangerFXMLController;
import co.edu.cue.escolarvote.utils.ComboBoxAdder;
import co.edu.cue.escolarvote.utils.StatisticsCreator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

import java.io.IOException;
public class AdminStatisticsViewController implements Initializable {

    @FXML
    private ComboBox<String> cbStudyingDay;

    @FXML
    private Button exitBtn;

    @FXML
    private Button menuOptionCandidates;

    @FXML
    private Button menuOptionStatistics;

    @FXML
    private BarChart<String, Number> votationBarChart;

    @FXML
    private PieChart votationsPieChart;

    private final ModelFactoryController mfc=ModelFactoryController.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ComboBoxAdder.addComboBoxOptions(cbStudyingDay, StudyingDay.studyingDayList());
        cbStudyingDay.setValue(StudyingDay.MORNING.getLabel());
        initializeStatistics();
    }

    private void initializeStatistics(){
        cbStudyingDay.setOnAction(event -> {
            String selectedValue = cbStudyingDay.getValue();
            StudyingDay selectedDay = StudyingDay.fromLabel(selectedValue);
            StatisticsCreator.createVotePieChart(votationsPieChart, selectedDay);
            StatisticsCreator.createVoteBarChart(votationBarChart, selectedDay);
        });
        if (!cbStudyingDay.getItems().isEmpty()) {
            cbStudyingDay.setValue(cbStudyingDay.getItems().get(0));
            StatisticsCreator.createVotePieChart(votationsPieChart, StudyingDay.fromLabel(cbStudyingDay.getValue()));
            StatisticsCreator.createVoteBarChart(votationBarChart, StudyingDay.fromLabel(cbStudyingDay.getValue()));
        }
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
    void exitMainView(ActionEvent event) throws IOException {
        ChangerFXMLController.sceneChange(event,"views/main_view.fxml");
    }

}

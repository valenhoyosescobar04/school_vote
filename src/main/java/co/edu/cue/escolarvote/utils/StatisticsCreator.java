package co.edu.cue.escolarvote.utils;

import co.edu.cue.escolarvote.controller.ModelFactoryController;
import co.edu.cue.escolarvote.domain.entities.Candidate;
import co.edu.cue.escolarvote.domain.enums.StudyingDay;
import javafx.scene.chart.*;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class StatisticsCreator {
    private static final ModelFactoryController mfc=ModelFactoryController.getInstance();

    public static void createVotePieChart(PieChart votationsPieChart, StudyingDay studyingDay) {
        votationsPieChart.getData().clear();
        List<Candidate> candidates=mfc.getSchool().candidateService.filterByStudyingDay(studyingDay);
        for (Candidate candidate : candidates) {
            PieChart.Data slice = new PieChart.Data(candidate.getName(), mfc.getSchool().voteService.filterByCandidate(candidate.getId()).size()); // `getVotes` es un método que retorna el número de votos para el candidato
            votationsPieChart.getData().add(slice);
        }
        votationsPieChart.setLegendVisible(true);
    }

    public static void createVoteBarChart(BarChart<String, Number> votationBarChart, StudyingDay studyingDay) {
        votationBarChart.getData().clear();
        List<Candidate> candidates = mfc.getSchool().candidateService.filterByStudyingDay(studyingDay);
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        Set<String> usedColors = new HashSet<>();
        for (Candidate candidate : candidates) {
            int voteCount = mfc.getSchool().voteService.filterByCandidate(candidate.getId()).size();
            XYChart.Data<String, Number> data = new XYChart.Data<>(candidate.getName(), voteCount);
            series.getData().add(data);
            String color = generateUniqueColor(usedColors);
            usedColors.add(color);
            data.nodeProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    newValue.setStyle("-fx-bar-fill: " + color + ";");
                }
            });
        }
        votationBarChart.getData().add(series);
        votationBarChart.setLegendVisible(false);
        votationBarChart.setCategoryGap(10);
        votationBarChart.setBarGap(5);
        CategoryAxis xAxis = (CategoryAxis) votationBarChart.getXAxis();
        xAxis.setLabel("Candidatos");
        NumberAxis yAxis = (NumberAxis) votationBarChart.getYAxis();
        yAxis.setLabel("Votos");
        votationBarChart.setAnimated(false);
    }

    private static String generateUniqueColor(Set<String> usedColors) {
        Random random = new Random();
        String color;
        do {
            color = String.format("#%06x", random.nextInt(0xffffff + 1));
        } while (usedColors.contains(color));
        return color;
    }
}

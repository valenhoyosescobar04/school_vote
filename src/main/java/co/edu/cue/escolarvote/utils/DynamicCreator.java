package co.edu.cue.escolarvote.utils;

import co.edu.cue.escolarvote.controller.ModelFactoryController;
import co.edu.cue.escolarvote.domain.entities.Candidate;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import javax.swing.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public class DynamicCreator {
    private static final ModelFactoryController mfc=ModelFactoryController.getInstance();

    public static Button createCandidateControlButton(Candidate candidate, TextField nameCandidatesTxt, ImageView selectedImageView, ComboBox<String> cbStudyingDay, ComboBox<String> cbGrade){
        ImageView imageView=new ImageView(new Image(new ByteArrayInputStream(candidate.getProfileImage())));
        Button button=new Button();
        button.setPrefHeight(80);
        button.setPrefWidth(100);
        button.setCursor(Cursor.HAND);
        button.setId(candidate.getId().toString());
        button.setOnAction(event -> {
            InputsSelectionController.showInInputs(candidate,nameCandidatesTxt,selectedImageView,cbStudyingDay,cbGrade);
            mfc.candidate=candidate;
        });
        imageView.setFitHeight(80);
        imageView.setFitWidth(100);
        button.setStyle("-fx-background-color: transparent;");
        button.setGraphic(imageView);
        return button;
    }

    public static ImageView createCandidateImageView(Candidate candidate){
        ImageView imageView=new ImageView(new Image(new ByteArrayInputStream(candidate.getProfileImage())));
        imageView.setFitHeight(160);
        imageView.setFitWidth(200);
        return imageView;
    }

    public static VBox createVbox(){
        VBox vBox=new VBox();
        vBox.setPrefHeight(80);
        vBox.setPrefWidth(70);
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }

    public static Button createButton1(String btnMessage){
        Button button=new Button();
        button.setPrefHeight(50);
        button.setPrefWidth(120);
        button.setText(btnMessage);
        button.getStyleClass().add("button1");
        button.setCursor(Cursor.HAND);
        return button;
    }

    public static HBox createHbox(){
        HBox hBox=new HBox();
        hBox.setPrefHeight(80);
        hBox.setPrefWidth(70);
        hBox.setSpacing(40);
        return hBox;
    }

    public static Text createName(Candidate candidate){
        Text text=new Text(candidate.getName());
        text.setFill(Paint.valueOf("white"));
        text.setFont(new Font(15));
        return text;
    }

    public static Text createInfo(Candidate candidate){
        Text text=new Text(candidate.getName()+" - "+candidate.getGrade());
        text.setFill(Paint.valueOf("white"));
        text.setFont(new Font(20));
        return text;
    }

    public static Text createStudyingDay(Candidate candidate){
        Text text=new Text(candidate.getStudyingDay().getLabel());
        text.setFill(Paint.valueOf("white"));
        text.setFont(new Font(20));
        return text;
    }

    public static void putControlCandidates(List<Candidate> candidates, VBox vBoxPrincipal, TextField nameCandidatesTxt, ImageView selectedImageView, ComboBox<String> cbStudyingDay, ComboBox<String> cbGrade){
        int x=0;
        while (x<candidates.size()){
            HBox hBox=createHbox();
            hBox.setAlignment(Pos.CENTER);
            individual:for (int y=0;y<4;y++){
                VBox vBox=createVbox();
                vBox.getChildren().add(createName(candidates.get(x)));
                vBox.getChildren().add(createCandidateControlButton(candidates.get(x), nameCandidatesTxt,selectedImageView,cbStudyingDay,cbGrade));
                vBox.getChildren().add(createStudyingDay(candidates.get(x)));
                hBox.getChildren().add(vBox);
                x++;
                if (x==candidates.size()){
                    break individual;
                }
            }
            vBoxPrincipal.getChildren().add(hBox);
        }
    }

    public static void putCandidates(List<Candidate> candidates, VBox vBoxPrincipal){
        int x=0;
        while (x<candidates.size()){

            HBox hBox=createHbox();
            hBox.setAlignment(Pos.CENTER);
            individual:for (int y=0;y<4;y++){
                Candidate candidate = candidates.get(x);
                VBox vBox=createVbox();
                vBox.setSpacing(10);
                vBox.getChildren().add(createInfo(candidate));
                vBox.getChildren().add(createCandidateImageView(candidate));
                Button voteButton=createButton1("Votar");
                voteButton.setOnAction((event -> {
                    mfc.getSchool().voteService.createVote(mfc.voter,candidate);
                    AlertGenerator.showVotingConfirmation(() -> {
                        try {
                            ChangerFXMLController.sceneChange(event,"views/main_view.fxml");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });

                }));
                vBox.getChildren().add(voteButton);
                hBox.getChildren().add(vBox);
                x++;
                if (x==candidates.size()){
                    break individual;
                }
            }
            vBoxPrincipal.getChildren().add(hBox);
        }
    }



}

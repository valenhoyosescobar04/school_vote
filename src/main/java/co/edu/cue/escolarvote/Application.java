package co.edu.cue.escolarvote;

import co.edu.cue.escolarvote.controller.ModelFactoryController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Application extends javafx.application.Application {
    ModelFactoryController mfc=ModelFactoryController.getInstance();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("views/main_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1283.3, 720);
        stage.setTitle("School Vote");
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/co/edu/cue/escolarvote/images/6c0c488a-8483-4fda-902d-1c6cb39ae976-removebg-preview.png"))));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
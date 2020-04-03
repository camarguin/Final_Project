package sample;

import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Citizenship study");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        Controller.readQuestions();
    }


    public static void main(String[] args) {
        launch(args);
    }
}


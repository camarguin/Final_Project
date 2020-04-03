package sample;

import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {


    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Citizenship study");
        primaryStage.setResizable(false);
        //Controller.readQuestions();
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        //Question questionT = new Question();
        //ArrayList<Question> question = new ArrayList<Question>();
        //question = Question.readQuestions("questions.txt");
        /*for (Question q : question){
            System.out.println(q.toString());
            //System.out.println(Question.getOptions(q));
        }*/
        launch(args);
    }
}


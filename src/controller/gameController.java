package controller;


import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import menu.menuController;
import sample.Question;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class gameController implements Initializable {
    private static int indexQuestion = 0;
    public static ArrayList<Question> questions = new ArrayList<Question>();
    public static int score = 0;
    @FXML
    public BorderPane borderPane;
    @FXML
    public RadioButton radioButtonA1;
    @FXML
    public RadioButton radioButtonA2;
    @FXML
    public RadioButton radioButtonA3;
    @FXML
    public RadioButton radioButtonA4;
    @FXML
    public Label questionLabel;
    @FXML
    public Button btnCheckAnswer;
    @FXML
    public ToggleGroup possibleAnswers;

    public static Boolean isSplashLoaded = false;



    public static void readQuestions(){
        questions = Question.readQuestions("questions.txt");
    }

    /**
     * This method updates the radioButtons with the options
     */
    public void updateQuestion(){
        increaseIndexQuestion();
        if (indexQuestion> menuController.getNumberQuestionGame()-1){
            btnCheckAnswer.getScene().getWindow().hide();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("GAME OVER");
            alert.setHeaderText("GAME OVER");
            alert.showAndWait();
        }else{
            Question myQuestion = questions.get(indexQuestion);
            ArrayList<String> option = myQuestion.getOptions();
            questionLabel.setText(myQuestion.getQuestion());
            radioButtonA1.setText(option.get(0));
            radioButtonA2.setText(option.get(1));
            radioButtonA3.setText(option.get(2));
            radioButtonA4.setText(option.get(3));
        }
    }

    /**
     * ButtonClicked method is the action when the user presses the button
     *
     * @param actionEvent
     */

    public void buttonClicked(ActionEvent actionEvent) {
        Question myQuestion = questions.get(indexQuestion);
        String correctAnswer = myQuestion.getAnswer();
        Alert alert;
        // Get the selectedRadioButton --> possibleAnswers it's the ToggleGroup of Radio Buttons
        RadioButton selectedRadioButton = (RadioButton) possibleAnswers.getSelectedToggle();
        // Create the alert and set it
        if (selectedRadioButton == null){
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("You must choose an answer");
        }else {
            if (selectedRadioButton.getText().compareTo(correctAnswer)== 0){
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Correct Answer");
                increaseScore();
            }else {
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Wrong Answer");
            }
            alert.setHeaderText(myQuestion.getQuestion()+"\n\nCorrect answer: "+myQuestion.getAnswer());
            /**
             *
             * TODO
             * put the REASON should be in other variable of questions
             *
             * */
            alert.setContentText("Reason!!!!!!");
            alert.showAndWait();
            updateQuestion();
        }
    }
    private void increaseIndexQuestion(){
        this.indexQuestion++;
    }
    private void increaseScore(){
        this.score++;
    }

    /**
     * *TEST*
     * Method that loads the SplashScreen Inside same window using FadeTransition
     *
     */
    /*private void loadSplashScreen() {
        try {
            isSplashLoaded = true;
            StackPane stackPane = FXMLLoader.load(getClass().getResource("splashscreen.fxml"));
            borderPane.getChildren().setAll(stackPane);

            // Transition effect fadeIN
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), stackPane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), stackPane);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);

            fadeIn.setOnFinished((event) -> {
                fadeOut.play();
            });
            fadeOut.setOnFinished((event) -> {
                try {
                    BorderPane parentContent = FXMLLoader.load(getClass().getResource("game.fxml"));
                    borderPane.getChildren().setAll(parentContent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });


            fadeIn.play();


        } catch (IOException e) {
            //Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }

    }*/



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*if (!isSplashLoaded) {
            loadSplashScreen();
        }*/
        Collections.shuffle(questions);
        Question myQuestion = questions.get(indexQuestion);
        ArrayList<String> option = myQuestion.getOptions();
        questionLabel.setText(myQuestion.getQuestion());
        radioButtonA1.setText(option.get(0));
        radioButtonA2.setText(option.get(1));
        radioButtonA3.setText(option.get(2));
        radioButtonA4.setText(option.get(3));
    }
    public static ArrayList<String> getOptionsNumQuestions(){
        ArrayList<String> numOptions = new ArrayList<String>();
        int increment = questions.size()/4;
        int num = increment;
        numOptions.add(Integer.toString(num));
        num += increment;
        numOptions.add(Integer.toString(num));
        num += increment;
        numOptions.add(Integer.toString(num));
        numOptions.add(Integer.toString(questions.size()));
        return numOptions;
    }
}

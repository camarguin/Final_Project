package controller;


import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private static Double progress = 0.0;
    private static int indexQuestion = 0;
    private final String imageURL = "icon.png";
    private int numQuestions = menuController.getNumberQuestionGame();
    public static ArrayList<Question> questions = new ArrayList<Question>();
    private static final Integer STARTTIME = 0;
    public static Integer[] timeEachQuestionArray;
    private Timeline timeline;
    private Integer timeSeconds = STARTTIME;
    private Integer timeMinutes = STARTTIME;
    private Integer timeHours = STARTTIME;
    public static Integer timeTotal = STARTTIME;
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
    @FXML
    public ProgressBar progressBar;
    @FXML
    public Label progressLabel;
    @FXML
    public Label timerLabel;

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
            timeline.stop();
            btnCheckAnswer.getScene().getWindow().hide();
            changeScene();
            /*Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("GAME OVER");
            alert.setHeaderText("GAME OVER");
            alert.showAndWait();*/
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
        if (!(selectedRadioButton == null)){
            timeEachQuestionArray[indexQuestion] = getTimeTotal();
            //System.out.println(timeEachQuestionArray[indexQuestion].toString());
            if (selectedRadioButton.getText().compareTo(correctAnswer)== 0){
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Correct Answer");
                alert.getDialogPane().setGraphic(new ImageView("correctIcon.png"));

                increaseScore();
            }else {
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Wrong Answer");
                questions.get(indexQuestion).setCorrect(false);
                alert.getDialogPane().setGraphic(new ImageView("wrongIcon.png"));
            }
            alert.setHeaderText(myQuestion.getQuestion()+"\n\nCorrect answer: "+myQuestion.getAnswer());
            progressBar.setProgress(increaseProgress());
            progressLabel.setText((indexQuestion + 1) + "/" + numQuestions);
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
     * Method calculates the progress in the progressBar
     *
     */
    public Double increaseProgress() {

        return progress += 1.0 / numQuestions;
    }

    public int increaseProgressLabel() {
        return numQuestions;
    }

    /**
     * Method to change the scene and show the Score in a new window
     */
    public void changeScene() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../score/score.fxml"));
            root.getStylesheets().add("style/style.css");
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("CITIZENCANN - Score");
        stage.getIcons().add(new Image(imageURL));
        stage.setScene(new Scene(root, 450, 250));
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Method to start the timer when the user starts to do the test
     */
    public void startTimer() {
        timerLabel.setText(timeHours.toString() + ":" + timeMinutes.toString() + ":" + timeSeconds.toString());
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1),
                        new EventHandler() {
                            @Override
                            public void handle(Event event) {
                                timeSeconds++;
                                timeTotal++;
                                // update timerLabel
                                if (timeSeconds == 59){
                                    timeMinutes++;
                                    timeSeconds = 0;
                                    if (timeMinutes == 59) {
                                        timeMinutes = 0;
                                        timeHours++;
                                    }
                                }
                                timerLabel.setText(timeHours.toString() + ":" + timeMinutes.toString() + ":" + timeSeconds.toString());
                            }
                        }));
        timeline.playFromStart();

    }

    /**
     * Getter and Setter TimeTotal
     * @return TimeTotal
     */
    public Integer getTimeTotal() {
        return timeTotal;
    }

    public void setTimeTotal(Integer timeTotal) {
        this.timeTotal = timeTotal;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        timeEachQuestionArray = new Integer[numQuestions+1];
        questionLabel.setWrapText(true);
        questionLabel.setMaxWidth(480);
        Collections.shuffle(questions);
        Question myQuestion = questions.get(indexQuestion);
        ArrayList<String> option = myQuestion.getOptions();
        questionLabel.setText(myQuestion.getQuestion());
        radioButtonA1.setText(option.get(0));
        radioButtonA2.setText(option.get(1));
        radioButtonA3.setText(option.get(2));
        radioButtonA4.setText(option.get(3));
        progressBar.setProgress(0);
        progressLabel.setText(0 + "/" + numQuestions);
        startTimer();
    }

    /**
     * Method to return the possible answers of each question
     * @return numOptions
     */
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

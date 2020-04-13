package controller;

import controller.gameController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import controller.menuController;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class scoreController implements Initializable {
    private final String CSSDirection = "style/style.css";
    private final String imageURL = "images/icon.png";
    private StackPane stackPane;
    @FXML
    public ProgressIndicator progressScore;
    @FXML
    public Label scoreLabel;
    @FXML
    public TextField timerTextField;
    @FXML
    public TextField timerQuestionTextField;
    @FXML
    public Button retryButton;

    /**
     * Method to update the score in the progress cirle bar and update the messages for the user
     */

    private void updateScoreMessages() {
        timerTextField.setText(timeTotalString());
        timerQuestionTextField.setText(String.format("%.2f seconds", calculateTime()));
        if (calculateScore() == 1.0) {
            scoreLabel.setText("Great job! You are ready for the Test");
        } else if (calculateScore() >= 0.7 && calculateScore() < 1.0) {
            scoreLabel.setText("Good Job! But not enough, Keep Practicing and you will be ready");
        } else if (calculateScore() >= 0.1 && calculateScore() < 0.6) {
            scoreLabel.setText("You didn't do well. Study further and retake the test");
        } else {
            scoreLabel.setText("Terrible Job! Study more to get better next time.");
        }
    }

    /**
     * Method that prints the time with the score
     * @return
     */
    private String timeTotalString() {
        int hours = (gameController.timeTotal / 60) / 60;
        int minutes = (gameController.timeTotal / 60) % 60;
        int seconds = gameController.timeTotal % 60;
        return hours + ":" + minutes + ":" + seconds;
    }

    /**
     * Method to calculate the average time per question based in the times.
     * @return
     */
    public double calculateTime() {
        return gameController.timeTotal * 1.0 / menuController.getNumberQuestionGame();
    }

    /**
     * This Method calculates the score based in the correct answers and the quantity of questions
     * @return score
     */
    public Double calculateScore() {
        Double score = gameController.score * 1.0 / menuController.getNumberQuestionGame();

        return score;
    }
    public void buttonClicked(ActionEvent actionEvent) {
        gameController.score = 0;
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../view/menuGame.fxml"));
            root.getStylesheets().add(CSSDirection);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Stage stage = new Stage();
        stage.setTitle("CITIZENCANN");
        stage.getIcons().add(new Image(imageURL));
        stage.setScene(new Scene(root, 565, 360));
        stage.setResizable(false);
        stage.show();
        Stage stage1 = (Stage) retryButton.getScene().getWindow();
        stage1.close();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        progressScore.setProgress(calculateScore());
        updateScoreMessages();
    }
}

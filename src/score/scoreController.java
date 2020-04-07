package score;

import controller.gameController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import menu.menuController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class scoreController implements Initializable {

    @FXML
    public ProgressIndicator progressScore;
    @FXML
    public Label scoreLabel;

    private void updateScoreMessages() {
        if (calculateScore() == 1.0) {
            scoreLabel.setText("Awesome job! You are totally ready for the Test.");
        } else if (calculateScore() >= 0.7 && calculateScore() < 1.0) {
            scoreLabel.setText("Good Job! But not enough, Keep Practicing and you will be ready");
        } else if (calculateScore() >= 0.1 && calculateScore() < 0.6) {
            scoreLabel.setText("Terrible Job! Go practice more!");
        } else {
            scoreLabel.setText("Awful job! You got 0 correct answers!");
        }

    }

    public Double calculateScore() {
        Double score = gameController.score * 1.0 / menuController.getNumberQuestionGame();

        return score;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        progressScore.setProgress(calculateScore());
        updateScoreMessages();
    }
}

package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class menuController implements Initializable {
    private final String imageURL = "images/icon.png";
    public static int numberQuestionGame = 0;

    @FXML
    public Button btnConfirmMenu;
    public ChoiceBox numQuestion;

    public void buttonClicked(ActionEvent actionEvent) {
        numberQuestionGame = Integer.parseInt(numQuestion.getValue().toString());
        changeStage();
    }

    /**
     * this function close the current stage and open the stage of the game to start the game with the number
     * questions that the user wants
     *
     * */

    public void changeStage(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../view/game.fxml"));
            root.getStylesheets().add("style/style.css");
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("CITIZENCANN");
        stage.getIcons().add(new Image(imageURL));
        stage.setScene(new Scene(root, 700, 450));
        stage.setResizable(false);
        stage.show();
        Stage stage1 = (Stage) btnConfirmMenu.getScene().getWindow();
        stage1.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> options = gameController.getOptionsNumQuestions();
        ObservableList<String> numQuestions = FXCollections.observableArrayList(options);
        numQuestion.setItems(numQuestions);
        numQuestion.setValue(numQuestions.get(0));
    }

    public static int getNumberQuestionGame() {
        return numberQuestionGame;
    }
}

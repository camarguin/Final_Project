package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class menuController implements Initializable {
    private final String imageURL = "images/icon.png";
    public static int numberQuestionGame = 0;
    private final String hyperLinkURL = "https://www.canada.ca/content/dam/ircc/migration/ircc/english/pdf/pub/discover.pdf";

    @FXML
    public Button btnConfirmMenu;
    public ChoiceBox numQuestion;
    public Hyperlink hyperLink;
    public ImageView infoIcon;
    public Button infoButton;

    /**
     * Method called when the button is clicked
     * @param actionEvent
     */
    public void buttonClicked(ActionEvent actionEvent) {
        numberQuestionGame = Integer.parseInt(numQuestion.getValue().toString());
        changeStage();
    }

    /**
     * This Method opens the Book by the url link
     * @param actionEvent
     */

    public void hyperLinkClicked(ActionEvent actionEvent) {
        try {
            Desktop.getDesktop().browse(new URL(hyperLinkURL).toURI());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    /**
     * Method to show the info about the software
     * @param actionEvent
     */
    public void showInfo(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.getDialogPane().setGraphic(new ImageView("images/info64x64.png"));
        alert.setContentText("CITIZENCANN is a software that was developed to help to prepare you for the Citizenship Test." +
                "\n" +
                "\nDeveloped by: CLZ" +
                "\nVersion 1.0");
        alert.showAndWait();

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
        stage.setScene(new Scene(root, 780, 447));
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
        hyperLink.setText(hyperLinkURL);
    }

    public static int getNumberQuestionGame() {
        return numberQuestionGame;
    }
}

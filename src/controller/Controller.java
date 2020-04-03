package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

public class Controller{

    @FXML
    public RadioButton radioButtonA1;
    @FXML
    private RadioButton radioButtonA2;
    @FXML
    private RadioButton radioButtonA3;
    @FXML
    private RadioButton radioButtonA4;
    @FXML
    private Label labelScore;
    @FXML
    public Button button;


    public void buttonClicked(ActionEvent actionEvent) {
        if (radioButtonA1.isSelected()) {
            System.out.println("Hello my ass");
        }
    }
}

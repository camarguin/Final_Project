package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.Question;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private static int indexQuestion = 0;
    public static ArrayList<Question> questions = new ArrayList<Question>();
    public static int score = 0;
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



    public static void readQuestions(){
        questions = Question.readQuestions("questions.txt");
    }
    public void updateQuestion(){
        increaseIndexQuestion();
        System.out.println(indexQuestion);
        System.out.println(questions.size());
        if (indexQuestion>questions.size()-1){
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        readQuestions();
        Question myQuestion = questions.get(indexQuestion);
        ArrayList<String> option = myQuestion.getOptions();
        questionLabel.setText(myQuestion.getQuestion());
        radioButtonA1.setText(option.get(0));
        radioButtonA2.setText(option.get(1));
        radioButtonA3.setText(option.get(2));
        radioButtonA4.setText(option.get(3));

    }
}

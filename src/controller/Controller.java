package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.Question;

import java.util.ArrayList;

public class Controller{
    private static int indexQuestion = 0;
    public static ArrayList<Question> questions = new ArrayList<Question>();
    public static int score = 0;
    @FXML
    public static RadioButton radioButtonA1 = new RadioButton("");
    @FXML
    private static RadioButton radioButtonA2 = new RadioButton("");
    @FXML
    private static RadioButton radioButtonA3 = new RadioButton("");
    @FXML
    private static RadioButton radioButtonA4 = new RadioButton("");
    @FXML
    private Label label;
    @FXML
    public Button btnCheckAnswer;
    @FXML
    public ToggleGroup possibleAnswers;



    public static void readQuestions(){
        questions = Question.readQuestions("questions.txt");
        setQuestions();
    }
    private static void setQuestions(){
        System.out.println("SetQuestions");
        Question myQuestion = questions.get(indexQuestion);
        System.out.println(myQuestion.getAnswer());
        //radioButtonA1.setText(myQuestion.getAnswer());
        radioButtonA1 = new RadioButton(myQuestion.getAnswer());
        radioButtonA2.setText("option");
        radioButtonA3.setText("option");
        radioButtonA4.setText("option");
    }


    public void buttonClicked(ActionEvent actionEvent) {

        Question myQuestion = questions.get(indexQuestion);
        String correctAnswer = myQuestion.getAnswer();
        // Get the selectedRadioButton --> possibleAnswers it's the ToggleGroup of Radio Buttons
        RadioButton selectedRadioButton = (RadioButton) possibleAnswers.getSelectedToggle();
        // Create the alert and set it
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        if (selectedRadioButton.getText().compareTo(correctAnswer)== 0){
            alert.setTitle("Correct Answer");
            increaseScore();
        }else {
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
    }
    private void increaseIndexQuestion(){
        this.indexQuestion++;
    }
    private void increaseScore(){
        this.score++;
    }

}

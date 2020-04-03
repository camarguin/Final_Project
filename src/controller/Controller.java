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

        //setQuestions();
    }
    public void setQuestions(){
        /*System.out.println("SetQuestions");
        Question myQuestion = questions.get(indexQuestion);
        System.out.println(myQuestion.getAnswer());
        //radioButtonA1.setText(myQuestion.getAnswer());
        radioButtonA1 = new RadioButton(myQuestion.getAnswer());*/
        radioButtonA2.setText("option");
        radioButtonA3.setText("option");
        radioButtonA4.setText("option");
    }


    public void buttonClicked(ActionEvent actionEvent) {

        radioButtonA1.setText("Option1 MOFO");
        radioButtonA2.setText("Option2MOFO");
        radioButtonA3.setText("OPTION3 MOFO");
        radioButtonA4.setText("Option 4 MotherFucker");
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Question question = new Question("Question 1", "Answer 1", "Wrong 1", "Wrong 2", "Wrong 3");
        Question.readQuestions("questions.txt");
        radioButtonA1.setText(question.getOptions().get(0));
        radioButtonA2.setText(question.getOptions().get(1));
        radioButtonA3.setText(question.getOptions().get(2));
        radioButtonA4.setText(question.getOptions().get(3));

    }
}

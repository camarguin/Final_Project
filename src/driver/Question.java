package driver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Question {
    /**
     *  ATTRIBUTES
     */
    private static Scanner scanner;
    private static final String questionsFileName = "questions.txt";
    private static final String DELIMITER = ":";
    private String question;
    private String answer;
    private boolean correct = true;
    ArrayList<String> wrongs;

    /**
     *  CONSTRUCTORS
     *
     * */
    public Question(String question, String answer, String wrong1, String wrong2, String wrong3) {
        this.question = question;
        this.answer = answer;
        this.wrongs = new ArrayList<>();
        this.wrongs.add(wrong1);
        this.wrongs.add(wrong2);
        this.wrongs.add(wrong3);
    }
    public Question() {
    }

    /**
     * This method read the file with questions and get the right answer and created a arraylist with the wrongs ones
     *
     * input: Text questions name     (questions.txt)
     * output: ArrayList with all questions
     *
     * */
    public static ArrayList<Question> readQuestions(String questionsFileName) {
        ArrayList<Question> questions = new ArrayList<>();
        try {
            scanner = new Scanner(new File(questionsFileName));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] split = line.split(DELIMITER);
                questions.add(new Question(split[0].trim(), split[1].trim(), split[2].trim(), split[3].trim(), split[4].trim()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return questions ;
    }

    /**
     *  This method creates a random arrayList with the options for every question
     *
     *  input: none
     *  output: ArrayList with options from each question
     *
     * */
    public ArrayList<String> getOptions() {
        ArrayList<String> options = new ArrayList<>();
        options.add(this.getAnswer());
        for (String w : this.getWrongs()) {
            options.add(w);
        }
        Collections.shuffle(options);
        return options;
    }

    /**
     * Method to return the string of each question
     *
     */
    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", wrongs=" + wrongs +
                '}';
    }

    /**
     * GETTERS AND SETTERS
     *
     */
    public ArrayList<String> getWrongs() {
        return wrongs;
    }
    public String getQuestion() {
        return question;
    }
    public String getAnswer() {
        return answer;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
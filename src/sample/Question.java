package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Question {
    private Scanner scanner;
    private final String questionsFileName = "questions.txt";
    //private final String answersFileName = "answers.txt";
    private final String DELIMITER = ":";
    private String question;
    private String answer;
    ArrayList<String> wrongs;

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

    // Method to read the questions from the txt file (BETA)
    public void readQuestions(String questionsFileName) {
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
        for (Question q : questions){
            System.out.println(q.question);
            System.out.println(q.answer);
        }
        //System.out.println(squestions.toString());
    }
}

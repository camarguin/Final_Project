package splashscreen;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static controller.gameController.readQuestions;

public class loadSplashController implements Initializable {
    @FXML
    private StackPane stackPane;

    private final String imageURL = "icon.png";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new SplashScreen().start();
        readQuestions();
    }

    /**
     * Method to load the splashScreen in separated window
     */
    class SplashScreen extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(2500);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Parent root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("../menu/menuGame.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        Stage stage = new Stage();
                        stage.setTitle("CITIZENCANN");
                        stage.getIcons().add(new Image(imageURL));
                        stage.setScene(new Scene(root, 500, 300));
                        stage.setResizable(false);
                        stage.show();
                        stackPane.getScene().getWindow().hide();
                    }
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

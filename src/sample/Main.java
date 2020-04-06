package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;

public class Main extends Application {
    private final String imageURL = "icon.png";

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../splashscreen/loadsplash.fxml"));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(imageURL));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root, 647, 325));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}


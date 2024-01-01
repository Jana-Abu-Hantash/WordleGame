package com.example.WordleGame;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.Random;

public class lost extends Application {
    private static final String[] lost = {
            "Better luck next time! \nKeep playing and you'll get the hang of it.",
            "Don't worry, it takes time to master this \ngame. Try again and see if you can improve your score.",
            "You were so close! \nKeep practicing and you'll get there.",
            "Don't give up! \nKeep playing and you'll eventually guess all the words.",
            "It's okay to lose sometimes. \nKeep playing and you'll be surprised how much progress you can make.",
            "It's not about winning or losing, it's about having fun! Keep playing and enjoy the game.",
            "You're making progress with every attempt! \nKeep playing and you'll eventually crack all the words.",
            "You gave it your best shot! \nKeep playing and you'll improve with time."
    };

    private final String GuessWord;

    public lost(String word){
        GuessWord = word;
    }

    @Override
    public void start(Stage stage) {
        // the word that they didn't guess

        BorderPane pane = new BorderPane();
        playSound();
        pane.setStyle("-fx-background-color: #250902;");

        ImageView w = new ImageView(new Image("/lost.png"));
//        w.setFitWidth(80);
        w.setFitHeight(70);

        Line line = new Line(0, w.getFitHeight(), 700, w.getFitHeight());
        line.setStroke(Color.WHITE);
        line.setStrokeWidth(5);

        VBox vbox1 = new VBox();
        vbox1.setAlignment(Pos.CENTER);
        vbox1.getChildren().addAll(w, line);
        vbox1.setStyle("-fx-background-color: #550C18;");

        Font font = new Font("Georgia Bold", 15);
        int randomIndex = new Random().nextInt(lost.length);
        Label lbl1 = new Label(lost[randomIndex]);
        lbl1.setFont(font);
        lbl1.setAlignment(Pos.CENTER);
        lbl1.setTextFill(Color.WHITE);
        lbl1.setWrapText(true);
        lbl1.setMaxWidth(350);

        Label lbl2 = new Label("The correct word was " + GuessWord);
        lbl2.setFont(font);
        lbl2.setAlignment(Pos.CENTER);
        lbl2.setTextFill(Color.WHITE);
        lbl2.setWrapText(true);
        lbl2.setMaxWidth(350);

        Button btn = new Button("Play Again");
        btn.setFont(font);
        btn.setOnAction(e -> stage.close());
        btn.setStyle(
                """
                        -fx-background-color: #2D6A4F;
                        -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 10, 0.0, 3, 3);
                        -fx-text-fill: white;
                        """
        );

        btn.setOnMouseEntered(e -> btn.setStyle(
                """
                        -fx-background-color: #550C18;
                        -fx-text-fill: white;
                        """
        ));

        btn.setOnMouseExited(e -> btn.setStyle(
                """
                        -fx-background-color: #2D6A4F;
                        -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 10, 0.0, 3, 3);
                        -fx-text-fill: white;
                        """
        ));


        VBox vbox2 = new VBox();
        vbox2.setAlignment(Pos.CENTER);
        vbox2.setSpacing(15);
        vbox2.getChildren().addAll(lbl2, btn);

        HBox hbox = new HBox();
        hbox.getChildren().addAll(lbl1, vbox2);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(5);

        pane.setCenter(hbox);
        pane.setTop(vbox1);

        Scene scene = new Scene(pane, 700, 210);
        stage.setTitle("Hard Luck");
        stage.setScene(scene);
        stage.setX(409);
        stage.setY(310);
        stage.show();
    }

    private void playSound(){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    new File("/Users/janaabuhantash/IdeaProjects/trial/src/main/resources/lost.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

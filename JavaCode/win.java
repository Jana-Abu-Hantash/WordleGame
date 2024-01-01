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

public class win extends Application {
    private static final String[] congrats = {
            "Excellent work!  \nYou've solved the word!",
            "Great job! \nYou're a word-guessing master!",
            "Wow, you're on fire! \nKeep up the good work!",
            "Impressive! \nYou guessed the word with just a few tries!",
            "You're a natural at this! \nKeep playing and see how far you can go!",
            "You're a word wizard! \nKeep guessing those words!",
            "You've cracked the code! \nThat word never stood a chance.",
            "Keep playing and see how many more words you can guess.",
            "Excellent work! Hooray, you won! \nKeep up the momentum and conquer the next level."
    };


    @Override
    public void start(Stage stage) {

        //num of trials
        BorderPane pane = new BorderPane();
        pane.setStyle("-fx-background-color: #081C15;");
        playSound();

        ImageView w = new ImageView(new Image("/cong.png"));
        w.setFitHeight(70);

        Line line = new Line(0, w.getFitHeight(), 700, w.getFitHeight());
        line.setStroke(Color.WHITE);
        line.setStrokeWidth(5);

        VBox vbox1 = new VBox();
        vbox1.setAlignment(Pos.CENTER);
        vbox1.getChildren().addAll(w, line);
        vbox1.setStyle("-fx-background-color: #2D6A4F;");

        int randomIndex = new Random().nextInt(congrats.length);
        Label lbl = new Label(congrats[randomIndex]);
        Font font = new Font("Georgia Bold", 15);
        lbl.setFont(font);
        lbl.setAlignment(Pos.CENTER);
        lbl.setTextFill(Color.WHITE);
        lbl.setWrapText(true);
        lbl.setMaxWidth(400);

        Button btn = new Button("Play Again");
        btn.setFont(font);
        btn.setOnAction(e -> stage.close());

        btn.setStyle(
                """
                        -fx-background-color: #550C18;
                        -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 10, 0.0, 3, 3);
                        -fx-text-fill: white;
                        """
        );

        btn.setOnMouseEntered(e -> btn.setStyle(
                """
                        -fx-background-color: #2D6A4F;
                        -fx-text-fill: white;
                        """
        ));

        btn.setOnMouseExited(e -> btn.setStyle(
                """
                        -fx-background-color: #550C18;
                        -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 10, 0.0, 3, 3);
                        -fx-text-fill: white;
                        """
        ));

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);
        hbox.getChildren().addAll(lbl, btn);

        pane.setCenter(hbox);
        pane.setTop(vbox1);

        Scene scene = new Scene(pane, 700, 210);
        stage.setTitle("You nailed it!");
        stage.setScene(scene);
        stage.setX(409);
        stage.setY(310);
        stage.show();
    }

    protected void playSound(){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    new File("/Users/janaabuhantash/IdeaProjects/trial/src/main/resources/win.wav"));
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

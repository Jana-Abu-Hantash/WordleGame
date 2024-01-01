package com.example.WordleGame;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;


public class MainPage extends Application {
    public Boolean Sound = true;
    private Button btnPlay;
    private Button btnHelp;
    private static final String[] title1 = {
            "T","H","E"};
    private static final String[] title2 = {
            "W","O","R","D","L","E"};
    private static final String[] title3 = {
            "G","A","M","E"};

    protected BorderPane getPane() {

        BorderPane pane = new BorderPane();
        pane.setStyle("-fx-background-color: #232528;");

        playSound(getSound());

        int rows = 3;
        int cols = 6;

        Label[][] labels= new Label[rows][cols];
        Rectangle[][] rectangles= new Rectangle[rows][cols];
        Group[][] groups = new Group[rows][cols];

        // Initialize each element in the labels array with a new Label object
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                labels[i][j] = new Label();
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rectangles[i][j] = new Rectangle();
            }
        }

        for (int i = 0; i < 3; i++) {
            rectangles[0][i] = new Rectangle(80, 80);
            rectangles[0][i].setFill(Color.web("#5B8C5A"));
            rectangles[0][i].setStroke(Color.web("#5B8C5A"));
            rectangles[0][i].setStrokeWidth(3);
        }

        for (int i = 3; i < 6; i++) {
            rectangles[0][i] = new Rectangle(80, 80);
            rectangles[0][i].setFill(Color.TRANSPARENT);
            rectangles[0][i].setStroke(Color.web("#9BA7C0"));
            rectangles[0][i].setStrokeWidth(3);
        }

        for (int i = 0; i < 6; i++) {
            rectangles[1][i] = new Rectangle(80, 80);
            rectangles[1][i].setFill(Color.web("#5B8C5A"));
            rectangles[1][i].setStroke(Color.web("#5B8C5A"));
            rectangles[1][i].setStrokeWidth(3);
        }

        for (int i = 0; i < 2; i++) {
            rectangles[2][i] = new Rectangle(80, 80);
            rectangles[2][i].setFill(Color.TRANSPARENT);
            rectangles[2][i].setStroke(Color.web("#9BA7C0"));
            rectangles[2][i].setStrokeWidth(3);
        }

        for (int i = 2; i < 6; i++) {
            rectangles[2][i] = new Rectangle(80, 80);
            rectangles[2][i].setFill(Color.web("#5B8C5A"));
            rectangles[2][i].setStroke(Color.web("#5B8C5A"));
            rectangles[2][i].setStrokeWidth(3);
        }


        for (int i = 0; i < labels.length; i++) {
            for (int j = 0; j < labels[i].length; j++) {
                labels[i][j] = new Label();
                labels[i][j].setFont(Font.font("Impact", FontWeight.BOLD, 80));
                if(i == 0 && j < 3 ) {
                    labels[i][j].setText(title1[j]);
                }
                else if (i == 1 && j < 7) {
                    labels[i][j].setText(title2[j]);
                }
                else if (i == 2) {
                    labels[i][2].setText(title3[0]);
                    labels[i][3].setText(title3[1]);
                    labels[i][4].setText(title3[2]);
                    labels[i][5].setText(title3[3]);
                }

                labels[i][j].setTextFill(Color.WHITE);
                labels[i][j].setAlignment(Pos.CENTER);
                labels[i][j].setLayoutX(rectangles[i][j].getX() + 12);
                labels[i][j].setLayoutY(rectangles[i][j].getY() - 10);

                groups[i][j] = new Group(rectangles[i][j], labels[i][j]);
            }
        }

        HBox[] hBoxes = new HBox[3];
        for (int i = 0; i < hBoxes.length; i++) {

            hBoxes[i] = new HBox();
            for (int j = 0; j < 6; j++) {
                hBoxes[i].getChildren().add(groups[i][j]);
            }

            hBoxes[i].setSpacing(18);
            hBoxes[i].setAlignment(Pos.CENTER);
        }


        VBox vBox1 = new VBox();
        vBox1.getChildren().addAll(hBoxes);
        vBox1.setSpacing(35);
        vBox1.setAlignment(Pos.CENTER);
        vBox1.setPadding(new Insets(80,0,0,0));

        pane.setTop(vBox1);

        btnPlay = new Button("  Play");
        btnPlay.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Create an instance of the info class and set its scene as the scene for the stage
                Levels levels = new Levels();
                levels.start(new Stage());

                Stage MainPageStage = (Stage) btnPlay.getScene().getWindow();
                MainPageStage.close();
            }

        });

        btnHelp = new Button("  Help");
        btnHelp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Create an instance of the info class and set its scene as the scene for the stage
                help help = new help();
                help.start(new Stage());
            }
        });

        btnStyles();

        VBox bHelp = new VBox();
        bHelp.setSpacing(20);
        bHelp.setAlignment(Pos.CENTER);
        bHelp.getChildren().addAll(btnPlay, btnHelp);

        pane.setCenter(bHelp);

        Label lbl2 = new Label("Alpha Version");
        lbl2.setFont(Font.font("Copperplate", FontWeight.BOLD, 20));
        lbl2.setTextFill(Color.WHITE);
        lbl2.setPadding(new Insets(0,10,10,20));
        VBox vboxlbl = new VBox();
        vboxlbl.getChildren().add(lbl2);
        pane.setBottom(vboxlbl);

        return pane;
    }

    private void btnStyles(){

        // 1. Play button
        btnPlay.setPrefSize(150, 50);
        btnPlay.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        ImageView play = new ImageView(new Image("/play.png"));
        play.setFitWidth(30);
        play.setFitHeight(30);
        btnPlay.setGraphic(play);
        btnPlay.setStyle(
                """
                        -fx-background-color: #2D6A4F;
                        -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 10, 0.0, 3, 3);
                        -fx-text-fill: white;
                        """
        );

        btnPlay.setOnMouseEntered(e -> btnPlay.setStyle(
                """
                        -fx-background-color: #550C18;
                        -fx-text-fill: white;
                        """

        ));

        btnPlay.setOnMouseExited(e -> btnPlay.setStyle(
                """
                        -fx-background-color: #2D6A4F;
                        -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 10, 0.0, 3, 3);
                        -fx-text-fill: white;
                        """
        ));

        // 2. Help Button
        btnHelp.setPrefSize(150, 50);
        btnHelp.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        ImageView help = new ImageView(new Image("/info.png"));
        help.setFitWidth(30);
        help.setFitHeight(30);
        btnHelp.setGraphic(help);

        btnHelp.setStyle(
                """
                        -fx-background-color: #550C18;
                        -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 10, 0.0, 3, 3);
                        -fx-text-fill: white;
                        """
        );

        btnHelp.setOnMouseEntered(e -> {
            btnHelp.setStyle(
                    """
                            -fx-background-color: #2D6A4F;
                            -fx-text-fill: white;
                            """

            );
        });

        btnHelp.setOnMouseExited(e -> {
            btnHelp.setStyle(
                    """
                            -fx-background-color: #550C18;
                            -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 10, 0.0, 3, 3);
                            -fx-text-fill: white;
                            """
            );
        });

    }

    public void playSound(boolean Sound){
        setSound(Sound);
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    new File("/Users/janaabuhantash/IdeaProjects/trial/src/main/resources/sound.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            if (Sound)
                clip.start();
            else
                clip.stop();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean getSound() {
        return Sound;
    }

    public void setSound(boolean Sound) {
        this.Sound = Sound;
    }


    @Override
    public void start(Stage stage) {

        Scene scene = new Scene(getPane(), 700, 1000);

        stage.setTitle("The Wordle Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

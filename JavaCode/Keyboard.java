package com.example.WordleGame;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.Arrays;

public class Keyboard extends Boxes {

    private static final char[] BUTTON_TEXT = {
            'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P',
            'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L',
            'Z', 'X', 'C', 'V', 'B', 'N', 'M'
    };

    protected Button[] buttons;
    private Button enterButton;
    private Button backspaceButton;

    public Keyboard(int rows, int cols) {
        super(rows, cols);
    }

    protected BorderPane getPane() {

        BorderPane boxesPane = super.getPane();
        BorderPane keyboardPane = new BorderPane();

        HBox hBox1 = new HBox();
        HBox hBox2 = new HBox();
        HBox hBox3 = new HBox();
        HBox hBox4 = new HBox();

        enterButton = new Button("Enter");
        backspaceButton = new Button();

        buttons = new Button[BUTTON_TEXT.length];

        for (int i = 0; i < indices.length; i++) {
            // Check if the current row is not full
            if (indices[i] < labels[i].length) {
                // Set the row index to the current row
                rowNum = i;
                break;
            }
        }

        // Check if there is an available row
        if (rowNum != -1 && rowNum < super.getRows()) {

            for (int i = 0; i < BUTTON_TEXT.length; i++) {

                char buttonText = BUTTON_TEXT[i];
                Button button = new Button(String.valueOf(buttonText));
                button.setPrefSize(45, 45);
                buttons[i] = button;

                // 1. Add an event handler to the clicked button
                button.setOnAction(buttonEvent -> {
                    playSound();
                    // Get the button that was clicked
                    Button clickedButton = (Button) buttonEvent.getSource();
                    // Get the text of the button that was clicked
                    String buttonText1 = clickedButton.getText();

                    if (indices[rowNum] < labels[rowNum].length) {
                        setLblText(buttonText1);
                    }

                });

                // 2. The enter button is pressed and the rowNum is full
                enterButton.setOnAction(enterEvent -> {
                    playSound();
                    if (indices[rowNum] == labels[rowNum].length) {
                        enter();
                    }
                });

                // 3. Decrement the index of the current rowNum if the backspace button is pressed
                backspaceButton.setOnAction(bsEvent -> {
                    playSound();
                    if (indices[rowNum] > 0) {
                        backspace();
                    }
                });
            }
        }

        hBox1.getChildren().addAll(Arrays.copyOfRange(buttons, 0, 10));
        hBox1.setSpacing(10);
        hBox1.setAlignment(Pos.CENTER);

        hBox2.getChildren().addAll(Arrays.copyOfRange(buttons, 10, 19));
        hBox2.setSpacing(10);
        hBox2.setAlignment(Pos.CENTER);

        hBox3.getChildren().addAll(Arrays.copyOfRange(buttons, 19, 26));
        hBox3.setSpacing(10);
        hBox3.setAlignment(Pos.CENTER);

        hBox4.getChildren().addAll(enterButton, hBox3, backspaceButton);
        hBox4.setAlignment(Pos.CENTER);
        hBox4.setSpacing(10);

        btnStyles();

        Label lbl1 = new Label("                  ");
        VBox keyboard = new VBox();
        keyboard.getChildren().addAll(hBox1, hBox2, hBox4, lbl1);
        keyboard.setAlignment(Pos.CENTER);
        keyboard.setPadding(new Insets(10, 10, 10, 10));
        keyboard.setSpacing(15);

        keyboardPane.setCenter(keyboard);
        boxesPane.setBottom(keyboardPane);

        return boxesPane;
    }

    private void playSound(){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    new File("/Users/janaabuhantash/IdeaProjects/trial/src/main/resources/click.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void btnStyles(){
        for (Button button : buttons) {

            button.setFont(Font.font("Verdana", FontWeight.BOLD, 15));

            button.setStyle(
                    """
                            -fx-background-color: #2D6A4F;
                            -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 10, 0.0, 3, 3);
                            -fx-text-fill: white;
                            """
            );

            button.setOnMouseEntered(e -> button.setStyle(
                    """
                            -fx-background-color: #004B23;
                            -fx-text-fill: white;
                            """

            ));

            button.setOnMouseExited(e -> button.setStyle(
                    """
                            -fx-background-color: #2D6A4F;
                            -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 10, 0.0, 3, 3);
                            -fx-text-fill: white;
                            """
            ));
        }
        // 2. Backspace button Design
        ImageView delete = new ImageView(new Image("/delete.png"));
        delete.setFitWidth(40);
        delete.setFitHeight(40);
        backspaceButton.setGraphic(delete);
        backspaceButton.setPrefWidth(80);

        backspaceButton.setStyle(
                """
                        -fx-background-color: #2D6A4F;
                        -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 10, 0.0, 3, 3);
                        -fx-text-fill: white;
                        """
        );

        backspaceButton.setOnMouseEntered(e -> backspaceButton.setStyle(
                """
                        -fx-background-color: #550C18;
                        -fx-text-fill: white;
                        """

        ));

        backspaceButton.setOnMouseExited(e -> backspaceButton.setStyle(
                """
                        -fx-background-color: #2D6A4F;
                        -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 10, 0.0, 3, 3);
                        -fx-text-fill: white;
                        """
        ));

        // 3. Enter button Design
        enterButton.setFont(Font.font("Copperplate", FontWeight.BOLD, 18));
        enterButton.setPrefWidth(80);
        enterButton.setPrefHeight(47);

        enterButton.setStyle(
                """
                        -fx-background-color: #2D6A4F;
                        -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 10, 0.0, 3, 3);
                        -fx-text-fill: white;
                        """
        );

        enterButton.setOnMouseEntered(e -> enterButton.setStyle(
                """
                        -fx-background-color: #550C18;
                        -fx-text-fill: white;
                        """

        ));

        enterButton.setOnMouseExited(e -> enterButton.setStyle(
                """
                        -fx-background-color: #2D6A4F;
                        -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 10, 0.0, 3, 3);
                        -fx-text-fill: white;
                        """
        ));

    }

    @Override
    public void start(Stage stage) {

        Scene scene = new Scene(getPane(), 700, 1000);
        scene.setOnKeyPressed(super.getKeyEvent());

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {launch(args);}
}
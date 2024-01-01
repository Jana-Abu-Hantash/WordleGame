package com.example.WordleGame;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.List;
import java.util.Random;

import static javafx.scene.input.KeyCode.BACK_SPACE;
import static javafx.scene.input.KeyCode.ENTER;

public class Boxes extends background {

    protected Label[][] labels;
    protected Rectangle[][] rectangles;
    private final Group[][] groups;

    protected String GuessWord = "";
    protected String Hint;
    protected String guessedWord = "";

    protected int[] indices;
    protected int rowNum = -1;
    private final int rows;
    private final int cols;

    private KeyCode[] alphabet;
    protected EventHandler<KeyEvent> KeyEvent;

    private Button btnLetter;
    private Button btnReset;
    private Button btnHint;
    private Button btnHelp;
    private Button backBtn;

    public Boxes(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        this.labels = new Label[rows][cols];
        this.rectangles = new Rectangle[rows][cols];
        this.groups = new Group[rows][cols];

        // Initialize each element in the labels array with a new Label object
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.labels[i][j] = new Label();
            }
        }

        // Initialize each element in the rectangles array with a new rectangle object
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.rectangles[i][j] = new Rectangle();
            }
        }

        this.indices = new int[cols];
        for (int i = 0; i < cols; i++) {
            this.indices[i] = 0;
        }
    }

    public int getRows(){
        return rows;
    }

    protected BorderPane getPane() {

        BorderPane mainPane = super.getPane();
        BorderPane boxesPane = new BorderPane();

        // Get a list of Word objects from the FileReader
        FileReader reader = new FileReader();
        List<FileReader.Word> words = reader.getWords();

        // Select a random Word object from the list
        FileReader.Word randomWord = words.get(new Random().nextInt(words.size()));
        while (randomWord.getLength() != rows) {
            int randomIndex = new Random().nextInt(words.size());
            randomWord = words.get(randomIndex);
        }

        System.out.println(randomWord.getWord());

        GuessWord = randomWord.getWord();
        Hint = randomWord.getHint();

        alphabet = new KeyCode[26];
        for (int i = 0; i < alphabet.length; i++) {
            alphabet[i] = KeyCode.valueOf(String.valueOf((char) ('A' + i)));
        }

        // Create a 2D array of Rectangle objects and set their properties such as width,
        // height, fill, and stroke color.
        for (int i = 0; i < rectangles.length; i++) {
            for (int j = 0; j < rectangles[i].length; j++) {
                rectangles[i][j] = new Rectangle(50, 50);
                rectangles[i][j].setFill(Color.WHITE);
                rectangles[i][j].setStroke(Color.BLACK);
                rectangles[i][j].setStrokeWidth(3);
            }
        }

        // Create a 2D array of Label objects and set their properties such as font,
        // text, text fill, and layout position relative to the corresponding Rectangle object.
        for (int i = 0; i < labels.length; i++) {
            for (int j = 0; j < labels[i].length; j++) {
                labels[i][j] = new Label();
                labels[i][j].setFont(new Font(40));
                labels[i][j].setText(" ");
                labels[i][j].setTextFill(Color.BLACK);
                labels[i][j].setLayoutX(rectangles[i][j].getX() + 9);
                labels[i][j].setLayoutY(rectangles[i][j].getY());

                groups[i][j] = new Group(rectangles[i][j], labels[i][j]);
            }
        }

        //  Create an array of HBox objects and adds the groups of Rectangle and Label objects to each of them.
        HBox[] hBoxes = new HBox[cols];
        for (int i = 0; i < hBoxes.length; i++) {

            hBoxes[i] = new HBox();
            for (int j = 0; j < hBoxes.length; j++) {
                hBoxes[i].getChildren().add(groups[i][j]);
            }

            hBoxes[i].setSpacing(18);
            hBoxes[i].setAlignment(Pos.CENTER);
        }

        //  Create a VBox object and add all the HBox objects to it.
        VBox vBox1 = new VBox();
        vBox1.getChildren().addAll(hBoxes);
        vBox1.setSpacing(30);
        vBox1.setAlignment(Pos.CENTER);

        // Create the four main buttons

        // 1. Reveal a letter Button
        btnLetter = new Button("letter");
        btnLetter.setOnAction(e -> {
            if (rowNum != -1 && guessedWord.length() > 0) {
                revealLetter();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Try Once At Least");
                alert.setHeaderText(null);

                VBox vbox1 = new VBox();
                Label lbl = new Label("Give it a shot and try playing the game at least once.");
                Font font = new Font("Georgia Bold", 15);
                lbl.setFont(font);

                vbox1.getChildren().add(lbl);
                alert.getDialogPane().setContent(vbox1);
                alert.showAndWait();

            }
        });

        // 2. Reset Button
        btnReset = new Button("Reset");
        btnReset.setOnAction(e -> reset());

        // 3. Hint Button
        btnHint = new Button("HINT");
        btnHint.setOnAction(e -> {
            hint hint = new hint(Hint);
            hint.start(new Stage());
        });

        // 4. Help Button
        btnHelp = new Button("Help");
        btnHelp.setOnAction(e -> {
            help help = new help();
            help.start(new Stage());
        });

        backBtn = new Button("BACK");
        backBtn.setOnAction(e -> {

            MainPage mainPage = new MainPage();
            mainPage.playSound(false);
            mainPage.start(new Stage());

            Stage LevelsStage = (Stage) backBtn.getScene().getWindow();
            LevelsStage.close();
        });

        btnStyles();

        HBox hBox = new HBox();
        hBox.getChildren().addAll(backBtn, btnReset, btnHelp);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(168);

        VBox helpBox = new VBox();
        Label lbl1 = new Label("                  ");
        helpBox.getChildren().addAll(hBox, lbl1);
        helpBox.setAlignment(Pos.CENTER);
        helpBox.setSpacing(10);

        HBox letterBox = new HBox();
        Label lbl2 = new Label("     ");
        letterBox.getChildren().addAll(lbl2, btnLetter);
        letterBox.setAlignment(Pos.TOP_CENTER);
        letterBox.setSpacing(5);

        HBox hintBox = new HBox();
        Label lbl3 = new Label("     ");
        hintBox.getChildren().addAll(btnHint, lbl3);
        hintBox.setAlignment(Pos.TOP_CENTER);
        hintBox.setSpacing(10);

        boxesPane.setRight(hintBox);
        boxesPane.setLeft(letterBox);
        boxesPane.setTop(helpBox);
        boxesPane.setCenter(vBox1);
        mainPane.setCenter(boxesPane);

        return mainPane;
    }

    // This function returns an EventHandler for handling KeyEvents.
    protected EventHandler<KeyEvent> getKeyEvent() {
        if (KeyEvent == null) {
            KeyEvent = e -> {
                KeyCode code = e.getCode();

                // Initialize the rowNum index to -1
                for (int i = 0; i < indices.length; i++) {
                    // Check if the current row is not full
                    if (indices[i] != labels[i].length + 1) {
                        // Set the row index to the current row
                        rowNum = i;
                        break;
                    }
                }

                // Check if there is an available row
                if (rowNum != -1 && rowNum < rows) {
                    // 1. Decrement the index of the current rowNum if the backspace key is pressed
                    if (code == BACK_SPACE && indices[rowNum] > 0) {
                        backspace();
                    }

                    // 2. Check if the enter key is pressed and the rowNum is full
                    else if (code == ENTER && indices[rowNum] == labels[rowNum].length) {
                        enter();
                    }

                    // 3. Check if a letter key is pressed and the rowNum is not full
                    else if (code.isLetterKey() && indices[rowNum] < labels[rowNum].length) {
                        for (KeyCode letter : alphabet) {
                            if (code == letter) {
                                // Set the text of the label at the current index to the letter
                                String letterStr = letter.getName();
                                setLblText(letterStr);
                                break;
                            }
                        }
                    }
                }
            };
        }

        return KeyEvent;
    }

    // This function resets the game state.
    public void reset() {

        // Get a list of Word objects from the FileReader
        FileReader reader = new FileReader();
        List<FileReader.Word> words = reader.getWords();

        // Select a random Word object from the list
        FileReader.Word randomWord = words.get(new Random().nextInt(words.size()));
        while (randomWord.getLength() != rows) {
            int randomIndex = new Random().nextInt(words.size());
            randomWord = words.get(randomIndex);
        }

        System.out.println(randomWord.getWord());

        GuessWord = randomWord.getWord();
        Hint = randomWord.getHint();

        // Clear the guessed word, reset the row index
        guessedWord = "";
        rowNum = 0;

        // Reset the label texts and colors
        for (int i = 0; i < labels.length; i++) {
            for (int j = 0; j < labels[i].length; j++) {
                labels[i][j].setTextFill(Color.BLACK);
                labels[i][j].setText(" ");
            }
        }

        // Reset the rectangle colors and strokes
        for (int i = 0; i < rectangles.length; i++) {
            for (int j = 0; j < rectangles[i].length; j++) {
                rectangles[i][j].setFill(Color.WHITE);
                rectangles[i][j].setStroke(Color.BLACK);
            }
        }

        // Reset the indices to 0
        for (int i = 0; i < rectangles.length; i++) {
            indices[i] = 0;
        }
    }

    protected void backspace(){
        indices[rowNum]--;
        // Clear the text of the label at the current rowNum and index
        labels[rowNum][indices[rowNum]].setText(" ");
    }

    protected void enter(){

        for (int i = 0; i < labels[rowNum].length; i++) {
            // Get the character at the current label
            char letter = labels[rowNum][i].getText().charAt(0);

            // Check if the character matches the corresponding character in the secret word
            if (letter == GuessWord.toUpperCase().charAt(i)) {
                rectangles[rowNum][i].setFill(Color.web("#5B8C5A")); // green
                labels[rowNum][i].setTextFill(Color.WHITE);
            } else {

                for (int j = 0; j < GuessWord.length(); j++) {
                    // Check if the character matches any character in the secret word
                    if (letter == GuessWord.toUpperCase().charAt(j)) {
                        rectangles[rowNum][i].setFill(Color.web("#FFA400")); //orange
                        break;
                    } else {
                        rectangles[rowNum][i].setFill(Color.web("#9BA7C0")); // grey
                    }
                }
            }
        }

        guessedWord = "";
        for (int i = 0; i < indices.length; i++) {
            guessedWord += labels[rowNum][i].getText();
        }

        // Check if the guessed word is correct
        if (guessedWord.equalsIgnoreCase(GuessWord)) {
            win winMessage = new win();
            winMessage.start(new Stage());
            reset();
            return;
        }

        if (rowNum == rows-1 && !guessedWord.equalsIgnoreCase(GuessWord)) {
            lost lostMessage = new lost(GuessWord);
            lostMessage.start(new Stage());
            reset();
            return;
        }

        // Increment the index of the current rowNum
        indices[rowNum]++;
        rowNum++;

    }

    // This function sets the text of the current label with the given letter
    // and increments the index of the current row.
    protected void setLblText(String letter){
        labels[rowNum][indices[rowNum]].setText(letter);
        indices[rowNum]++;
    }

    // This function reveals the next letter of the guess word.
    private void revealLetter(){
        for (int i = 0; i < GuessWord.length(); i++) {
            if (GuessWord.toUpperCase().charAt(i) != guessedWord.charAt(i)) {

                // Set the text of the corresponding label to the correct letter
                labels[rowNum][i].setText(String.valueOf(GuessWord.toUpperCase().charAt(i)));
                labels[rowNum][i].setTextFill(Color.WHITE);
                // Change the color of the corresponding rectangle
                rectangles[rowNum][i].setFill(Color.web("#5B8C5A")); //Green

                // Increment the index of the current row
                if (i > 0)
                    indices[rowNum] = 0;
                else
                    indices[rowNum]++;
                break;
            }
        }
    }

    private void btnStyles(){

        // 1. Reveal a letter Button
        btnLetter.setStyle(
                """
                    -fx-background-color: #004B23;
                    -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 10, 0.0, 3, 3);
                    -fx-text-fill: white;
                      """
        );

        btnLetter.setOnMouseEntered(e -> btnLetter.setStyle(
                """
                    -fx-background-color: #550C18;
                    -fx-text-fill: white;
                    """
        ));

        btnLetter.setOnMouseExited(e -> btnLetter.setStyle(
                """
                     -fx-background-color: #004B23;
                     -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 10, 0.0, 3, 3);
                     -fx-text-fill: white;
                     """
        ));


        // 2. Reset Button
        btnReset.setStyle(
                """
                    -fx-background-color: #550C18;
                    -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 10, 0.0, 3, 3);
                    -fx-text-fill: white;
                    """
        );

        btnReset.setOnMouseEntered(e -> btnReset.setStyle(
                """
                    -fx-background-color: #004B23;
                    -fx-text-fill: white;
                    """

        ));

        btnReset.setOnMouseExited(e -> btnReset.setStyle(
                """
                        -fx-background-color: #550C18;
                        -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 10, 0.0, 3, 3);
                        -fx-text-fill: white;
                        """
        ));

        // 3. Hint Button
        btnHint.setStyle(
                """
                        -fx-background-color: #004B23;
                        -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 10, 0.0, 3, 3);
                        -fx-text-fill: white;
                        """
        );
        btnHint.setOnMouseEntered(e -> btnHint.setStyle(
                """
                        -fx-background-color: #550C18;
                        -fx-text-fill: white;
                        """
        ));
        btnHint.setOnMouseExited(e -> btnHint.setStyle(
                """
                        -fx-background-color: #004B23;
                        -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 10, 0.0, 3, 3);
                        -fx-text-fill: white;
                        """
        ));

        // 4. Help Button
        btnHelp.setStyle(
                """
                        -fx-background-color: #550C18;
                        -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 10, 0.0, 3, 3);
                        -fx-text-fill: white;
                        """
        );

        btnHelp.setOnMouseEntered(e -> btnHelp.setStyle(
                """
                        -fx-background-color: #004B23;
                        -fx-text-fill: white;
                        """

        ));

        btnHelp.setOnMouseExited(e -> btnHelp.setStyle(
                """
                        -fx-background-color: #550C18;
                        -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 10, 0.0, 3, 3);
                        -fx-text-fill: white;
                        """
        ));

        // 5. Back Button
        backBtn.setPrefSize(116, 30);
        backBtn.setStyle(
                """
                        -fx-background-color: #550C18;
                        -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 10, 0.0, 3, 3);
                        -fx-text-fill: white;
                        """
        );

        backBtn.setOnMouseEntered(e -> {
            backBtn.setStyle(
                    """
                            -fx-background-color: #2D6A4F;
                            -fx-text-fill: white;
                            """

            );
        });

        backBtn.setOnMouseExited(e -> {
            backBtn.setStyle(
                    """
                            -fx-background-color: #550C18;
                            -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 10, 0.0, 3, 3);
                            -fx-text-fill: white;
                            """
            );
        });

        // buttons fonts
        Font font = Font.font("Copperplate", FontWeight.BOLD, 20);
        btnLetter.setFont(font);
        btnReset.setFont(font);
        btnHint.setFont(font);
        btnHelp.setFont(font);
        backBtn.setFont(font);

        // Images for the buttons
        ImageView search = new ImageView(new Image("/search.png"));
        search.setFitWidth(20);
        search.setFitHeight(20);
        btnLetter.setGraphic(search);

        ImageView reset = new ImageView(new Image("/reset.png"));
        reset.setFitWidth(20);
        reset.setFitHeight(20);
        btnReset.setGraphic(reset);

        ImageView hint = new ImageView(new Image("/btnHint.png"));
        hint.setFitWidth(20);
        hint.setFitHeight(20);
        btnHint.setGraphic(hint);

        ImageView help = new ImageView(new Image("/info.png"));
        help.setFitWidth(20);
        help.setFitHeight(20);
        btnHelp.setGraphic(help);

        ImageView back = new ImageView(new Image("/back.png"));
        back.setFitWidth(20);
        back.setFitHeight(20);
        backBtn.setGraphic(back);
    }

    @Override
    public void start(Stage stage) {

        Scene scene = new Scene(getPane(), 700, 1000);
        scene.setOnKeyPressed(getKeyEvent());

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
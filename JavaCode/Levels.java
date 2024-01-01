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


public class Levels extends Application {
    private Button btnEasy;
    private Button btnInter;
    private Button btnAd;
    private Button backBtn;
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

        // 1. Easy Button
        btnEasy = new Button("Easy");
        btnEasy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Create an instance of the info class and set its scene as the scene for the stage
                Keyboard game = new Keyboard(4,4);
                game.start(new Stage());

                Stage LevelsStage = (Stage) btnEasy.getScene().getWindow();
                LevelsStage.close();

            }

        });

        // 2. Intermediate Button
        btnInter = new Button("Intermediate");
        btnInter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Create an instance of the info class and set its scene as the scene for the stage
                Keyboard game = new Keyboard(5,5);
                game.start(new Stage());

                Stage LevelsStage = (Stage) btnInter.getScene().getWindow();
                LevelsStage.close();

            }

        });

        // 3. Advanced Button
        btnAd = new Button("Advanced");
        btnAd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Create an instance of the info class and set its scene as the scene for the stage
                Keyboard game = new Keyboard(6,6);
                game.start(new Stage());
                Stage LevelsStage = (Stage) btnAd.getScene().getWindow();
                LevelsStage.close();

            }

        });

        // 4. Back Button
        backBtn = new Button("BACK");
        backBtn.setOnAction(e -> {

            MainPage mainPage = new MainPage();
            mainPage.playSound(false);
            mainPage.start(new Stage());

            Stage levelsStage = (Stage) backBtn.getScene().getWindow();
            levelsStage.close();
        });

        // 5. Help Button
        btnHelp = new Button("HELP");
        btnHelp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Create an instance of the info class and set its scene as the scene for the stage
                help help = new help();
                help.start(new Stage());
            }
        });

        btnStyles();

        Label lbl2 = new Label("Alpha Version");
        lbl2.setFont(Font.font("Copperplate", FontWeight.BOLD, 20));
        lbl2.setTextFill(Color.WHITE);
        lbl2.setPadding(new Insets(0,10,10,20));

        HBox hBox = new HBox();
        hBox.setSpacing(300);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(backBtn, btnHelp);

        VBox bHelp = new VBox();
        bHelp.setSpacing(20);
        bHelp.setAlignment(Pos.CENTER);
        bHelp.getChildren().addAll(btnEasy, btnInter, btnAd, hBox);

        pane.setCenter(bHelp);

        VBox vbox2 = new VBox();
        vbox2.getChildren().addAll(lbl2);
        pane.setBottom(vbox2);

        return pane;
    }

    private void btnStyles() {

        // 1. Easy button
        btnEasy.setPrefSize(180, 50);
        btnEasy.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        btnEasy.setStyle(
                """
                        -fx-background-color: #2D6A4F;
                        -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 10, 0.0, 3, 3);
                        -fx-text-fill: white;
                        """
        );

        btnEasy.setOnMouseEntered(e -> {
            btnEasy.setStyle(
                    """
                            -fx-background-color: #9BA7C0;
                            -fx-text-fill: black;
                            """

            );
        });

        btnEasy.setOnMouseExited(e -> {
            btnEasy.setStyle(
                    """
                            -fx-background-color: #2D6A4F;
                            -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 10, 0.0, 3, 3);
                            -fx-text-fill: white;
                            """
            );
        });

        // 2. Intermediate Button
        btnInter.setPrefSize(180, 50);
        btnInter.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        btnInter.setStyle(
                """
                        -fx-background-color: #2D6A4F;
                        -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 10, 0.0, 3, 3);
                        -fx-text-fill: white;
                        """
        );

        btnInter.setOnMouseEntered(e -> {
            btnInter.setStyle(
                    """
                            -fx-background-color: #FFA400;
                            -fx-text-fill: black;
                            """

            );
        });

        btnInter.setOnMouseExited(e -> {
            btnInter.setStyle(
                    """
                            -fx-background-color: #2D6A4F;
                            -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 10, 0.0, 3, 3);
                            -fx-text-fill: white;
                            """
            );
        });


        // 3. Advanced Button
        btnAd.setPrefSize(180, 50);
        btnAd.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        btnAd.setStyle(
                """
                        -fx-background-color: #2D6A4F;
                        -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 10, 0.0, 3, 3);
                        -fx-text-fill: white;
                        """
        );

        btnAd.setOnMouseEntered(e -> {
            btnAd.setStyle(
                    """
                            -fx-background-color: #550C18;
                            -fx-text-fill: white;
                            """

            );
        });

        btnAd.setOnMouseExited(e -> {
            btnAd.setStyle(
                    """
                            -fx-background-color: #2D6A4F;
                            -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 10, 0.0, 3, 3);
                            -fx-text-fill: white;
                            """
            );
        });

        //4. Back Button
        backBtn.setPrefSize(150, 50);
        backBtn.setFont(Font.font("Copperplate", FontWeight.BOLD, 20));

        ImageView back = new ImageView(new Image("/back.png"));
        back.setFitWidth(30);
        back.setFitHeight(30);
        backBtn.setGraphic(back);

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

        // 5. Help Button
        btnHelp.setPrefSize(150, 50);
        btnHelp.setFont(Font.font("Copperplate", FontWeight.BOLD, 20));


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

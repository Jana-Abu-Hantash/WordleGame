package com.example.WordleGame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class help extends Application {

    @Override
    public void start(Stage stage) {
        BorderPane pane = new BorderPane();
        String css = this.getClass().getResource("/application.css").toExternalForm();


        ImageView back = new ImageView(new Image("/back.png"));
        back.setFitWidth(20);
        back.setFitHeight(20);

        Button backBtn = new Button("BACK");
        backBtn.setPrefSize(140, 40);
        backBtn.setFont(Font.font("Copperplate", FontWeight.BOLD, 20));
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

        backBtn.setOnAction(e -> {
            stage.close();
        });

        HBox HBoxback = new HBox();
        HBoxback.getChildren().add(backBtn);
        HBoxback.setPadding(new Insets(0,10,10,20));
        pane.setBottom(HBoxback);

        Label lbl1 = new Label("How To Play");
        lbl1.setLayoutY(35.0);
        lbl1.setPrefHeight(62.0);
        lbl1.setPrefWidth(381.0);
        Font font = new Font("Georgia Bold", 36.0);
        lbl1.setFont(font);

        Label label2 = new Label("Guess the wordle in 6 tries.");
        label2.setLayoutY(89.0);
        label2.setPrefHeight(35.0);
        label2.setPrefWidth(314.0);
        Font font0 = new Font(24.0);
        label2.setFont(font0);

        Text txt1 = new Text("•  Each guess must be a valid 5-letter word.");
        txt1.setStrokeWidth(0.0);
        txt1.setWrappingWidth(472.0);
        txt1.setFill(Color.WHITE);
        Font font18 = new Font(18.0);
        txt1.setFont(font18);

        Text txt2 = new Text("•  The color of the tiles will change to show how close your guess was to the word.");
        txt2.setStrokeWidth(0.0);
        txt2.setWrappingWidth(541.0);
        txt2.setFill(Color.WHITE);
        txt2.setFont((font18));

        Label label3 = new Label("Examples");
        label3.setLayoutX(100);
        label3.setPrefHeight(35.0);
        label3.setPrefWidth(191.0);
        Font font1 = new Font("Calibri Bold", 24.0);
        label3.setFont(font1);
        label3.setPadding(new Insets(20, 0, 0, 0));

        double squareSize = 45;

        Rectangle square1 = new Rectangle(squareSize, squareSize);
        Rectangle square2 = new Rectangle(squareSize, squareSize);
        Rectangle square3 = new Rectangle(squareSize, squareSize);
        Rectangle square4 = new Rectangle(squareSize, squareSize);
        Rectangle square5 = new Rectangle(squareSize, squareSize);

        square1.setFill(Color.web("#5B8C5A"));

        square1.setStroke(Color.web("#5B8C5A"));
        square1.setStrokeWidth(3);
        square2.setStroke(Color.web("#9BA7C0"));
        square2.setStrokeWidth(3);
        square3.setStroke(Color.web("#9BA7C0"));
        square3.setStrokeWidth(3);
        square4.setStroke(Color.web("#9BA7C0"));
        square4.setStrokeWidth(3);
        square5.setStroke(Color.web("#9BA7C0"));
        square5.setStrokeWidth(3);

        Font fontw = Font.font("Calibri Bold", 30);
        Font fonte = Font.font("Calibri", 30);

        Text txtw = new Text("W");
        txtw.setFont(fontw);
        txtw.setFill(Color.WHITE);
        StackPane stack1 = new StackPane();
        stack1.getChildren().addAll(square1, txtw);

        Text txte = new Text("E");
        txte.setFont(fonte);
        txte.setFill(Color.WHITE);
        StackPane stack2 = new StackPane();
        stack2.getChildren().addAll(square2, txte);

        Text txta = new Text("A");
        txta.setFont(fonte);
        txta.setFill(Color.WHITE);
        StackPane stack3 = new StackPane();
        stack3.getChildren().addAll(square3, txta);

        Text txtr = new Text("R");
        txtr.setFont(fonte);
        txtr.setFill(Color.WHITE);
        StackPane stack4 = new StackPane();
        stack4.getChildren().addAll(square4, txtr);

        Text txty = new Text("Y");
        txty.setFont(fonte);
        txty.setFill(Color.WHITE);
        StackPane stack5 = new StackPane();
        stack5.getChildren().addAll(square5, txty);

        HBox hBoxs1 = new HBox();
        hBoxs1.setPadding(new Insets(10, 0, 0, 0));
        hBoxs1.setSpacing(5);
        hBoxs1.getChildren().addAll(stack1, stack2, stack3, stack4, stack5);

        Label label4 = new Label("W");
        label4.setPrefHeight(50.0);
        label4.setPrefWidth(25.0);
        Font font3 = new Font("System Bold", 18.0);
        label4.setFont(font3);

        Label label5 = new Label("is in the word and in the correct spot.");
        label5.setPrefHeight(50.0);
        label5.setPrefWidth(374.0);
        label5.setFont(font18);

        HBox hBox1 = new HBox();
        hBox1.setPadding(new Insets(0, 0, 0, 0));
        hBox1.getChildren().addAll(label4, label5);

        Rectangle square6 = new Rectangle(squareSize, squareSize);
        Rectangle square7 = new Rectangle(squareSize, squareSize);
        Rectangle square8 = new Rectangle(squareSize, squareSize);
        Rectangle square9 = new Rectangle(squareSize, squareSize);
        Rectangle square10 = new Rectangle(squareSize, squareSize);

        square7.setFill(Color.web("#FFA400"));

        square6.setStroke(Color.web("#9BA7C0"));
        square6.setStrokeWidth(3);
        square7.setStroke(Color.web("#FFA400"));
        square7.setStrokeWidth(3);
        square8.setStroke(Color.web("#9BA7C0"));
        square8.setStrokeWidth(3);
        square9.setStroke(Color.web("#9BA7C0"));
        square9.setStrokeWidth(3);
        square10.setStroke(Color.web("#9BA7C0"));
        square10.setStrokeWidth(3);

        Text txtp = new Text("P");
        txtp.setFont(fontw);
        txtp.setFill(Color.WHITE);
        StackPane stack6 = new StackPane();
        stack6.getChildren().addAll(square6, txtp);

        Text txti = new Text("I");
        txti.setFont(fontw);
        txti.setFill(Color.WHITE);
        StackPane stack7 = new StackPane();
        stack7.getChildren().addAll(square7, txti);

        Text txtl = new Text("L");
        txtl.setFont(fonte);
        txtl.setFill(Color.WHITE);
        StackPane stack8 = new StackPane();
        stack8.getChildren().addAll(square8, txtl);

        Text txtll = new Text("L");
        txtll.setFont(fonte);
        txtll.setFill(Color.WHITE);
        StackPane stack9 = new StackPane();
        stack9.getChildren().addAll(square9, txtll);

        Text txts = new Text("S");
        txts.setFont(fonte);
        txts.setFill(Color.WHITE);
        StackPane stack10 = new StackPane();
        stack10.getChildren().addAll(square10, txts);

        HBox hBoxs2 = new HBox();
        hBoxs2.setPadding(new Insets(10, 0, 0, 0));
        hBoxs2.setSpacing(5);
        hBoxs2.getChildren().addAll(stack6, stack7, stack8, stack9, stack10);

        Label label6 = new Label("I");
        label6.setPrefHeight(50.0);
        label6.setPrefWidth(15.0);
        label6.setFont(font3);

        Label label7 = new Label("is in the word but in the wrong spot.");
        label7.setPrefHeight(50.0);
        label7.setPrefWidth(374.0);
        label7.setFont(font18);

        HBox hBox3 = new HBox();
        hBox3.setPadding(new Insets(0, 0, 0, 0));
        hBox3.getChildren().addAll(label6, label7);

        Rectangle square11 = new Rectangle(squareSize, squareSize);
        Rectangle square12 = new Rectangle(squareSize, squareSize);
        Rectangle square13 = new Rectangle(squareSize, squareSize);
        Rectangle square14 = new Rectangle(squareSize, squareSize);
        Rectangle square15 = new Rectangle(squareSize, squareSize);

        square14.setFill(Color.web("#9BA7C0"));

        square11.setStroke(Color.web("#9BA7C0"));
        square11.setStrokeWidth(3);
        square12.setStroke(Color.web("#9BA7C0"));
        square12.setStrokeWidth(3);
        square13.setStroke(Color.web("#9BA7C0"));
        square13.setStrokeWidth(3);
        square14.setStroke(Color.web("#9BA7C0"));
        square14.setStrokeWidth(3);
        square15.setStroke(Color.web("#9BA7C0"));
        square15.setStrokeWidth(3);

        Text txtv = new Text("V");
        txtv.setFont(fontw);
        txtv.setFill(Color.WHITE);
        StackPane stack11 = new StackPane();
        stack11.getChildren().addAll(square11, txtv);

        Text txtaa = new Text("A");
        txtaa.setFont(fontw);
        txtaa.setFill(Color.WHITE);
        StackPane stack12 = new StackPane();
        stack12.getChildren().addAll(square12, txtaa);

        Text txtg = new Text("G");
        txtg.setFont(fonte);
        txtg.setFill(Color.WHITE);
        StackPane stack13 = new StackPane();
        stack13.getChildren().addAll(square13, txtg);

        Text txtu = new Text("U");
        txtu.setFont(fontw);
        txtu.setFill(Color.BLACK);
        StackPane stack14 = new StackPane();
        stack14.getChildren().addAll(square14, txtu);

        Text txtee = new Text("E");
        txtee.setFont(fonte);
        txtee.setFill(Color.WHITE);
        StackPane stack15 = new StackPane();
        stack15.getChildren().addAll(square15, txtee);

        HBox hBoxs3 = new HBox();
        hBoxs3.setPadding(new Insets(10, 0, 0, 0));
        hBoxs3.setSpacing(5);
        hBoxs3.getChildren().addAll(stack11, stack12, stack13, stack14, stack15);

        Label label8 = new Label("U");
        label8.setPrefHeight(50.0);
        label8.setPrefWidth(20.0);
        label8.setFont(font3);

        Label label9 = new Label("is not in the word in any spot.");
        label9.setPrefHeight(50.0);
        label9.setPrefWidth(374.0);
        label9.setFont(font18);

        HBox hBox5 = new HBox();
        hBox5.setPadding(new Insets(0, 0, 0, 0));
        hBox5.getChildren().addAll(label8, label9);

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(30, 30, 30, 30));
        vBox.getChildren().addAll(lbl1, label2, txt1, txt2, label3, hBoxs1, hBox1, hBoxs2, hBox3, hBoxs3, hBox5);

        Line line = new Line(500, 0, 0, 0);
        line.setStroke(Color.WHITE);
        line.setStrokeWidth(2.5);

        HBox hbox6 = new HBox();
        hbox6.setPadding(new Insets(10, 10, 10, 0));
        hbox6.getChildren().add(line);

        Label lbl10 = new Label(" Press on the hint icon to get clues about the word.");
        lbl10.setFont(font18);

        Button btnHint = new Button("Help");
        btnHint.setFont(Font.font("Copperplate", FontWeight.BOLD, 20));

        ImageView hint = new ImageView(new Image("/btnHint.png"));
        hint.setFitWidth(20);
        hint.setFitHeight(20);
        btnHint.setGraphic(hint);

        btnHint.setStyle(
                """
                        -fx-background-color: #2D6A4F;
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
                        -fx-background-color: #2D6A4F;
                        -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 10, 0.0, 3, 3);
                        -fx-text-fill: white;
                        """
        ));

        HBox hbox7 = new HBox();
        hbox7.getChildren().addAll(btnHint, lbl10);
        hbox7.setPadding(new Insets(25, 25, 15, 0));
        hbox7.setSpacing(10);

        Label lbl11 = new Label("   Press on the reveal a letter button to reveal a letter.");
        lbl11.setFont(font18);

        Button btnLetter = new Button("Letter");
        btnLetter.setFont(Font.font("Copperplate", FontWeight.BOLD, 20));

        ImageView search = new ImageView(new Image("/search.png"));
        search.setFitWidth(20);
        search.setFitHeight(20);
        btnLetter.setGraphic(search);

        btnLetter.setStyle(
                """
                        -fx-background-color: #2D6A4F;
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
                        -fx-background-color: #2D6A4F;
                        -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 10, 0.0, 3, 3);
                        -fx-text-fill: white;
                        """
        ));

        HBox hbox8 = new HBox();
        hbox8.getChildren().addAll(btnLetter, lbl11);
        hbox8.setPadding(new Insets(15, 15, 15, 0));

        VBox vBox2 = new VBox();
        vBox2.setPadding(new Insets(30, 30, 30, 30));
        vBox2.getChildren().addAll(lbl1, label2, txt1, txt2, label3, hBoxs1, hBox1, hBoxs2, hBox3, hBoxs3, hBox5, hbox6, hbox7, hbox8);
        pane.setCenter(vBox2);

        pane.setCenter(vBox2);
        Scene scene = new Scene(pane, 700, 900);
        stage.setX(408);
        stage.setY(170);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

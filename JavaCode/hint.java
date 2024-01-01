package com.example.WordleGame;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class hint extends Application {
    private final String hint;

    public hint(String h){
        hint = h;
    }

    @Override
    public void start(Stage stage) {

        BorderPane pane = new BorderPane();
        pane.setStyle("-fx-background-color: #001233;");

        ImageView w = new ImageView(new Image("/hint.png"));
        w.setFitWidth(110);
        w.setFitHeight(95);

        Line line = new Line(0, 100, 409, 100);
        line.setStroke(Color.WHITE);
        line.setStrokeWidth(5);

        VBox vbox1 = new VBox();
        vbox1.setAlignment(Pos.CENTER);
        vbox1.getChildren().addAll(w, line);
        vbox1.setStyle("-fx-background-color: #023E7D;");

        Label lbl = new Label(hint);
        Font font = new Font("Georgia Bold", 15);
        lbl.setAlignment(Pos.CENTER);
        lbl.setTextFill(Color.WHITE);
        lbl.setWrapText(true);
        lbl.setMaxWidth(350);
        lbl.setFont(font);

        VBox vbox2 = new VBox();
        vbox2.setAlignment(Pos.CENTER);
        vbox2.setSpacing(15);
        vbox2.getChildren().addAll(lbl);

        pane.setCenter(vbox2);
        pane.setTop(vbox1);

        Scene scene = new Scene(pane, 409, 220);
        stage.setTitle("Hint");
        stage.setScene(scene);

        stage.setX(1200);
        stage.setY(10);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

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
import javafx.stage.Stage;


public class background extends Application {

    protected BorderPane getPane() {
        BorderPane pane = new BorderPane();
        pane.setStyle("-fx-background-color: #232528;");

        ImageView w = new ImageView(new Image("/wordle.png"));
        w.setFitWidth(200);

        Line line = new Line(0, w.getFitHeight(), 700, w.getFitHeight());
        line.setStroke(Color.WHITE);
        line.setStrokeWidth(5);

        Label lbl1 = new Label("                                          ");

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(w, line);
        vbox.setStyle("-fx-background-color: #5B8C5A;");

        VBox vbox1 = new VBox();
        vbox1.setAlignment(Pos.CENTER);
        vbox1.getChildren().addAll(vbox, lbl1);

        pane.setTop(vbox1);
        return pane;
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

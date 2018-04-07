// Author: Patrick Fief
// Course: Java Programming
// Set 3 Program 4
// References: https://docs.oracle.com/javase/8/javafx/api/allclasses-noframe.html

import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.*;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.Group;

public class EmotionalHeartPF extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        final int size = 500;
        
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, size, size);
        VBox btnBox = new VBox(10);
        btnBox.setAlignment(Pos.CENTER);
        btnBox.setMinWidth(size / 5);
        
        HBox holderText = new HBox();
        holderText.setPrefHeight(size / 5);
        holderText.setAlignment(Pos.TOP_CENTER);
        
        Text emotionText = new Text("Click a button");
        emotionText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        emotionText.setTextAlignment(TextAlignment.CENTER);
        holderText.getChildren().addAll(emotionText);
        
        createButtons(btnBox, emotionText);
        
        //TODO: Draw a Heart
        
        root.setLeft(btnBox);
        BorderPane.setAlignment(holderText, Pos.CENTER);
        root.setBottom(holderText);
        
        //Scene creation with root
        primaryStage.setScene(scene);
        primaryStage.setTitle("Emotional Heart");
        primaryStage.show();
    }
    
    //TODO: Remove color change, because gross black text
    //TODO: After drawing the heart figure out how to swap its color
    private void createButtons(Pane container, Text text) {
        Button red = new Button("Red");
        red.setOnAction((ActionEvent e) -> {
            text.setFill(Color.RED);
            text.setText("Love & Anger");
            //TODO Draw Heart
        });
        
        Button orange = new Button("Orange");
        orange.setOnAction((ActionEvent e) -> {
            text.setFill(Color.ORANGE);
            text.setText("Pride & Shame");
            //TODO Draw Heart
        });
        
        Button yellow = new Button("Yellow");
        yellow.setOnAction((ActionEvent e) -> {
            text.setFill(Color.YELLOW);
            text.setText("Joy & Grief");
            //TODO Draw Heart
        });
        
        Button green = new Button("Green");
        green.setOnAction((ActionEvent e) -> {
            text.setFill(Color.GREEN);
            text.setText("Trust & Fear");
            //TODO Draw Heart
        });
        
        Button blue = new Button("Blue");
        blue.setOnAction((ActionEvent e) -> {
            text.setFill(Color.BLUE);
            text.setText("Peace & Hostility");
            //TODO Draw Heart
        });
        
        Button indigo = new Button("Indigo");
        indigo.setOnAction((ActionEvent e) -> {
            text.setFill(Color.INDIGO);
            text.setText("Admiration & Disgust");
            //TODO Draw Heart
        });
        
        Button violet = new Button("Violet");
        violet.setOnAction((ActionEvent e) -> {
            text.setFill(Color.VIOLET);
            text.setText("Surprise & Boredom");
            //TODO Draw Heart
        });
        
        container.getChildren().addAll(red, orange, yellow, green, blue, indigo, violet);
    }
    
}
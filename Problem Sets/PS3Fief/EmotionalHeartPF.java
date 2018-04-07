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
        
        //Container setup
        GridPane root = new GridPane();
        Scene scene = new Scene(root, size, size);
        VBox btnBox = new VBox(10);
        btnBox.setAlignment(Pos.CENTER);
        btnBox.setMinWidth(size / 5);
        
        final Canvas canvas = new Canvas(size * .75,size * .75);
        
        //Text setup
        HBox holderText = new HBox();
        holderText.setPrefHeight(size / 5);
        holderText.setAlignment(Pos.CENTER);
        
        Text emotionText = new Text("Click a button");
        emotionText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        emotionText.setTextAlignment(TextAlignment.CENTER);
        holderText.getChildren().addAll(emotionText);
        
        //Button setup followed by adding containers to the grid
        createButtons(btnBox, emotionText, canvas);
        
        root.add(canvas, 1, 0);
        root.add(btnBox, 0, 0);
        root.add(holderText, 1, 1);
        
        //Scene creation with root
        primaryStage.setScene(scene);
        primaryStage.setTitle("Emotional Heart");
        primaryStage.show();
    }
    
    private void createButtons(Pane container, Text text, Canvas canvas) {
        Button red = new Button("Red");
        red.setOnAction((ActionEvent e) -> {
            drawHeart(canvas, Color.RED);
            text.setText("Love & Anger");
        });
        
        Button orange = new Button("Orange");
        orange.setOnAction((ActionEvent e) -> {
            drawHeart(canvas, Color.ORANGE);
            text.setText("Pride & Shame");
        });
        
        Button yellow = new Button("Yellow");
        yellow.setOnAction((ActionEvent e) -> {
            drawHeart(canvas, Color.YELLOW);
            text.setText("Joy & Grief");
        });
        
        Button green = new Button("Green");
        green.setOnAction((ActionEvent e) -> {
            drawHeart(canvas, Color.GREEN);
            text.setText("Trust & Fear");
        });
        
        Button blue = new Button("Blue");
        blue.setOnAction((ActionEvent e) -> {
            drawHeart(canvas, Color.BLUE);
            text.setText("Peace & Hostility");
        });
        
        Button indigo = new Button("Indigo");
        indigo.setOnAction((ActionEvent e) -> {
            drawHeart(canvas, Color.rgb(75, 0, 130));
            text.setText("Admiration & Disgust");
        });
        
        Button violet = new Button("Violet");
        violet.setOnAction((ActionEvent e) -> {
            drawHeart(canvas, Color.rgb(128, 0, 128));
            text.setText("Surprise & Boredom");
        });
        
        container.getChildren().addAll(red, orange, yellow, green, blue, indigo, violet);
    }
    
    //Possible to create QuadCurve objects and change their fill colors
    private void drawHeart(Canvas canvas, Color color) {
        final double leftMulti = .06;
        final double rightMulti = .94;
        final double heightDivisor = 3;
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, 500, 500);
        
        gc.setFill(color);
        gc.setStroke(color);
        
        gc.beginPath();
        gc.moveTo(canvas.getWidth() * leftMulti, canvas.getHeight()/ heightDivisor -1);
        //Upper
        gc.quadraticCurveTo(canvas.getWidth() * leftMulti, (canvas.getHeight()/ heightDivisor -1) -150, canvas.getWidth()/2, canvas.getHeight()/ heightDivisor -1);
        gc.quadraticCurveTo(canvas.getWidth() * rightMulti, (canvas.getHeight()/ heightDivisor -1) -150, canvas.getWidth() * rightMulti, canvas.getHeight()/ heightDivisor -1);
        //Lower
        gc.quadraticCurveTo(canvas.getWidth() * rightMulti -15, (canvas.getHeight()/ heightDivisor * 2 -1), canvas.getWidth()/2, canvas.getHeight() *rightMulti -1);
        gc.quadraticCurveTo(canvas.getWidth() * leftMulti +15, (canvas.getHeight()/ heightDivisor * 2 -1), canvas.getWidth() * leftMulti, canvas.getHeight()/ heightDivisor -1);
        
        gc.stroke();
        gc.fill();
    }
    
}
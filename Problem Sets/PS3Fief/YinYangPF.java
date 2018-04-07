// Author: Patrick Fief
// Course: Java Programming
// Set 3 Program 3
// References: https://docs.oracle.com/javase/8/javafx/api/allclasses-noframe.html

import java.util.Random;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.Group;
 
public class YinYangPF extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        final double size = 400;
        
        BorderPane root = new BorderPane();
        Group YinYang = new Group();
        Scene scene = new Scene(root, size+25, size+25);

        final Canvas canvas = new Canvas(size,size);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        //Creating 2 random colors
        Color upperColor = new Color(getRandom(), getRandom(), getRandom(), 1.0);
        Color lowerColor = new Color(getRandom(), getRandom(), getRandom(), 1.0);
        
        gc.setFill(upperColor);
        // Use fillOval to draw a filled in circle
        gc.fillOval(0, 0, size-1, size-1);
 
        gc.setFill(lowerColor);
        // Use fillArc to draw a semi circle
        gc.fillArc(0, 0, size-1, size-1, 270, 180, ArcType.ROUND);
        gc.fillOval(size/4, size/2, size/2, size/2);
 
        gc.setFill(upperColor);
        gc.fillOval(size/4, 0, size/2, size/2);
        gc.fillOval(7*size/16, 11*size/16, size/8, size/8);
 
        gc.setFill(lowerColor);
        gc.fillOval(7*size/16, 3*size/16, size/8, size/8);
        
        // Use strokeOval to draw an empty circle for the outside border
        gc.strokeOval(0, 0, size-1, size-1);
        
        YinYang.getChildren().add(canvas);
        root.setCenter(YinYang);
        
        //Scene creation with root
        primaryStage.setScene(scene);
        primaryStage.setTitle("Yin and Yang");
        primaryStage.show();
    }
    
    private float getRandom() {
        Random rand = new Random();
        return rand.nextFloat();
    }
}
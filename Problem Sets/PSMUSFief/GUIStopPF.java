// Author: Patrick Fief
// Course: Java Programming
// Set MU Program 4
// References: https://stackoverflow.com/questions/13906352/java-draw-regular-polygon

import java.util.Random;
import javafx.application.Application;
import java.awt.Polygon;
import javafx.scene.text.Font;
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

public class GUIStopPF extends Application {
    public static void main(String[] args) {
        launch(args);
    } 
    
    @Override
    public void start(Stage primaryStage) {
        final double size = 600;
        
        BorderPane root = new BorderPane();
        Group stSign = new Group();
        Scene scene = new Scene(root, size+25, size+25);

        final Canvas canvas = new Canvas(size,size);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        //Draw the outline for the white border
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.WHITE);
        Octogon outerOct = new Octogon(size - 200, size / 2);
        gc.strokePolygon(outerOct.getXPoints(), outerOct.getYPoints(), 8);
        
        //Fill octagon with random color
        //Creating random color
        Color signColor = new Color(getRandom(), getRandom(), getRandom(), 1.0);
        gc.setFill(signColor);
        
        Octogon innerOct = new Octogon(size - 225, size / 2);
        gc.fillPolygon(innerOct.getXPoints(), innerOct.getYPoints(), 8);
        
        //Right white STOP lettering
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.WHITE);
        Font stopFont = new Font(200);
        gc.setFont(stopFont);
        gc.strokeText("STOP", size / 9, size / 1.65);
        gc.fillText("STOP", size / 9, size / 1.65);
        
        
        stSign.getChildren().add(canvas);
        root.setCenter(stSign);
        
        //Scene creation with root
        primaryStage.setScene(scene);
        primaryStage.setTitle("Stop Sign");
        primaryStage.show();
    }
    
    private float getRandom() {
        Random rand = new Random();
        return rand.nextFloat();
    }
    
    class Octogon {
        double [] xPoints;
        double [] yPoints;
        int sides = 8;
        
        public Octogon(double side, double origin){
            xPoints = new double[8];
            yPoints = new double[8];
            
            for(int i = 0; i < sides; i++) {
                xPoints[i] = (origin + side * Math.cos(i * 2 * Math.PI / sides + 75) * (Math.PI/4));
                yPoints[i] = (origin + side * Math.sin(i * 2 * Math.PI / sides + 75) * (Math.PI/4));
            }
        }
        
        public double [] getXPoints(){
          return xPoints;
        }
        
        public double [] getYPoints(){
          return yPoints;
        }
    }
}
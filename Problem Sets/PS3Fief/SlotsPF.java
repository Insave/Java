// Author: Patrick Fief
// Course: Java Programming
// Set 3 Program 2
// References: https://docs.oracle.com/javase/8/javafx/api/allclasses-noframe.html

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.stream.*;
import java.nio.file.*;
import java.io.IOException;
 
public class SlotsPF extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Slots!!");
        
        //Creating the root node as a GridPane
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        //Creating Controls
        Text scenetitle = new Text("!! Play Slots !!");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        scenetitle.setTextAlignment(TextAlignment.CENTER);
        grid.add(scenetitle, 0, 0, 2, 1);

        //Create the three image holders for the slots
        HBox[] slotHolder = new HBox[3];
        
        
        //Load images
        Image[] slotImages = getImages();
        
        
        //Button Creation
        Button btn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);
        
        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);
        
        //Displays the grid lines for debugging
        grid.setGridLinesVisible(true);
        
        //Event Handling
        btn.setOnAction((ActionEvent e) -> {
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("Sign in button pressed");
        });
        
        //Scene creation with grid as the root node
        Scene scene = new Scene(grid, 400, 375);
        primaryStage.setScene(scene);
        
        primaryStage.show();
        
    }
    
    private void PlaySlots() {
        
    }
    
    private Image[] getImages() {
        //Assumed to be 6 images in the /Fruits directory provided
        //Names of images are assumed to be the same as provided
        try (Stream<Path> paths = Files.walk(Paths.get("./Fruits"))) {
            paths.filter(Files::isRegularFile).forEach(System.out::println);
        } catch (IOException e){
            System.out.println("The Fruits folder is missing files");
        }
        return new Image[6];
    }
}
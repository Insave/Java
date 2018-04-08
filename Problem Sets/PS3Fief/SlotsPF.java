// Author: Patrick Fief
// Course: Java Programming
// Set 3 Program 2
// References: https://docs.oracle.com/javase/8/javafx/api/allclasses-noframe.html
// https://stackoverflow.com/questions/11300847/load-and-display-all-the-images-from-a-folder

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
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.File;
import javafx.embed.swing.SwingFXUtils;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.imageio.ImageIO;
 
public class SlotsPF extends Application {
    // I don't like the global
    int cash = 50;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Slots!!");
        
        //Creating the root node as a GridPane
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(25);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        //Scene creation with grid as the root node
        Scene scene = new Scene(grid, 400, 375);
        primaryStage.setScene(scene);
        
        //Load images
        Image[] slotImages = getImages();
        
        //Buttons with actions
        Button play = new Button("Spin");
        play.setPrefWidth(100);
        
        play.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                --cash;
                cash += PlaySlots(scene, slotImages);
                ((Text) scene.lookup("#moneyText")).setText("Cash: $" + cash);
            }
        });
        
        Button quit = new Button("Quit");
        quit.setPrefWidth(100);
        quit.setOnAction((ActionEvent e) -> {
            primaryStage.close();
        });
        
        //Add content to grid
        grid.add(initImageBox("leftImage"), 0, 0);
        grid.add(initImageBox("centerImage"), 1, 0);
        grid.add(initImageBox("rightImage"), 2, 0);
        
        grid.add(initTextBox("", "leftText"), 0, 1);
        grid.add(initTextBox("", "centerText"), 1, 1);
        grid.add(initTextBox("", "rightText"), 2, 1);
        grid.add(initTextBox("Hit spin to play!", "winText"), 1, 3);
        grid.add(initTextBox("Cash: $50", "moneyText"), 0, 5);
        
        grid.add(play, 1, 5);
        grid.add(quit, 2, 5);
        
        //Displays the grid lines for debugging
        //grid.setGridLinesVisible(true);
        
        
        // Finished initialization
        primaryStage.show();
        
    }
    
    //Takes the scene the game is played on, and images that have a chance to show
    //Returns the winnings if any
    private int PlaySlots(Scene scene, Image[] images) {
        //Ideally would not need to hard code the names in the same order as the files, luckily they're in alpha order
        final String[] symbols = new String[]{"BAR", "BELL", "CHERRY", "LEMON", "ORANGE", "PLUM"};
        
        // Get me three random ints 0 to 5
        int[] rolls = new int[3];
        Random rand = new Random();
        
        for(int i = 0; i < 3; i++) {
            rolls[i] = rand.nextInt((6));
        }
        
        // Not sure if this is the proper way to do this
        ((Text) scene.lookup("#leftText")).setText(symbols[rolls[0]]);
        ((Text) scene.lookup("#centerText")).setText(symbols[rolls[1]]);
        ((Text) scene.lookup("#rightText")).setText(symbols[rolls[2]]);
        
        ((ImageView) scene.lookup("#leftImage")).setImage(images[rolls[0]]);
        ((ImageView) scene.lookup("#centerImage")).setImage(images[rolls[1]]);
        ((ImageView) scene.lookup("#rightImage")).setImage(images[rolls[2]]);
        
        int winnings = 0;
        //A really really hardcoded way to check
        //BAR BAR BAR
        if(rolls[0] == 0 && rolls[1] == 0 && rolls[2] == 0) winnings = 250;
        //BELL BELL BELL/BAR
        else if(rolls[0] == 1 && rolls[1] == 1 && (rolls[2] == 1 || rolls[2] == 0)) winnings = 20;
        //PLUM PLUM PLUM/BAR
        else if(rolls[0] == 5 && rolls[1] == 5 && (rolls[2] == 5 || rolls[2] == 0)) winnings = 14;
        //ORANGE ORANGE ORANGE/BAR
        else if(rolls[0] == 4 && rolls[1] == 4 && (rolls[2] == 4 || rolls[2] == 0)) winnings = 10;
        //CHERRY CHERRY CHERRY
        else if(rolls[0] == 2 && rolls[1] == 2 && rolls[2] == 2) winnings = 7;
        //CHERRY CHERRY ---
        else if(rolls[0] == 2 && rolls[1] == 2) winnings = 5;
        //CHERRY --- ---
        else if(rolls[0] == 2) winnings = 2;
        
        if (winnings > 0)
            ((Text) scene.lookup("#winText")).setText("You won $" + winnings + "!");
        else 
            ((Text) scene.lookup("#winText")).setText("You Lose");
        
        return winnings;
    }
    
    // Creates a Text control with specified Id wrapped in an HBox container
    private HBox initTextBox(String label, String id) {
        Text text = new Text(label);
        text.setId(id);
        HBox textHolder = new HBox();
        textHolder.setAlignment(Pos.CENTER);
        textHolder.setPrefWidth(100);
        textHolder.getChildren().addAll(text);
        return textHolder;
    }
    
    // Pulls the 6 images from the predefined Fruits directory
    private Image[] getImages() {
        //Assumed to be 6 images in the /Fruits directory provided
        //Names of images are assumed to be the same as provided
        final Path dir = Paths.get("./Fruits");
        Image[] images = new Image[6];
        int index = 0;
        
        if (dir.toFile().isDirectory()) {
            for (final File f : dir.toFile().listFiles(IMAGE_FILTER)) {
                BufferedImage img = null;
                
                try {
                    img = ImageIO.read(f);
                    images[index++] = SwingFXUtils.toFXImage(img, null);
                    
                } catch (final IOException e) {
                    System.out.println("Image failed to load from file " + f.getName());
                }
            }
        }
        
        return images;
    }
    
    // Filter to identify images based on their extensions
    static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {
        final String[] EXTENSIONS =  new String[] {"gif", "png", "bmp", "jpg"};

        @Override
        public boolean accept(final File dir, final String name) {
            for (final String ext : EXTENSIONS) {
                if (name.endsWith("." + ext)) {
                    return (true);
                }
            }
            return (false);
        }
    };
    
    // Creates a Imageview with specified Id wrapped in an HBox container
    private HBox initImageBox(String id) {
        ImageView iv = new ImageView();
        HBox box = new HBox();
        box.getChildren().addAll(iv);
        box.setStyle("-fx-border-color: black");
        
        iv.setId(id);
        iv.setFitWidth(100);
        iv.setFitHeight(100);
        iv.setSmooth(true);
        iv.setCache(true);
        
        return box;
    }
}
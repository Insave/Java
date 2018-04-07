import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Basic JPanel that draws a YinYang symbol.
 * 
 * @author halfdan
 */
public class YinYangPanel extends JPanel {
    /**
     * Those colors determine the upper and lower color
     * of the YinYang symbol.
     */
    private Color upperColor, lowerColor;
    
    /**
     * Constructor with parameters to set the upper and lower color
     * of the YinYang symbol.
     * 
     * @param upperColor The upper color of the YinYang symbol
     * @param lowerColor The lower color of the YinYang symbol
     */
    public YinYangPanel(Color upperColor, Color lowerColor) {
        super();
        this.upperColor = upperColor;
        this.lowerColor = lowerColor;
    }
    
    /**
     * Default constructor that sets the colors
     * to black and white.
     */
    public YinYangPanel() {
        this(Color.black, Color.white);
    }
    
    /**
     * Determines the diameter of the circle, depending on the
     * size of the JPanel.
     * 
     * @return the diameter
     */
    private int getDiameter() {
        int height = this.getHeight();
        int width = this.getWidth();
        
        // Make the drawable area proportional
        if (width < height) {
            height = width;
        } else {
            width = height;
        }
        
        return width;
    }
    
    /**
     * Draw the YinYang symbol.
     * 
     * @param g Graphics object to draw onto the panel
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        final int diameter = getDiameter();
        final int radius = diameter/2;
                        
        // Outer upper circle
        g.setColor(upperColor);
        g.fillArc(0, 0, diameter, diameter, 0, 180);
        
        // Outer lower circle        
        g.setColor(lowerColor);
        g.fillArc(0, 0, diameter, diameter, 0, -180);
        
        // Inner upper circle (white) - left side
        g.setColor(lowerColor);
        g.fillArc(0, diameter/4+1, radius, radius, 0, 180);
        
        // Inner lower circle (black) - right side
        g.setColor(upperColor);
        g.fillArc(radius, diameter/4, radius, radius, 0, -180);
        
        // Inner left circle (black on white)
        g.setColor(upperColor);
        g.fillArc(diameter/4-radius/8, diameter/2-radius/8, radius/4, radius/4, 0, 360);
        
        // Inner right circle (white on black)
        g.setColor(lowerColor);
        g.fillArc(diameter/2+(radius/8)*3, diameter/2-radius/8, radius/4, radius/4, 0, 360);        
        
    }
    
    
    /**
     * Main function to test the YinYangPanel.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        final JFrame mainFrame = new JFrame("YinYangPanel");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JPanel board = new YinYangPanel();
        mainFrame.add(board, BorderLayout.CENTER);
        mainFrame.setVisible(true);
    }    
}
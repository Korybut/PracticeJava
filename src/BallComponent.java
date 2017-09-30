import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BallComponent extends JPanel {

    public static final int DEFAULT_WIDTH = 450;
    public static final int DEFAULT_HEIGHT = 350;
    private List<Ball> balls = new ArrayList<>();

    /**
     * Add the ball to this component.
     * parameter b is the ball to be added.
     */
    public void add(Ball b){
        balls.add(b);
    }

    public void clear() { balls.clear(); }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Cleaning background
        Graphics2D g2 = (Graphics2D) g;
        for(Ball b : balls){
            g2.fill(b.getShape());
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }
}

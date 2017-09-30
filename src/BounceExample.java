import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class BounceExample extends JFrame {

    private BallComponent ballComponent;
    public static final int STEPS = 1000;
    public static final int DELAY = 3;

    private JPanel buttonPanel;

    /**
     * Creates frame with component containing bounds ball and start/stop buttons.
     *
     */
    public BounceExample() {
        setTitle("Bounds Balls");
        ballComponent = new BallComponent();
        add(ballComponent, BorderLayout.CENTER);
        buttonPanel = new JPanel();
        addButton(buttonPanel, "Start", e -> addBall());
        addButton(buttonPanel, "Start HIGH SPEED", e -> addBall(30,70,3,3));
        addButton(buttonPanel, "Stop", e -> setVisible(false));
        addButton(buttonPanel, "Clear", e -> clear());
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }

    public void addButton(Container c, String name, ActionListener listener){
        JButton button = new JButton(name);
        c.add(button);
        button.addActionListener(listener);
    }

    public void clear(){
        ballComponent.clear();
        repaint();
    }

    public void addBall(){
        try{
            Ball ball = new Ball();
            ballComponent.add(ball);
            // how many times the ball will bounce
            for(int i=1; i <= STEPS; i++){
                // get current ball position
                ball.move(ballComponent.getBounds());
                // repaint graphics
                ballComponent.paint(ballComponent.getGraphics());
                Thread.sleep(DELAY);
            }
        }
        catch (InterruptedException e){
        }
    }

    public void addBall(double x, double y, double dx, double dy){
        try{
            Ball ball = new Ball(x,y,dx,dy);
            ballComponent.add(ball);
            // how many times the ball will bounce
            for(int i=1; i <= STEPS; i++){
                // get current ball position
                ball.move(ballComponent.getBounds());
                // repaint graphics
                ballComponent.paint(ballComponent.getGraphics());
                Thread.sleep(DELAY);
            }
        }
        catch (InterruptedException e){
        }
    }
}

class BallComponent extends JPanel {

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

class Ball {

    private static final int X_SIZE = 15;
    private static final int Y_SIZE = 15;
    private double x = 0;
    private double y = 0;
    private double dx = 1;
    private double dy = 1;

    public Ball() {
    }

    public Ball(double x, double y, double dx, double dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Moving balls to next position, and reverse course if ball hit an edge.
     */
    public void move(Rectangle2D ball){
        x+=dx;
        y+=dy;
        // if hit left edge.
        if(x<ball.getMinX()){
            x = ball.getMinX();
            dx = -dx;
        }
        // if hit right edge.
        if(x+X_SIZE >= ball.getMaxX()){
            x = ball.getMaxX() - X_SIZE;
            dx = -dx;
        }
        if(y<ball.getMinY()){
            y = ball.getMinY();
            dy = -dy;
        }
        if(y+Y_SIZE >= ball.getMaxY()){
            y = ball.getMaxY() - Y_SIZE;
            dy = -dy;
        }
    }

    /**
     * Set ball in current position.
     */
    public Ellipse2D getShape(){
        return new Ellipse2D.Double(x, y, X_SIZE, Y_SIZE);
    }

}
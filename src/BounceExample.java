import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

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
        addButton(buttonPanel, "Start", e -> { addBall(); });
        addButton(buttonPanel, "Start HIGH SPEED", e -> { addBall(30,70,3,3);} );
        addButton(buttonPanel, "Stop", e -> { System.exit(0);} );
        addButton(buttonPanel, "Clear", e -> { clear(); } );
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

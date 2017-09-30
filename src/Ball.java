import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Ball {

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

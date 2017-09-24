import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class CirclesDrawExample extends JFrame {

    public CirclesDrawExample() {
        add(new CircleDrawComponent());
        pack();
        setLocation((int)MainFrame.point.getX()-this.getWidth()/2,(int)MainFrame.point.getY()-this.getHeight()/2);
    }
}

class CircleDrawComponent extends JComponent {

    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 500;

    private ArrayList<Ellipse2D> circles;
    private ArrayList<Line2D> lines;
    private Ellipse2D current;
    private Line2D line;

    public CircleDrawComponent() {
        circles = new ArrayList<>();
        current = null;
        lines = new ArrayList<>();
        line = null;
        addMouseListener(new MouseHandler());
        addMouseMotionListener(new MouseMotionHandler());
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // Drawing all elements from list.
        g2.setColor(Color.BLACK);
        for(Ellipse2D c : circles) g2.draw(c);
        g2.setColor(Color.GRAY);
        for(Line2D l : lines) g2.draw(l);
    }

    // Method searching ellipse about same position as point. If find it return this element, else return null.
    public Ellipse2D findCircle(Point2D point){
        for(Ellipse2D c : circles) if(c.contains(point)) return c;
        return null;
    }

    public Line2D findLine(Point2D point){
        for(Line2D l : lines) if((l.getX1()>point.getX()-10 & l.getX1()<point.getX()+10) &
                (l.getY1()>point.getY()-10 & l.getY1()<point.getY()+10)) return l;
        return null;
    }

    // Add new ellipse to list and repaint all components.
    public void add(Point2D point){
        double x = point.getX();
        double y = point.getY();
        current = new Ellipse2D.Double(x-10,y-10,20,20);
        circles.add(current);
        // Add line to current position.
        line = new Line2D.Double(x,y,x,y);
        lines.add(line);
        repaint();
    }

    // Remove finding element from list ant repaint all components.
    public void remove(Point2D point){
        circles.remove(findCircle(point));
        lines.remove(findLine(point));
        repaint();
    }

    private class MouseHandler extends MouseAdapter{

        // When current is not null (exist in list) checked click count and if is more and same 2 it is remove from list.
        @Override
        public void mouseClicked(MouseEvent e) {
            current = findCircle(e.getPoint());
            if(current!=null && e.getClickCount()>=2){
                remove(e.getPoint());
            }
        }

        // After clicked given mouse position and set new ellipse on current element if is null, but is not, do nothing.
        @Override
        public void mousePressed(MouseEvent e) {
            current = findCircle(e.getPoint());
            if(current==null) add(e.getPoint());
        }
    }


    private class MouseMotionHandler implements MouseMotionListener{

        @Override
        public void mouseDragged(MouseEvent e) {

        }
        // Helper class method constantly checking mouse position, and correct P2 lines.
        @Override
        public void mouseMoved(MouseEvent e) {
            System.out.println(e.getX() + " | " + e.getY());
            if(lines.size()!=0) for(Line2D l : lines) l.setLine(l.getP1(),e.getPoint());
            repaint();
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }
}

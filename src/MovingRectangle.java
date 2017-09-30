import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

public class MovingRectangle extends JFrame {
    public static final int DELAY = 4;
    public static final int JUMP_TIME = 25;
    private GameComponent gameComponent;
    private PlayRect playRect = new PlayRect();
    private JPanel buttonPanel;

    public MovingRectangle() throws HeadlessException {
        gameComponent = new GameComponent();
        gameComponent.addObj(playRect);
        add(gameComponent, BorderLayout.CENTER);
        buttonPanel = new JPanel();
        makeButton(buttonPanel, "<", e -> left());
        makeButton(buttonPanel, "Jump", e -> jump());
        makeButton(buttonPanel, "Rubber", e -> rubberJumps());
        makeButton(buttonPanel, ">", e -> right());
        makeButton(buttonPanel, "Close", e -> setVisible(false));
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }

    public void makeButton(Container c, String name, ActionListener listener){
        JButton button = new JButton(name);
        c.add(button);
        button.addActionListener(listener);
    }

    public void left(){
        playRect.moveLeft(playRect.getShape());
        gameComponent.paint(gameComponent.getGraphics());
    }

    public void right(){
        playRect.moveRight(playRect.getShape());
        gameComponent.paint(gameComponent.getGraphics());
    }

    public void jump(){
        try{
            for(int i=0; i<JUMP_TIME; i++){
                playRect.up(playRect.getShape());
                gameComponent.paint(gameComponent.getGraphics());
                Thread.sleep(DELAY+i);
            }
            for(int i=0; i<JUMP_TIME; i++){
                playRect.down(playRect.getShape());
                gameComponent.paint(gameComponent.getGraphics());
                Thread.sleep(DELAY+JUMP_TIME-i);
            }
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

    }

    public void rubberJumps(){
        try{
            int val = JUMP_TIME+20;
            while(val>0){
                for(int i=0; i<val; i++){
                    playRect.up(playRect.getShape());
                    gameComponent.paint(gameComponent.getGraphics());
                    Thread.sleep(DELAY+i);
                }
                for(int i=0; i<val; i++){
                    playRect.down(playRect.getShape());
                    gameComponent.paint(gameComponent.getGraphics());
                    Thread.sleep(DELAY+val-i);
                }
                if(val < 3) val = 0;
                else val-=(val/3);
            }

        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class GameComponent extends JPanel{

    public static final int DEFAULT_WIDTH = 600;
    public static final int DEFAULT_HEIGHT = 500;
    private PlayRect playRect;

    public void addObj(PlayRect pR){
        playRect = pR;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.draw(playRect.getShape());
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }
}

class PlayRect {

    public static final double SIZE_X = 40;
    public static final double SIZE_Y = 40;

    private double x = 250;
    private double y = 200;
    private double mx = 10;
    private double my = 4;


    public Rectangle2D getShape(){
        return new Rectangle2D.Double(x, y, SIZE_X, SIZE_Y);
    }

    public void moveLeft(Rectangle2D obj){
        if(obj.getX() >= 0+mx) {
            x-=mx;
        }
    }

    public void moveRight(Rectangle2D obj){
        if(obj.getX() <= GameComponent.DEFAULT_WIDTH-SIZE_X) {
            x += mx;
        }
    }

    public void up(Rectangle2D obj) {
        y -= my;
    }

    public void down(Rectangle2D obj) {
        y += my;
    }
}

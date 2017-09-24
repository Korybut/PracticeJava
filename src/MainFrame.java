import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

public class MainFrame extends JFrame {

    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 100;

    private static Toolkit kit = Toolkit.getDefaultToolkit();
    private static final int SCREEN_WIDTH = kit.getScreenSize().width;
    private static final int SCREEN_HEIGHT = kit.getScreenSize().height;

    static final Point2D point = new Point2D.Double(SCREEN_WIDTH/2,SCREEN_HEIGHT/2);

    public MainFrame(){
        setLocation((int) (point.getX()-DEFAULT_WIDTH/2), (int) (point.getY()-DEFAULT_HEIGHT));
        add(createTitlePanel("My practice task in JAVA"), BorderLayout.NORTH);
        add(new PracticeButtonComponent(), BorderLayout.CENTER);
        pack();
    }

    public JComponent createTitlePanel(String title){
        JComponent component = new JPanel(new BorderLayout());
        component.setPreferredSize(new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT));
        JLabel label = new JLabel(title, SwingConstants.CENTER);
        label.setFont(new Font("SansSerif",Font.PLAIN,30));
        component.add(label, BorderLayout.CENTER);
        return component;
    }

    // Private class with buttons to example practice projects.
    private class PracticeButtonComponent extends JComponent {

        public PracticeButtonComponent() {
            setLayout(new FlowLayout());

            makeButton("Color Button Example", e -> {
                new ColorExample().setVisible(true);
            });

            makeButton("Mouse Action Example", e -> {
                new CirclesDrawExample().setVisible(true);
            });
        }

        private void makeButton(String name, ActionListener listener){
            JButton button = new JButton(name);
            button.addActionListener(listener);
            add(button);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        }

    }
}

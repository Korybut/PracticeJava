import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorExample extends JFrame {

    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 250;

    private JComponent buttonPanel;
    private JLabel label;

    public ColorExample() {
        setTitle("Color Event");
        setLocation((int) MainFrame.point.getX()-DEFAULT_WIDTH/2,(int) MainFrame.point.getY()-DEFAULT_HEIGHT/2);
        buttonPanel = new JPanel(new FlowLayout());

        label = new JLabel("Color Event - press button to change background color", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 14));
        add(label, BorderLayout.NORTH);

        buttonPanel.setPreferredSize(new Dimension(400,100));
        add(buttonPanel, BorderLayout.CENTER);
        makeButton("yellow", Color.YELLOW);
        makeButton("green", Color.GREEN);
        makeButton("blue", Color.BLUE);
        pack();
    }

    private void makeButton(String name, Color color){
        JButton button = new JButton(name);
        button.addActionListener(new ColorAction(color));
        buttonPanel.add(button,BorderLayout.CENTER);
    }

    private class ColorAction implements ActionListener {

        Color color;

        public ColorAction(Color bgColor) {
            color = bgColor;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            buttonPanel.setBackground(color);
        }
    }
}

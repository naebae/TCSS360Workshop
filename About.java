import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class About {
    private JFrame frame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JLabel imageLabel;
    private JPanel panel;

    private Container contentPane;
    public About() {
        prepareGUI();
    }
    private void prepareGUI() {
        prepareFrame();
    }
    private void prepareFrame() {
        frame = new JFrame("Java Swing");
        Insets insets = frame.getInsets();
        frame.setSize(1000 + insets.left + insets.right,
                1000 + insets.top + insets.bottom);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        preparePanel();
        frame.add(panel);
        frame.setVisible(true);
    }
    private void preparePanel() {
        prepareText();
        prepareImages();
        contentPane = frame.getContentPane();
        contentPane.setLayout(new FlowLayout());
        headerLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        statusLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        imageLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(headerLabel);
        panel.add(imageLabel);
        panel.add(statusLabel);
    }
    private void prepareImages() {
       ImageIcon imageIcon = new ImageIcon("./calculator1.jpg");
       Image image = imageIcon.getImage();
       Image newimg = image.getScaledInstance(250, 500,  java.awt.Image.SCALE_SMOOTH);
       imageIcon = new ImageIcon(newimg);
       imageLabel = new JLabel(imageIcon);
       Dimension size = imageLabel.getPreferredSize();
       imageLabel.setBounds(250, 500, size.width, size.height);
        // source: https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
    }
    private void prepareText() {
        headerLabel = new JLabel("", JLabel.CENTER);
        headerLabel.setFont(new Font("Serif", Font.PLAIN, 60));
        statusLabel = new JLabel("",JLabel.CENTER);
        statusLabel.setFont(new Font("Serif", Font.PLAIN, 30));
        headerLabel.setText("About");
        statusLabel.setText("Creators: Danait Asefa, Joseph Thompson, Nathan Nguyen, Matthew Burgess");
        Dimension size = statusLabel.getPreferredSize();
        headerLabel.setBounds(500, -200, 500, 500);
        statusLabel.setBounds(250, -1000, size.width, size.height);
    }
}

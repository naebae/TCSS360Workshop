import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculatorv01");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);
        JPanel panel = new JPanel();
        panel.setBounds(40,80,200,200);
        panel.setBackground(Color.lightGray);
        JButton b1 = new JButton("About");
        b1.setBounds(50,100,80,30);
        b1.setBackground(Color.yellow);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                About about = new About();
            }
        });
        panel.add(b1);
        frame.add(panel);
        frame.setVisible(true);
    }
}
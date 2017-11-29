package mystuff.swing;

import mystuff.database.DatabaseSearch;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class OpenFrame extends JFrame {

    JButton button;
    JFrame mainFrame;


    public static void main(String[] args) {

        new OpenFrame();
    }

    public OpenFrame() {
        mainFrame = new JFrame();
        mainFrame.setSize(300, 300);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setTitle("open new frame");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();

        button = new JButton("open new frame");
        ListenForButton listenForButton = new ListenForButton();
        button.addActionListener(listenForButton);
        mainPanel.add(button);


        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);

    }

    public class ListenForButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == button) {
                Frame newFrame = new Frame();
                mainFrame.setVisible(false);

                Database newDatabase = new Database();
                newFrame.label.setText(newDatabase.labelOutput);

            }
        }
    }
}

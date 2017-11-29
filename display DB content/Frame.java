package mystuff.swing;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    JLabel label;
    JTextArea textArea;


   public Frame(){
       this.setSize(200,200);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setTitle("new frame");
       JPanel panel = new JPanel();
       label = new JLabel();
       //textArea = new JTextArea();
       //textArea.setLineWrap(true);
       //textArea.setEditable(false);
       //textArea.setOpaque(false);


      // panel.add(textArea);
       panel.add(label);

       this.add(panel);
       this.setVisible(true);
   }
}

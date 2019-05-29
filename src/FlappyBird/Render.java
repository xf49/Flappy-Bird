package FlappyBird;

import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class Render extends JPanel {
   private static final long serialversionUID=1L;
   
   protected void paintComponent(Graphics g){
	   super.paintComponent(g);
	   FlappyBird.flappybird.repaint(g);
   }
   
  
}

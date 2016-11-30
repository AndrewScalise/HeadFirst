package animation;


import javax.swing.*;
import java.awt.*;

public class SimpleAnimation {

	//make two instance variables in main gui class for x and y coordinates
	int x = 70;
	int y = 70;
	
	public static void main(String[] args){
		SimpleAnimation gui = new SimpleAnimation();
		gui.go();
	}
	
	public void go(){
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MyDrawPanel drawPanel = new MyDrawPanel();
		frame.getContentPane().add(drawPanel);
		frame.setSize(300, 300);
		frame.setVisible(true);
		
		//repeat 130 times
		for(int i = 0; i < 130; i++){
			//increment coordinates
			x++;
			y++;
			
			drawPanel.repaint();		//tell the panel to repaint itself
			
			try{
				Thread.sleep(50);		//slow it down to be visible
			}
			catch(Exception ex){
				
			}
		}
		}
	
	//make the inner class
	class MyDrawPanel extends JPanel{
		public void paintComponent(Graphics g){
			//reset background erase previous circle
			g.setColor(Color.white);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			
			g.setColor(Color.green);
			g.fillOval(x, y, 40, 40);				//use continually updated x,y coordinates form outer class
		}
	}
}



package gui;

import javax.swing.*;
import java.awt.*;

public class Buttons {

	public static void main(String[] args){
		Buttons gui = new Buttons();
		gui.go();
	}
	
	public void go(){
		JFrame frame = new JFrame();
		JButton button = new JButton("There is no spoons...");
		frame.getContentPane().add(BorderLayout.NORTH, button);
		frame.setSize(200, 200);
		frame.setVisible(true);
	}
}

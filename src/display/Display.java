package src.display;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Properties;

import javax.swing.JFrame;



public abstract class Display {
	private static boolean isCreated = false;
	private static JFrame window;
	private static Canvas content;
	
	public static void create(int width, int height, String title) {
		if(isCreated) return;
		
		Dimension size = new Dimension(width, height);
		content = new Canvas();
		content.setPreferredSize(size);
		
		window = new JFrame(title);
		window.getContentPane().add(content);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		window.setLocation((screenSize.width-width)/2, (screenSize.height - height)/2);
		window.setResizable(false);
		window.setVisible(true);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

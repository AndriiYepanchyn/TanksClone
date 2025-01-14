package display;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JFrame;



public abstract class Display {
	private static boolean isCreated = false;
	private static JFrame window;
	private static Canvas content;
	private static int CANVAS_WIDHT;
	private static int CANVAS_HEIGHT;
	
	public static void create(int width, int height, String title) {
		if(isCreated) return;
		CANVAS_WIDHT = width;
		CANVAS_HEIGHT = height;
		
		Dimension size = new Dimension(width, height);
		content = new Canvas() {
			@Override
			public void paint(Graphics g) {
//				Overriding paint is the worst way to draw dynamically changing objects
//				This causes the blinking of redrawn objects
				super.paint(g);
				render (g);
			}
		};
		content.setPreferredSize(size);
		content.setBackground(Color.BLACK);
		
		window = new JFrame(title);
		window.getContentPane().add(content);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		window.setLocation((screenSize.width-width)/2, (screenSize.height - height)/2);
		window.setResizable(false);
		window.setVisible(true);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void render() {
		content.repaint();
	}
	
	private static void render(Graphics g) {
		int r = 50;
		g.setColor(Color.YELLOW);
		g.fillOval(CANVAS_WIDHT/2-2*r, CANVAS_HEIGHT/2-2*r, 2*r, 2*r);
	}
}

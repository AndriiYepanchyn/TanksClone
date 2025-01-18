package display;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.Border;



public abstract class Display {
	private static boolean isCreated = false;
	private static JFrame window;
	private static Canvas content;
	private static int CANVAS_WIDHT;
	private static int CANVAS_HEIGHT;
	
	private static BufferedImage buffer;
	private static int[] bufferData;
	private static Graphics bufferGraphics;
	private static BufferStrategy bufferStrategy;
	
	private static int clearColor;
	private static double delta;
	
	public static void create(int width, int height, String title, int _clearColor, int numbuffers) {
		if(isCreated) return;
		CANVAS_WIDHT = width;
		CANVAS_HEIGHT = height;
		clearColor = _clearColor;
		
		Dimension size = new Dimension(CANVAS_WIDHT, CANVAS_HEIGHT);
		content = new Canvas();
		content.setPreferredSize(size);
		
		window = new JFrame(title);
		window.setPreferredSize(size);
		window.getContentPane().add(content);		
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		window.setLocation((screenSize.width-width)/2, (screenSize.height - height)/2);
		window.setVisible(true);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		ARGB - defines int format for storing point color
		buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
//		Here we establish connection between buffer and bufferData. This connection is bidirectional		
		bufferData = ((DataBufferInt) buffer.getRaster().getDataBuffer()).getData();
		bufferGraphics = buffer.getGraphics();
		
		content.createBufferStrategy(numbuffers);
//		 this is mandatory call after creating Strategy otherwise we'll get NPE
		bufferStrategy = content.getBufferStrategy();
		isCreated = true;
	
	}
	
	public static void render() {
//		Here we draw the picture in buffer
		int r = 150;
		bufferGraphics.setColor(Color.BLUE);
		bufferGraphics.fillOval((int)(CANVAS_WIDHT/2 - r + 200 * Math.sin(delta)),
				(int)(CANVAS_HEIGHT/2 - r + 100 * Math.cos(delta)), 2*r, 2*r);
		delta +=0.02;
//		This make red circle smooth
		((Graphics2D) bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		bufferGraphics.setColor(Color.RED);
		bufferGraphics.fillOval((int)(CANVAS_WIDHT/2 - r + 20 * Math.sin(delta)),
				(int)(CANVAS_HEIGHT/2 - 3*r + 10 * Math.cos(delta)), 2*r, 2*r);
		delta +=0.0;
//		This return smoothing into sharp
		((Graphics2D) bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
	}
	
	public static void swapBuffers() {
//		Here we swap image from buffer directly to the canvas and draw it
		Graphics g = bufferStrategy.getDrawGraphics();
		g.drawImage(buffer, 0, 0, null );
		bufferStrategy.show();
	}
	
	public static void clear() {
		Arrays.fill(bufferData, clearColor);
	}
	
	
}

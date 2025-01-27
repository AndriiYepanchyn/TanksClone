package game;

import java.awt.Color;
import java.awt.event.KeyEvent;

import display.Display;
import io.Input;
import main.Time;

public class Game implements Runnable {
	private static int WIDTH = 800;
	private static int HEIGHT = 600;
	private static String TITLE = "Tanks 1990 clone";
	private static int _CLEAR_COLOR = 0xff_00_00_00; // ARGB color
	private static int NUM_BUFFERS = 3;

	public static final float REQUIRED_FPS = 60.0f; //UPDATE_RATE
	public static final float TIME_PER_FRAME = Time.SECOND / REQUIRED_FPS; //UPDATE_INTERVAL
	public static final long IDLE_TIME = 1;
	
	private static int speed = 3;
	private static int x=350, y = 250, radius = 100;
	private static Color ovalColor = Color.YELLOW;
	
	private Input input;

	private boolean isRunning;
	private Thread gameThread;

	public Game() {
		isRunning = false;
		Display.create(WIDTH, HEIGHT, TITLE, _CLEAR_COLOR, NUM_BUFFERS);
		input = new Input();
		Display.addInputListener(input);
	}

	@Override
	public void run() {
		int realFps = 0;
		int realUpdates = 0; //updates in 1 loop in idea 1 loop = 1 render
		int updatesWithoutRender = 0; // show how many renders was missed
		
		int elapsedTimeForTitleRefresh = 0; //count
		
		long lastTime = Time.get();
		float requiredUpdates = 0; //delta
		while (isRunning) {
			long now = Time.get();
			long loopDuration = now - lastTime; // elapsedTime - duration of the loop
			lastTime = now; // Each loop refreshes last time
			elapsedTimeForTitleRefresh += loopDuration;
			
			boolean shouldRender = false;
			
			requiredUpdates += (loopDuration / TIME_PER_FRAME);
			
			while (requiredUpdates > 1) {
				update();
				requiredUpdates--;
				realUpdates++;
				if(shouldRender) {
					updatesWithoutRender++;
				}else {
					shouldRender = true;
				}
			}
			
			if(shouldRender) {
				render();
				realFps++;
			} else {
				try {
					Thread.sleep(IDLE_TIME);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			if(elapsedTimeForTitleRefresh >= Time.SECOND) {
				Display.setTitle(TITLE + "; FPS: " + realFps + "; Update: " + realUpdates + "; Missed render cicles: " +  updatesWithoutRender );
				realUpdates = 0;
				realFps = 0;
				updatesWithoutRender = 0;
				elapsedTimeForTitleRefresh = 0;
			}
		}
	}

	private void update() {
		if(input.getKey(KeyEvent.VK_UP)) {
			y -=speed;
		}
		if(input.getKey(KeyEvent.VK_DOWN)) {
			y +=speed;
		}
		if(input.getKey(KeyEvent.VK_LEFT)) {
			x -=speed;
		}
		if(input.getKey(KeyEvent.VK_RIGHT)) {
			x +=speed;
		}
		
	}

	private void render() {
		Display.clear();
		Display.getGraphics().setColor(ovalColor);
		Display.getGraphics().fillOval(x - radius, y - radius, radius *2, radius *2);
		Display.swapBuffers();
	}

	public synchronized void start() {
		if (isRunning)
			return;

		gameThread = new Thread(this);
		gameThread.start();
		isRunning = true;
	}

	public synchronized void stop() {
		if (!isRunning)
			return;

		try {
			gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		cleanup();
		isRunning = false;
	}

	public void cleanup() {
		Display.destroy();
	}

}

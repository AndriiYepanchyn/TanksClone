package graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Sprite {
	private SpriteSheet sheet;
	private float scale;
	
	public Sprite (SpriteSheet sheet, float scale) {
		this.sheet = sheet;
		this.scale = scale;
	}
	
	public void render(Graphics2D g, float x, float y) {
		BufferedImage image = sheet.getSprite(0);
		
		g.drawImage(image, (int) (x), (int) (y), (int) (scale * image.getWidth()), (int) (scale * image.getHeight()), null);
	}
}

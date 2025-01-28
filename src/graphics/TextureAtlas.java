package graphics;

import java.awt.image.BufferedImage;

import utils.ResourceLoader;

public class TextureAtlas {
	BufferedImage image;
	
	public TextureAtlas(String imagename) {
		image = ResourceLoader.loadImage(imagename);
	}
	
	public BufferedImage cut(int x, int y, int w, int h) {
        return image.getSubimage(x, y, w, h);
	}
}

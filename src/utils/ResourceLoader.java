package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ResourceLoader {
	public static final String PATH = "src/resources/";
	
	public static BufferedImage loadImage(String filename) {
		BufferedImage image = null;
		
		try {
			URL resource = ResourceLoader.class.getClassLoader().getResource("resources/" + filename);

	        if (resource == null) {
	            return null;
	        }
	        File picFile = new File(resource.getFile());
	        image = ImageIO.read(picFile);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return image;
	}
}

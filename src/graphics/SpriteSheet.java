package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteSheet {

	public String path;
	public final int SIZE;
	public int[] pixels;

	public static SpriteSheet tiles = new SpriteSheet("/textures/tiles/spritesheet.png", 256);
	
	public static SpriteSheet spider = new SpriteSheet("/textures/mobs/spider.png", 256);
	public static SpriteSheet zombie = new SpriteSheet("/textures/mobs/zombie2.png", 256);
	
	public static SpriteSheet player = new SpriteSheet("/textures/player/player.png", 256);
	public static SpriteSheet bars = new SpriteSheet("/textures/player/bars.png", 256);
	public static SpriteSheet effects = new SpriteSheet("/textures/effects.png", 256);
	
	public static SpriteSheet weapons = new SpriteSheet("/textures/attack/weapons.png", 256);
	public static SpriteSheet spells2 = new SpriteSheet("/textures/attack/spells.png", 256);
	public static SpriteSheet spells = new SpriteSheet("/textures/player/spells.png", 256);
	
	public static SpriteSheet numbers = new SpriteSheet("/numbers.png", 256);
	

	public SpriteSheet(String path, int size) {
		this.path = path;
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		load();
	}

	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("hi");
		}
	}

}

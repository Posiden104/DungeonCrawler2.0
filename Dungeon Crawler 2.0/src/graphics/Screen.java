package graphics;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import main.Game;

import tile.Tile;

public class Screen {

	public int width, height;
	public static int WIDTH, HEIGHT;
	public static int[] pixels;
	public static int xOffset, yOffset;
	public final int MAP_SIZE = 64;
	public final int MAP_SIZE_MASK = 63;

	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];

	private Random random = new Random();

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		WIDTH = width;
		HEIGHT = height;
		pixels = new int[width * height];

		for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
			tiles[i] = random.nextInt(0xffffff);
		}
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void rescale(int scale) {
		if (scale > 0) {
			Game.screen_scale = scale;
			Game.image = new BufferedImage(Game.width / Game.screen_scale, Game.height / Game.screen_scale, BufferedImage.TYPE_INT_RGB);
			Game.pixels = ((DataBufferInt) Game.image.getRaster().getDataBuffer()).getData();
			width = Game.width / Game.screen_scale;
			height = Game.height / Game.screen_scale;
			pixels = new int[width * height];
		}
	}

	public void renderTile(int xp, int yp, Tile tile) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < tile.sprite.SIZE; y++) {
			int ya = y + yp;
			for (int x = 0; x < tile.sprite.SIZE; x++) {
				int xa = x + xp;
				if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
			}
		}
	}

	public void renderPlayer(int xp, int yp, Sprite sprite, int flip) { // flip = 0 no flip, = 1 flip x, = 2 flip y, = 3 flip both
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < sprite.SIZE; y++) {
			int ya = y + yp;
			int ys = y;
			if (flip == 2 || flip == 3) ys = 31 - y;
			for (int x = 0; x < sprite.SIZE; x++) {
				int xa = x + xp;
				int xs = x;
				if (flip == 1 || flip == 3) xs = 31 - x;
				if (xa < -16 || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				int col = sprite.pixels[xs + ys * sprite.SIZE];
				if (col != 0xffFF00FF) pixels[xa + ya * width] = col;
			}
		}
	}

	public void renderItem(int xp, int yp, Sprite sprite) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < sprite.HEIGHT; y++) {
			int ya = y + yp;
			int ys = y;
			for (int x = 0; x < sprite.WIDTH; x++) {
				int xa = x + xp;
				int xs = x;
				if (xa < -16 || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				int col = sprite.pixels[xs + ys * sprite.WIDTH];
				if (col != 0xffFF00FF) pixels[xa + ya * width] = col;
			}
		}
	}

	public void renderAbsolute(int xp, int yp, Sprite sprite) {
		if (sprite == null) sprite = Sprite.rock;
		for (int y = 0; y < sprite.HEIGHT; y++) {
			int ya = y + yp;
			int ys = y;
			for (int x = 0; x < sprite.WIDTH; x++) {
				int xa = x + xp;
				int xs = x;
				if (xa < -16 || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				int col = sprite.pixels[xs + ys * sprite.WIDTH];
				if (col != 0xffFF00FF) pixels[xa + ya * width] = col;
			}
		}
	}

	public void renderBar(int xp, int yp, double WIDTH, Sprite sprite) {
		for (int y = 0; y < sprite.HEIGHT; y++) {
			int ya = y + yp;
			for (int x = 0; x < WIDTH; x++) {
				int xa = x + xp;
				if (xa < -16 || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				int col = sprite.pixels[x + y * sprite.WIDTH];
				if (col != 0xffFF00FF) pixels[xa + ya * width] = col;
			}
		}
	}

	public void renderPixel(int x, int y, int color) {
		x -= xOffset;
		y -= yOffset;
		if (x < 0 || x >= width || y < 0 || y >= height) return;
		pixels[x + y * width] = color;
	}

	public void setOffset(int xOffset, int yOffset) {
		Screen.xOffset = xOffset;
		Screen.yOffset = yOffset;
	}

}

package level;

import graphics.Screen;

import java.util.Random;

import tile.Tile;
import values.ColVals;

public class Level {

	protected int width, height;
	protected int[] tiles;
	protected int slowUpdate;

	public Random random = new Random();

	public Spawner spawner;
	protected Level level = this;


	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		generateLevel();
	}

	public Level(String path) {
		loadLevel(path);
		generateLevel();
	}

	protected void generateLevel() {
		spawner = new Spawner(level);
		spawn(20, 45, 10, "Spider");
		spawn(20, 20, 10, "Zombie");
	}

	protected void loadLevel(String path) {
	}

	public void update() {
		if (slowUpdate < 4) {
			spawner.update();
			slowUpdate++;
		} else {
			slowUpdate = 0;
		}
	}

	@SuppressWarnings("unused")
	private void time() {
	}

	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}

		spawner.render(screen);
	}

	public void spawn(int x, int y, int spawn, String name) {
		spawner = new Spawner(level);
		switch (name) {
		case "Spider":
			spawner.spider(x, y, spawn);
			break;
		case "Zombie":
			spawner.zombie(x, y, spawn);
			break;
		}
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.water;
		if (tiles[x + y * width] == ColVals.grass) return Tile.grass;
		if (tiles[x + y * width] == ColVals.yellowFlower) return Tile.yellowFlower;
		if (tiles[x + y * width] == ColVals.redFlower) return Tile.redFlower;
		if (tiles[x + y * width] == ColVals.rock) return Tile.rock;
		if (tiles[x + y * width] == ColVals.woodFloor) return Tile.woodFloor;
		if (tiles[x + y * width] == ColVals.hedge) return Tile.hedge;
		if (tiles[x + y * width] == ColVals.stoneWall) return Tile.stoneWall;
		if (tiles[x + y * width] == ColVals.coloredWall) return Tile.coloredWall;
		if (tiles[x + y * width] == ColVals.water) return Tile.water;
		if (tiles[x + y * width] == ColVals.cobble) return Tile.cobble;
		return Tile.water;
	}

}
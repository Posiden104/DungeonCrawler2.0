package tile;

import graphics.Screen;
import graphics.Sprite;

public class Tile {

	
	public int x, y;
	public Sprite sprite;

	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile yellowFlower = new YellowFlowerTile(Sprite.yellowFlower);
	public static Tile redFlower = new RedFlowerTile(Sprite.redFlower);
	public static Tile rock = new RockTile(Sprite.rock);
	public static Tile woodFloor = new WoodFloorTile(Sprite.woodFloor);
	public static Tile hedge = new HedgeTile(Sprite.hedge);
	public static Tile stoneWall = new WallTile(Sprite.stoneWall);
	public static Tile coloredWall = new WallTile(Sprite.coloredWall);
	public static Tile water = new WaterTile(Sprite.water1);
	public static Tile cobble = new CobbleTile(Sprite.cobble);

	
	
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);

	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}

	public void update(){
	
	}
	
	public boolean solid() {
		return false;
	}
}

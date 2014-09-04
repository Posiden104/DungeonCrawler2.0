package tile;

import graphics.Sprite;

public class WallTile extends Tile {

	public WallTile(Sprite sprite) {
		super(sprite);
	}
	
	public boolean solid(){
		return true;
	}
}

package tile;

import graphics.Sprite;

public class HedgeTile extends Tile{

	public HedgeTile(Sprite sprite) {
		super(sprite);
	}

	public boolean solid(){
		return true;
	}
}

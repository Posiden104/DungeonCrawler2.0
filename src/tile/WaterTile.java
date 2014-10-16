package tile;

import graphics.Screen;
import graphics.Sprite;

public class WaterTile extends Tile {

	private static int anim = 0;

	
	public WaterTile(Sprite sprite) {
		super(sprite);
	}

	public static void updateWater(){
		if (anim < 750000)
			anim++;
		else anim = 0;
	}
	
	public void render(int x, int y, Screen screen) {		
		if (anim % 200 > 100) {
			sprite = Sprite.water1;
		} else {
			sprite = Sprite.water2;
		}
		screen.renderTile(x << 4, y << 4, this);
	}

}

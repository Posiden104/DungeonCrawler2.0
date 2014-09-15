package entity.spells;

import entity.mob.Mob;
import graphics.Screen;
import level.Spawner;

public class Skill_Spell extends Spell {
	
	//private double spellRange;


	public Skill_Spell(int x, int y, int targetX, int targetY) {
		super(x, y, targetX, targetY);
		System.out.println("Hello");
	}
 
	public Skill_Spell() {
	}

	public void update() {

		checkMove();

		if (x == targetX) xa = 0;
		if (y == targetY) ya = 0;

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			x += xa;
			y += ya;
		}

		if (x == targetX && y == targetY) {
			checkHit();
			this.removed = true;
		}
	}

	public void render(Screen screen, int range) {
		if (!removed) screen.renderItem(x, y, sprite);
		
		//drawing the circle
		int midX = this.x + (sprite.WIDTH / 2);
		int midY = this.y + (sprite.HEIGHT / 2);
		for (int y = (midY - range); y <= (midY + range); y++) {
			for (int x = (midX - range); x <= (midX + range); x++) {
				if (distance(x, y, midX, midY) == range) {
					screen.renderPixel(x, y, 0xffFF0000);
				}
			}
		}

	}

	protected void checkHit(int damage, int range) {
		Mob selected;
		for (int i = 0; i < Spawner.mobs.size(); i++) {
			selected = Spawner.mobs.get(i);
			if (cornerCheck(x, y, selected, range)) {
				damage = applyDmgMod(damage, selected);
				selected.changeHealth(damage);
				selected.hit = true;
//				System.out.println(selected);
			}
		}
		removed = true;
	}

	protected void checkHit() {
	}

	protected int dist(int x, int y, int x1, int y1) {
		int b;
		int xx = (x - x1) * (x - x1);
		int yy = (y - y1) * (y - y1);
		b = (int) Math.sqrt(xx + yy);
		return b;
	}

	protected boolean cornerCheck(int x, int y, Mob selected, int range){
		int TL, TR, BL, BR;
		TL = dist(x, y, selected.xL, selected.yT);
		TR = dist(x, y, selected.xR, selected.yT);
		BL = dist(x, y, selected.xL, selected.yB);
		BR = dist(x, y, selected.xR, selected.yB);
		if(TL < range || TR < range || BL < range || BR < range)
			return true;
		return false;
	}
	
	public int getRange() {
		return range;
	}
}

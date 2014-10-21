package entity.spells;

import entity.mob.Mob;
import graphics.Screen;
import level.Spawner;
import main.Game;

public class Skill_Spell extends Spell {

	// private double spellRange;
	private int tanX, tanY;

	public Skill_Spell(double x, double y, int targetX, int targetY, int tanX, int tanY) {
		super(x, y, targetX, targetY);
		this.tanX = tanX;
		this.tanY = tanY;
		angle = angleCalc();
	}

	public Skill_Spell() {
	}

	protected double angleCalc() {
		double dx = tanX - Game.width / 2;
		double dy = tanY - Game.height / 2;
		return Math.atan2(dy, dx);
	}

	public void update() {

		checkMove();

		if (x == targetX)
			xa = 0;
		if (y == targetY)
			ya = 0;

		move(xa, ya);

		if ((x <= targetX + 5 && x >= targetX - 5) && (y <= targetY + 5 && y >= targetY - 5)) {
			checkHit();
			this.removed = true;
		}
	}

	public void render(Screen screen, int range) {
		if (!removed)
			screen.renderItem((int) x, (int) y, sprite);

		// drawing the circle
		int midX = (int) this.x + (sprite.WIDTH / 2);
		int midY = (int) this.y + (sprite.HEIGHT / 2);
		// System.out.println("circle range: " + range);
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
			if (cornerCheck((int) x, (int) y, selected, range)) {
				selected.changeHealth(damage, element);
				selected.hit = true;
				// System.out.println(selected);
			}
		}
		removed = true;
	}

	protected void checkHit() {
	}

	protected boolean cornerCheck(int x, int y, Mob selected, int range) {
		int TL, TR, BL, BR;
		TL = distance(x, y, selected.xL, selected.yT);
		TR = distance(x, y, selected.xR, selected.yT);
		BL = distance(x, y, selected.xL, selected.yB);
		BR = distance(x, y, selected.xR, selected.yB);
		if (TL < range || TR < range || BL < range || BR < range)
			return true;
		return false;
	}

	public int getRange() {
		return range;
	}
}

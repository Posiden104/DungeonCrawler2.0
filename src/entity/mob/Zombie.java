package entity.mob;

import values.Elements;
import graphics.LargeSprite;
import graphics.Screen;
import graphics.Sprite;

public class Zombie extends Mob {

	public Sprite sprite = Sprite.zombie_down;

	public Zombie(int x, int y, int ID, int LV) {
		this.x = this.startX = x * 16;
		this.y = this.startY = y * 16;
		this.LV = LV;
		distance = 150;
		weakness = Elements.fire;
		resist = Elements.ice;
		weakDmg = 55;
		resistDmg = 0;
		lvCalc(LV, 150);

		move = random.nextInt(5);
		name = "Zombie";
		this.ID = ID;

		xL = this.x - 16;
		yT = this.y - 16;
		xR = this.x + 16;
		yB = this.y + 16;
	}

	public void render(Screen screen) {
		if (getDir() == 0)
			sprite = Sprite.zombie_up;
		if (getDir() == 1)
			sprite = Sprite.zombie_right;
		if (getDir() == 2)
			sprite = Sprite.zombie_down;
		if (getDir() == 3)
			sprite = Sprite.zombie_left;
		if (dead)
			sprite = Sprite.zombie_dead;
		screen.renderItem(x - 16, y - 16, sprite);
		if (hit) {
			screen.renderBar(xL - Screen.xOffset, yT - 4 - Screen.yOffset, getHealthPercent(32),
					LargeSprite.floatingHealth);
		}
		if (Player.target == this) {
			screen.renderAbsolute((screen.width / 2) - 62, 5, Sprite.zombie_head);
			screen.renderBar((screen.width / 2) - 46, 13, 97.0, LargeSprite.back);
			screen.renderBar((screen.width / 2) - 46, 14, getHealthPercent(100), LargeSprite.health);
			screen.renderAbsolute((screen.width / 2) - 64, 5, LargeSprite.enemy_health);
		}
		if (frozen && !dead) {
			screen.renderItem(x - 16, y - 16, Sprite.freeze);
		}
	}
}

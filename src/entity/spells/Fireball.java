package entity.spells;

import entity.mob.Mob;
import entity.mob.Player;
import graphics.Sprite;

public class Fireball extends Spell {

	public static int damage = -(Player.LV * 4 + 50), mana = 50, range = 50, time = 30, ability = 0;

	public Fireball(int x, int y, Mob target) {
		super(x, y, target);
		sprite = Sprite.fireball;
		name = "Fireball";
		damage = -(Player.LV * 4 + 50);
		mana = 50;
		range = 50;
		time = 30;

	}

	public Fireball() {
		sprite = Sprite.fireball_icon;
		name = "Fireball";
	}

	protected void checkHit() {
		if (!hitWall) {
			checkHit(damage);
		}
	}
}

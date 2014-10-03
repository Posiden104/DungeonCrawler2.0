package entity.spells;

import entity.mob.Mob;
import entity.mob.Player;
import graphics.Sprite;

public class Iceball extends Spell {

	public static int damage = -(Player.LV * 4 + 35), mana = 60, range = 75, time = 60, ability = (Player.LV * 3 + 120);

	public Iceball(int x, int y, Mob target) {
		super(x, y, target);
		sprite = Sprite.iceball;
		name = "Iceball";
		damage = -(Player.LV * 4 + 35);
		mana = 60;
		range = 75;
		time = 60;
		ability = (Player.LV * 3 + 120);
	}

	public Iceball() {
		sprite = Sprite.iceball_icon;
		name = "Iceball";
	}

	protected void checkHit() {
		target.freeze(ability);
		checkHit(damage);
	}

}

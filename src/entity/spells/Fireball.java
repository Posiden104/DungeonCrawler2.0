package entity.spells;

import values.Elements;
import values.SpellVals;
import entity.mob.Mob;
import graphics.Sprite;

public class Fireball extends Spell {

	public Fireball(int x, int y, Mob target) {
		super(x, y, target);
		sprite = Sprite.fireball;
		name = "Fireball";
		damage = SpellVals.fireball_damage;
		mana = SpellVals.fireball_mana;
		range = SpellVals.fireball_range;
		time = SpellVals.fireball_time;
		element = Elements.fire;

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

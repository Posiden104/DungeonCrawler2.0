package entity.spells;

import values.Elements;
import values.SpellVals;
import entity.mob.Mob;
import graphics.Sprite;

public class Iceball extends Spell {

	public Iceball(int x, int y, Mob target) {
		super(x, y, target);
		sprite = Sprite.iceball;
		name = "Iceball";
		damage = SpellVals.iceball_damage;
		mana = SpellVals.iceball_mana;
		range = SpellVals.iceball_range;
		time = SpellVals.iceball_time;
		ability = SpellVals.iceball_freeze;
		element = Elements.ice;
	}

	public Iceball() {
		sprite = Sprite.iceball_icon;
		name = "Iceball";
	}

	protected void checkHit() {
		if (!hitWall) {
			target.freeze(ability);
			checkHit(damage);
		}
	}

}

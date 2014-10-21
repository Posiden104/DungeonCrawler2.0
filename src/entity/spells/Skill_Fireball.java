package entity.spells;

import graphics.Screen;
import graphics.Sprite;
import values.Elements;
import values.SpellVals;

public class Skill_Fireball extends Skill_Spell {

	public Skill_Fireball(int x, int y, int targetX, int targetY, int tanX, int tanY) {
		super(x, y, targetX, targetY, tanX, tanY);
		this.sprite = Sprite.fireball;
		name = "Skill Fireball";
		damage = SpellVals.skill_fireball_damage;
		mana = SpellVals.skill_fireball_mana;
		range = SpellVals.skill_fireball_range;
		time = SpellVals.skill_fireball_time;
		element = Elements.fire;
	}

	public void render(Screen screen) {
		render(screen, range);
	}

	public Skill_Fireball() {
		this.sprite = Sprite.skill_fireball_icon;
		name = "Skill Fireball";
	}

	protected void checkHit() {
		checkHit(damage, range);
	}
}

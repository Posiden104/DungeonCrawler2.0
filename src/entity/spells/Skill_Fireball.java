package entity.spells;

import entity.mob.Player;
import graphics.Screen;
import graphics.Sprite;

public class Skill_Fireball extends Skill_Spell {

	public static int damage = -(Player.LV * 4 + 100), mana = 50, range = 50, time = 30, ability;

	public Skill_Fireball(int x, int y, int targetX, int targetY, int tanX, int tanY) {
		super(x, y, targetX, targetY, tanX, tanY);
		this.sprite = Sprite.fireball;
		name = "Skill Fireball";
		damage = -(Player.LV * 4 + 100);
		mana = 50;
		range = 50;
		time = 30;
	}

	public void render(Screen screen){
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

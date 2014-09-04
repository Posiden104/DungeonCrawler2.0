package entity.weapons;

import entity.mob.Player;
import graphics.Sprite;

public class Sword extends Weapon {
	
	public Sword(Player player) {
		super(player);
		sprite = Sprite.sword;
		name = "Sword";
		range = 37;
		attackSpeed = 30;
		damage = 10;
	}
}

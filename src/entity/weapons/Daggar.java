package entity.weapons;

import entity.mob.Player;
import graphics.Sprite;

public class Daggar extends Weapon{

	public Daggar(Player player){
		super(player);
		sprite = Sprite.daggar;
		name = "Daggar";
		range = 20;
		attackSpeed = 60;
		damage = 4;
	}
}

package entity.weapons;

import entity.mob.Player;
import graphics.Sprite;

public class Wand extends Weapon{
		
	public Wand(Player player){
		super(player);
		sprite = Sprite.wand;
		wand = true;
	}
}

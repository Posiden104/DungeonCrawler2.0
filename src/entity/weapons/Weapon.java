package entity.weapons;

import level.Spawner;
import entity.Entity;
import entity.mob.Mob;
import entity.mob.Player;
import graphics.Sprite;

public class Weapon extends Entity {

	public Sprite sprite = Sprite.back;
	public Player player;

	public boolean wand = false;

	protected int attkTimer, range, attackSpeed, damage;

	public Weapon() {

	}

	public Weapon(Player player) {
		this.player = player;
	}

	public void attack(int dir) {
		if (attkTimer > 0) {
			return;
		}
		Mob selected;
		if (dir == 0) {
			for (int i = 0; i < Spawner.mobs.size(); i++) {
				selected = Spawner.mobs.get(i);
				if (selected.yT < player.y || selected.yB < player.y) {
					if (distance(player.x, player.y, selected.xL, selected.yT) <= range
							|| distance(player.x, player.y, selected.xR, selected.yB) <= range
							|| distance(player.x, player.y, selected.xL, selected.yB) <= range
							|| distance(player.x, player.y, selected.xR, selected.yT) <= range) {
						Player.target = selected;
						selected.hit = true;
						selected.changeHealth(-damage, -1);
					}
				}
			}
		}
		if (dir == 1) {
			for (int i = 0; i < Spawner.mobs.size(); i++) {
				selected = Spawner.mobs.get(i);
				if (selected.xL > player.x || selected.xR > player.x) {
					if (distance(player.x, player.y, selected.xL, selected.yT) <= range
							|| distance(player.x, player.y, selected.xR, selected.yB) <= range
							|| distance(player.x, player.y, selected.xL, selected.yB) <= range
							|| distance(player.x, player.y, selected.xR, selected.yT) <= range) {
						Player.target = selected;
						selected.hit = true;
						selected.changeHealth(-damage, -1);
					}
				}
			}
		}
		if (dir == 2) {
			for (int i = 0; i < Spawner.mobs.size(); i++) {
				selected = Spawner.mobs.get(i);
				if (selected.yT > player.y || selected.yB > player.y) {
					if (distance(player.x, player.y, selected.xL, selected.yT) <= range
							|| distance(player.x, player.y, selected.xR, selected.yB) <= range
							|| distance(player.x, player.y, selected.xL, selected.yB) <= range
							|| distance(player.x, player.y, selected.xR, selected.yT) <= range) {
						Player.target = selected;
						selected.hit = true;
						selected.changeHealth(-damage, -1);
					}
				}
			}
		}
		if (dir == 3) {
			for (int i = 0; i < Spawner.mobs.size(); i++) {
				selected = Spawner.mobs.get(i);
				if (selected.xL < player.x || selected.xR < player.x) {
					if (distance(player.x, player.y, selected.xL, selected.yT) <= range
							|| distance(player.x, player.y, selected.xR, selected.yB) <= range
							|| distance(player.x, player.y, selected.xL, selected.yB) <= range
							|| distance(player.x, player.y, selected.xR, selected.yT) <= range) {
						Player.target = selected;
						selected.hit = true;
						selected.changeHealth(-damage, -1);
					}
				}
			}
		}
		attkTimer = attackSpeed;
	}

	public void update() {
		attkTimer--;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}
}

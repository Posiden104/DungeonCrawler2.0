package entity.mob;

import entity.Entity;
import graphics.Sprite;

public class Mob extends Entity {

	protected Sprite sprite;

	protected int health, healthMax, healthTime;
	public int distance;
	public int ID;
	protected int anim;
	protected int time, killTime = 240;
	public int LV = 1;

	protected boolean moving = false;
	public boolean hit = false, frozen = false;
	public boolean dead = false;

	protected int freezeTime, freezeCap;

	public void lvCalc(int LV, int health) {
		this.health = healthMax = (int) (Math.pow(LV, 1.75) + health);
	}

	public void changeHealth(int change) {
		health += change;
	}

	public void deathTimer() {
		time++;
		if (time >= killTime) {
			removed = true;
		}
	}

	public void healthTimer() {
		healthTime++;
		if (healthTime >= 240) {
			hit = false;
			healthTime = 0;
		}
	}

	public void update() {
		if (health <= 0) {
			dead = true;
			if (Player.target == this) Player.target = null;
			deathTimer();
			return;
		}

		if (!frozen) {
			anim++;
			if (anim % 100 == 0) {
//				move = random.nextInt(5);
	//			anim = random.nextInt(50);
			}
		} else {
			move = 4;
			freezeTime++;
			if (freezeTime == freezeCap) {
				frozen = false;
			}
		}

		xa = 0;
		ya = 0;

		checkMove(distance);

		if (xa != 0 || ya != 0) {
			move(xa, ya);
		}

		if (hit) {
			healthTimer();
		}
	}

	public void render() {
	}

	public double getHealthPercent(int length) {
		double health_percent = (double) health / healthMax * length;
		return health_percent;
	}

	public void spawn() {
	}

	public void freeze(int time) {
		freezeTime = 0;
		freezeCap = time;
		frozen = true;
	}
}
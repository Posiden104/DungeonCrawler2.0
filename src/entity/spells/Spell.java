package entity.spells;

import entity.Entity;
import entity.mob.Mob;
import entity.mob.Player;
import graphics.Screen;
import graphics.Sprite;

public class Spell extends Entity {

	public Sprite sprite;
	protected Mob target;

	protected int targetX, targetY;
	protected int tanX, tanY;
	protected int move;
	protected int speed;
	public int damage, mana, range;
	public int time, ability, element;

	protected double xa, ya;
	protected double angle;
	protected double x, y;

	public boolean learned;
	protected boolean hitWall = false;

	// Used for regular Spells:
	public Spell(double x, double y, Mob target) {
		this.x = x;
		this.y = y;
		Player.spell = true;
		Player.weapon = false;
		if (target != null) {
			this.target = target;
			this.targetX = target.x;
			this.targetY = target.y;
			angle = angleCalc();
		}
		speed = 3;
	}

	// Used for Skill Spells:
	public Spell(double x, double y, int targetX, int targetY) {
		this.x = x;
		this.y = y;
		Player.spell = true;
		Player.weapon = false;
		this.targetX = targetX;
		this.targetY = targetY;
		speed = 3;
	}

	protected double angleCalc() {
		if (target != null) {
			targetX = target.x;
			targetY = target.y;
		}
		double dx = targetX - this.x;
		double dy = targetY - this.y;
		return Math.atan2(dy, dx);
	}

	// Used for GUI things:
	public Spell() {

	}

	public void start() {
		Player.startCast();
	}

	protected void checkMove() {
		xa = 0;
		ya = 0;

		xa = Math.cos(angle);
		ya = Math.sin(angle);

		if (collision((int) xa, (int) ya)) {
			hitWall = true;
			checkHit();
			removed = true;
		}
	}

	protected void checkHit(int damage) {
		target.changeHealth(damage, element);
		target.hit = true;
		removed = true;
	}

	protected void checkHit() {
	}

	public void move(double xa, double ya) {
		if (xa != 0 || ya != 0) {
			if (!collision((int) xa, (int) ya)) {
				x += speed * xa;
				y += speed * ya;
			} else {
				this.hitWall = true;
				checkHit();
				removed = true;
			}
		}
	}

	public void update() {
		angle = angleCalc();

		checkMove();

		if (x == targetX)
			xa = 0;
		if (y == targetY)
			ya = 0;

		move(xa, ya);

		if (x <= targetX + 5 && x >= targetX - 5 && y <= targetY + 5 && y >= targetY - 5) {
			checkHit();
		}
	}

	public void render(Screen screen) {
		if (!removed)
			screen.renderItem((int) x, (int) y, sprite);
	}

	protected boolean collision(int xa, int ya) {
		boolean solid = false;
		if (getDir() == 0) {
			if (level.getTile((int) (x + xa) / 16, (int) (y + ya) / 16).solid())
				solid = true;
		}
		if (getDir() == 1) {
			if (level.getTile(((int) (x + xa) / 16) + 1, (int) (y + ya) / 16).solid())
				solid = true;
		}
		if (getDir() == 2) {
			if (level.getTile(((int) (x + xa) / 16), ((int) (y + ya) / 16) + 1).solid())
				solid = true;
		}
		if (getDir() == 3) {
			if (level.getTile(((int) (x + xa) / 16) - 1, (int) (y + ya) / 16).solid())
				solid = true;
		}
		return solid;
	}
}

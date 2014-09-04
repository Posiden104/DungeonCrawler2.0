package entity.spells;

import entity.Entity;
import entity.mob.Mob;
import entity.mob.Player;
import graphics.Screen;
import graphics.Sprite;

public class Spell extends Entity {

	public Sprite sprite;
	protected Mob target;
	public String element;

	
	protected int x, y, targetX, targetY, range;
	protected int move, xa, ya;

	public boolean learned;


	//Used for regular Spells:
	public Spell(int x, int y, Mob target) {
		this.x = x;
		this.y = y;
		Player.spell = true;
		Player.weapon = false;
		if (target != null) {
			this.target = target;
			this.targetX = target.x;
			this.targetY = target.y;
		}
	}

	//Used for SplashSpells:
	public Spell(int x, int y, int targetX, int targetY) {
		this.x = x;
		this.y = y;
		Player.spell = true;
		Player.weapon = false;
		this.targetX = targetX;
		this.targetY = targetY;
	}
	
	//Used for GUI things:
	public Spell(){
		
	}
	
	public void start(){
		Player.startCast();
	}

	protected void checkMove() {
		xa = 0;
		ya = 0;

		if (x < targetX) {
			xa++;
		} else if (x > targetX) {
			xa--;
		}

		if (y < targetY) {
			ya++;
		} else if (y > targetY) {
			ya--;
		}

		if (collision(xa, ya)) {
			checkHit();
			removed = true;
		}
	}

	protected int applyDmgMod(int damage, Mob target){
		if(target.weakness == this.element){
			damage *= Player.LV;
			System.out.println(Player.LV + ", " + damage);
		}
		System.out.println(damage);
		return damage;
	}

	protected void checkHit(int damage) {
		damage = applyDmgMod(damage, target);
		target.changeHealth(damage);
		target.hit = true;
		this.removed = true;
	}
	
	protected void checkHit(){
	}

	public void render(Screen screen) {
		if (!removed) screen.renderItem(x, y, sprite);
	}

	public void update() {
		targetX = target.x;
		targetY = target.y;

		checkMove();

		if (x == targetX) xa = 0;
		if (y == targetY) ya = 0;

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			x += xa;
			y += ya;
		}

		if (x == targetX && y == targetY) {
			checkHit();
		}
	}

	protected boolean collision(int xa, int ya) {
		boolean solid = false;
		if (getDir() == 0) {
			if (level.getTile((x + xa) / 16, (y + ya) / 16).solid()) solid = true;
		}
		if (getDir() == 1) {
			if (level.getTile(((x + xa) / 16) + 1, (y + ya) / 16).solid()) solid = true;
		}
		if (getDir() == 2) {
			if (level.getTile(((x + xa) / 16), ((y + ya) / 16) + 1).solid()) solid = true;
		}
		if (getDir() == 3) {
			if (level.getTile(((x + xa) / 16) - 1, (y + ya) / 16).solid()) solid = true;
		}
		return solid;
	}
}

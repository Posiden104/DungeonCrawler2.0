package entity;

import graphics.Screen;

import java.util.Random;

import level.Level;
import main.Game;

public abstract class Entity {

	public int x, y, startX, startY;
	public int xL, xR, yT, yB;
	protected int xa, ya;
	private int dir = 0;
	protected int move;

	public boolean removed = false;

	protected Level level = Game.level;
	public String name;

	protected final Random random = new Random();

	public void move(int xa, int ya) {
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}
		if (xa > 0) setDir(1);
		if (xa < 0) setDir(3);
		if (ya > 0) setDir(2);
		if (ya < 0) setDir(0);

		if (!collision(xa, ya)) {
			x += xa;
			y += ya;

			xL += xa;
			yT += ya;

			xR += xa;
			yB += ya;
		}
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	protected int dist(int x, int y) {
		int b;
		int xx = (x - startX) * (x - startX);
		int yy = (y - startY) * (y - startY);
		b = (int) Math.sqrt(xx + yy);
		return b;
	}

	public static int distance(int x, int y, int x0, int y0) {
		int b;
		int xx = (x - x0) * (x - x0);
		int yy = (y - y0) * (y - y0);
		b = (int) Math.sqrt(xx + yy);
		return b;
	}

	protected void checkMove(int distance) {
		if (move == 0) {
			if (dist(x, y) <= distance || y - startY >= 0) {
				ya--;
			}
		}
		if (move == 1) {
			if (dist(x, y) <= distance || y - startY <= 0) {
				ya++;
			}
		}
		if (move == 2) {
			if (dist(x, y) <= distance || x - startX >= 0) {
				xa--;
			}
		}
		if (move == 3) {
			if (dist(x, y) <= distance || x - startX <= 0) {
				xa++;
			}
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
			if (level.getTile((x + xa) / 16, ((y + ya) / 16) + 1).solid()) solid = true;
		}
		if (getDir() == 3) {
			if (level.getTile(((x + xa) / 16) - 1, (y + ya) / 16).solid()) solid = true;
		}
		return solid;
	}

	public void update() {
	}

	public void render(Screen screen) {
	}

	public void remove() {
		removed = true;
	}

	public boolean isRemoved() {
		return removed;
	}

	public void init(Level level) {
		this.level = level;
	}

}

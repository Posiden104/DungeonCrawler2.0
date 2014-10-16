package level;

import java.util.ArrayList;
import java.util.Random;

import entity.mob.Mob;
import entity.mob.Spider;
import entity.mob.Zombie;
import graphics.Screen;

public class Spawner {

	protected Random random = new Random();
	private Level level;

	public static ArrayList<Mob> mobs = new ArrayList<Mob>();

	
	public Spawner(Level level) {
		this.level = level;
	}

	public void update() {
		for (int i = 0; i < mobs.size(); i++) {
			if (mobs.get(i).removed) {
				mobs.remove(i);
			} else {
				mobs.get(i).ID = i;
				mobs.get(i).update();
			}
		}
	}

	public void render(Screen screen) {
		for (int i = 0; i < mobs.size(); i++) {
			mobs.get(i).render(screen);
		}
	}

	//SPAWNER METHODS

	public void spider(int x, int y, int spawn) {
		if (spawn == 0) return;

		for (int i = 0; i < spawn; i++) {
			mobs.add(new Spider(getInt(x), getInt(y), lv(1, 5), i));
			mobs.get(mobs.size() - 1).init(level);
		}
	}

	public void zombie(int x, int y, int spawn) {
		if (spawn == 0) return;

		for (int i = 0; i < spawn; i++) {
			mobs.add(new Zombie(getInt(x), getInt(y), lv(4, 10), i));
			mobs.get(mobs.size() - 1).init(level);
		}
	}
		
	private int lv(int low, int high){
		int lv;
		lv = ((high - low +1) * random.nextInt()) + low;
		return lv;
	}
	
	private int getInt(int n) {
		n = n + random.nextInt(10);
		return n;
	}

}

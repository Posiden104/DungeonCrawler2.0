package entity.mob;

import entity.spells.Fireball;
import entity.spells.Iceball;
import entity.spells.Skill_Fireball;
import entity.spells.Spell;
import entity.weapons.Daggar;
import entity.weapons.Sword;
import entity.weapons.Wand;
import entity.weapons.Weapon;
import graphics.Screen;
import graphics.Sprite;
import headsUpDisplay.HUD;
import input.Keyboard;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import level.Spawner;
import main.Game;
import values.SpellVals;

public class Player extends Mob {

	private Keyboard input;
	private Sprite sprite;

	public static Mob target;
	private static HUD hud;

	private int anim = 0;
	private static int cast, castTime = SpellVals.fireball_time;
	public static int skillX = 0, skillY = 0;
	public static int tanX = 0, tanY = 0;
	public static int health, healthMax, mana, manaMax;
	public static int circleSize;
	public static int range = SpellVals.fireball_range;
	public static int LV = 1;

	private static boolean walking;
	public static boolean isCasting;
	public static boolean skill, aiming, stopAiming, show;
	public static boolean spell, weapon;
	public boolean test = false;

	public static String selectedSpell = "Fireball";

	public ArrayList<Spell> spells = new ArrayList<Spell>();
	public static ArrayList<Spell> knownSpells = new ArrayList<Spell>();

	public Weapon[] quickSlot = new Weapon[10];
	public static Weapon selectedWeapon = new Weapon();

	public Player(int LV, Keyboard input) {
		init(LV, input);
	}

	public Player(int x, int y, int LV, Keyboard input) {
		this.x = this.startX = x * 16;
		this.y = this.startY = y * 16;
		init(LV, input);
	}

	private void init(int LV, Keyboard input) {
		Player.LV = LV;
		this.input = input;
		sprite = Sprite.player_forward;
		knownSpells.add(new Skill_Fireball());
		knownSpells.add(new Iceball());
		knownSpells.add(new Fireball());
		quickSlot[0] = new Sword(this);
		quickSlot[1] = new Daggar(this);
		quickSlot[2] = new Wand(this);
		quickSlot[3] = new Weapon(this);
		quickSlot[4] = new Weapon(this);
		quickSlot[5] = new Weapon(this);
		quickSlot[6] = new Weapon(this);
		quickSlot[7] = new Weapon(this);
		quickSlot[8] = new Weapon(this);
		quickSlot[9] = new Weapon(this);
		selectedWeapon = quickSlot[0];
		hud = new HUD(this);
		lvCalc();
	}

	public static void lvCalc() {
		Player.health = healthMax = (int) (LV * 4.75 + 100);
		Player.mana = manaMax = (int) (LV * 3.24 + 50);
	}

	public static void dimSpell(String name) {
		switch (name) {
		case "Fireball":
			castTime = SpellVals.fireball_time;
			range = SpellVals.fireball_range;
			skill = false;
			aiming = false;
			break;
		case "Iceball":
			castTime = SpellVals.iceball_time;
			range = SpellVals.iceball_range;
			skill = false;
			aiming = false;
			break;
		case "Skill Fireball":
			castTime = SpellVals.skill_fireball_time;
			circleSize = SpellVals.skill_fireball_range;
			range = SpellVals.skill_fireball_range;
			target = null;
			stopAiming = false;
			aiming = true;
			skill = true;
			break;
		}
		selectedSpell = name;
	}

	public static void startCast() {
		if (!skill && target == null) {
			target = getTarget(range);
		}

		if (skill && !aiming) {
			aiming = true;
		}

		if (stopAiming)
			aiming = false;

		if (!aiming) {
			if (!skill) {
				if (target != null) {
					isCasting = true;
					cast = 0;
				}
			} else {
				stopAiming = false;
				isCasting = true;
				cast = 0;
			}
		}
	}

	public void casting() {
		isCasting = true;
		show = true;
		cast++;
		if (cast == castTime) {
			cast();
		}
	}

	public void cast() {
		isCasting = false;
		cast = 0;

		switch (selectedSpell) {
		case "Fireball":
			spells.add(new Fireball(x, y, target));
			mana -= SpellVals.fireball_mana;
			break;
		case "Iceball":
			spells.add(new Iceball(x, y, target));
			mana -= SpellVals.iceball_mana;
			break;
		case "Skill Fireball":
			spells.add(new Skill_Fireball(x, y, skillX, skillY, tanX, tanY));
			mana -= SpellVals.skill_fireball_mana;
			break;
		default:
			break;
		}

		spells.get(spells.size() - 1).init(level);
		show = false;
	}

	public static Mob getTarget(int range) {
		Mob selected;
		Mob tgt = null;
		int xp = Game.player.x;
		int yp = Game.player.y;
		int shortest = Integer.MAX_VALUE;
		for (int i = 0; i < Spawner.mobs.size(); i++) {
			selected = Spawner.mobs.get(i);
			if(selected.dead)
				continue;
			int dist = closestCorner(xp, yp, selected, range);
			if (dist != -1 && dist <= shortest) {
				shortest = dist;
				tgt = selected;
			}
		}
		return tgt;
	}

	public static int closestCorner(int x, int y, Mob selected, int range) {
		int TL, TR, BL, BR;
		TL = distance(x, y, selected.xL, selected.yT);
		TR = distance(x, y, selected.xR, selected.yT);
		BL = distance(x, y, selected.xL, selected.yB);
		BR = distance(x, y, selected.xR, selected.yB);
		if (TL < range || TR < range || BL < range || BR < range) {
			int right = Math.min(TR, BR);
			int left = Math.min(TL, BL);
			return Math.min(right, left);
		}
		return -1;
	}

	public void spellUpdate() {
		for (int i = 0; i < spells.size(); i++) {
			if (spells.get(i).removed) {
				spells.remove(i);
			} else {
				spells.get(i).update();
			}
		}
	}

	public void update() {
		int xa = 0, ya = 0;
		if (anim < 7500)
			anim++;
		else
			anim = 0;
		if (input.up)
			ya--;
		if (input.down)
			ya++;
		if (input.left)
			xa--;
		if (input.right)
			xa++;
		if (input.keys[KeyEvent.VK_F9]) {
			Player.LV++;
			lvCalc();
		}

		selectedWeapon.update();
		spellUpdate();

		// casting stuff:
		if (input.space) {
			if (selectedWeapon.wand) {
				if (!isCasting) {
					startCast();
				}
			} else {
				selectedWeapon.attack(getDir());
			}
		}

		if (isCasting) {
			if (target == null && !skill) {
				target = getTarget(range);
			}
			casting();
		}
		// done

		distance = dist(x, y);

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
	}

	public void render(Screen screen) {
		int flip = 0;
		if (getDir() == 0) {
			sprite = Sprite.player_forward;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_forward_1;
				} else {
					sprite = Sprite.player_forward_2;
				}
			}
		}

		if (getDir() == 1) {
			sprite = Sprite.player_side;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_side_1;
				} else {
					sprite = Sprite.player_side_2;
				}
			}
		}

		if (getDir() == 2) {
			sprite = Sprite.player_back;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_back_1;
				} else {
					sprite = Sprite.player_back_2;
				}
			}
		}

		if (getDir() == 3) {
			sprite = Sprite.player_side;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_side_1;
				} else {
					sprite = Sprite.player_side_2;
				}
			}
			flip = 1;
		}

		screen.renderPlayer(x - 16, y - 16, sprite, flip);

		for (int i = 0; i < spells.size(); i++) {
			spells.get(i).render(screen);
		}

		hud.render(screen);

		if (selectedWeapon.getRange() != 0) {
			if (getDir() == 0) {
				for (int y = this.y - selectedWeapon.getRange() + 1; y < this.y; y++) {
					for (int x = this.x - selectedWeapon.getRange() + 1; x < this.x + selectedWeapon.getRange() + 1; x++) {
						if (distance(x, y, this.x, this.y) >= selectedWeapon.getRange()) {
							screen.renderPixel(x, y, 0x00ff0000);
						}
					}
				}
			}
			if (getDir() == 1) {
				for (int y = this.y - selectedWeapon.getRange() + 1; y < this.y + selectedWeapon.getRange() + 1; y++) {
					for (int x = this.x; x < this.x + selectedWeapon.getRange() + 1; x++) {
						if (distance(x, y, this.x, this.y) >= selectedWeapon.getRange()) {
							screen.renderPixel(x, y, 0x00ff0000);
						}
					}
				}
			}
			if (getDir() == 2) {
				for (int y = this.y; y < this.y + selectedWeapon.getRange() + 1; y++) {
					for (int x = this.x - selectedWeapon.getRange() + 1; x < this.x + selectedWeapon.getRange() + 1; x++) {
						if (distance(x, y, this.x, this.y) >= selectedWeapon.getRange()) {
							screen.renderPixel(x, y, 0x00ff0000);
						}
					}
				}
			}
			if (getDir() == 3) {
				for (int y = this.y - selectedWeapon.getRange() + 1; y < this.y + selectedWeapon.getRange() + 1; y++) {
					for (int x = this.x - selectedWeapon.getRange() + 1; x < this.x; x++) {
						if (distance(x, y, this.x, this.y) >= selectedWeapon.getRange()) {
							screen.renderPixel(x, y, 0x00ff0000);
						}
					}
				}
			}
		}
	}

	public double getManaPercent() {
		double mana_percent = (double) mana / manaMax * 128;
		return mana_percent;
	}

	public double getHealthPercent() {
		double health_percent = (double) health / healthMax * 128;
		return health_percent;

	}

	public double getCastingPercent() {
		double casting_percent = (double) cast / castTime * 128;
		return casting_percent;
	}
}

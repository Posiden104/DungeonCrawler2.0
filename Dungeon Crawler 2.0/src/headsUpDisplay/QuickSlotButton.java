package headsUpDisplay;

import java.util.ArrayList;

import entity.mob.Player;
import entity.spells.Spell;
import entity.weapons.Weapon;
import graphics.Screen;
import graphics.Sprite;

public class QuickSlotButton {

	public int x, y, offY, x1, y1;

	public boolean selected;
	public boolean isWand;

	public String weaponName;

	private Weapon weapon = null;
	private Spell spell = null;
	public ArrayList<SpellSelectGUI> spellGUI = new ArrayList<SpellSelectGUI>();

	public QuickSlotButton(int x, int y, int offY, Weapon weapon) {
		this.x = x;
		this.y = y;
		x1 = x + 32;
		y1 = y + 32;
		this.offY = offY;
		this.weapon = weapon;
		if (weapon.wand) {
			isWand = true;
			spellGUI.add(new SpellSelectGUI(x, y - 37));
		}
	}

	public QuickSlotButton(int x, int y, int offY) {
		this.x = x;
		this.y = y;
		x1 = x + 32;
		y1 = y + 32;
		this.offY = offY;
	}

	public QuickSlotButton(int x, int y, int offY, Spell spell) {
		this.x = x;
		this.y = y;
		x1 = x + 32;
		y1 = y + 32;
		this.offY = offY;
		this.spell = spell;
	}

	public Spell getSpell() {
		return spell;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void render(Screen screen) {
		screen.renderAbsolute(x, y, Sprite.back);
		if (weapon != null) screen.renderAbsolute(x, y + offY, weapon.sprite);
		if (spell != null) screen.renderAbsolute(x, y + offY, spell.sprite);
		screen.renderAbsolute(x, y, Sprite.box);
		if (selected) {
			screen.renderAbsolute(x, y, Sprite.selected);
			if (isWand) {
				for (int i = 0; i < spellGUI.size(); i++) {
					spellGUI.get(i).render(screen);
					HUD.spells = true;
				}
			}
		}
	}

	public void clicked(String sender) {
		if (sender == "hud") {
			for (int i = 0; i < HUD.qSlots.length; i++) {
				HUD.qSlots[i].selected = false;
			}
			selected = true;
			Player.selectedWeapon = this.weapon;
		}
		if (sender == "pane") {
			for (int i = 0; i < SpellSelectGUI.spells.size(); i++) {
				SpellSelectGUI.spells.get(i).selected = false;
			}
			selected = true;
			Player.dimSpell(spell.name);

			if (!Player.skill) {
				if (Player.target != null) {
					Player.startCast();
				}
			} else {
				Player.startCast();
			}
		}
	}
}

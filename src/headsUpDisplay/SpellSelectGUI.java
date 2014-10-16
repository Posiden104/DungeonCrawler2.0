package headsUpDisplay;

import java.util.ArrayList;

import entity.mob.Player;
import graphics.Screen;

public class SpellSelectGUI {

	private int x, y;
	public static ArrayList<QuickSlotButton> spells = new ArrayList<QuickSlotButton>();

	public SpellSelectGUI(int x, int y) {
		this.x = x;
		this.y = y;
		spellLearned();
	}

	public void spellLearned() {
		for (int i = 0; i < spells.size(); i++) {
			spells.remove(i);
		}

		for (int i = 0; i < Player.knownSpells.size(); i++) {
			spells.add(new QuickSlotButton(x + 32 * i, y, 0, Player.knownSpells.get(i)));
		}		
		spells.get(0).selected = true;
	}

	public void render(Screen screen) {
		for (int i = 0; i < spells.size(); i++) {
			spells.get(i).render(screen);
		}
	}

}

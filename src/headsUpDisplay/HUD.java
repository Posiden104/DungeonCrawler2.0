package headsUpDisplay;

import input.Mouse;
import entity.mob.Player;
import entity.weapons.Weapon;
import graphics.LargeSprite;
import graphics.Screen;
import graphics.Sprite;

public class HUD {

	private Player player;
	public static QuickSlotButton[] qSlots = new QuickSlotButton[10];
	
	public static boolean spells; // used to determine if the spell selection pane is open

	public HUD(Player player) {
		this.player = player;
		redimQSlots();
	}

	public void redimQSlots() {
		for (int i = 0; i < player.quickSlot.length; i++) {
			Weapon weapon = player.quickSlot[i];
			int x = (Screen.WIDTH / 2) - (32 * (player.quickSlot.length / 2)) + (32 * i);
			int y = 300;
			qSlots[i] = new QuickSlotButton(x, y, 0, weapon);
		}
		qSlots[0].selected = true;
	}

	public static void wheel(int dir) {
		for (int i = 0; i < qSlots.length; i++) {
			if (qSlots[i].selected) {
				if(dir == 1 && i == 0 || dir == -1 && i == qSlots.length -1){
					return;
				}
				if (qSlots[i - dir] != null) {
					qSlots[i - dir].clicked("hud");
					return;
				}
			}
		}
	}

	public void render(Screen screen) {

		if (Player.target != null) {
			if (!Player.target.removed) {
				screen.renderItem(Player.target.x - 16, Player.target.y - 16, Sprite.crosshairs);
			} else {
				Player.target = null;
			}
		}

		//Drawing a circle:
		if (Player.aiming) {
			screen.renderItem(Mouse.mse.x - 16, Mouse.mse.y - 16, Sprite.crosshairs);
			for (int y = Mouse.mse.y - Player.circleSize; y <= Mouse.mse.y + Player.circleSize; y++) {
				for (int x = Mouse.mse.x - Player.circleSize; x <= Mouse.mse.x + Player.circleSize; x++) {
					if (player.distance(x, y, Mouse.mse.x, Mouse.mse.y) == Player.circleSize) {
						screen.renderPixel(x, y, 0xffFF0000);
					}
				}
			}
		}

		if (Player.show) {
			int x = (screen.width / 2) - 64;
			screen.renderAbsolute(x, 190, LargeSprite.back);
			screen.renderBar(x, 191, player.getCastingPercent(), LargeSprite.mana);
			screen.renderAbsolute(x, 190, LargeSprite.bronze);
		}

		screen.renderAbsolute(5, 315, LargeSprite.back);
		screen.renderBar(5, 316, player.getManaPercent(), LargeSprite.mana);
		screen.renderAbsolute(5, 315, LargeSprite.bronze);

		for (int i = 0; i < qSlots.length; i++) {
			qSlots[i].render(screen);
		}
	}
}

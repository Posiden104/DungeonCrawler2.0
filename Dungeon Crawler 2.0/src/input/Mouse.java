package input;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import level.Spawner;
import main.Game;
import entity.mob.Player;
import graphics.Screen;
import headsUpDisplay.HUD;
import headsUpDisplay.QuickSlotButton;
import headsUpDisplay.SpellSelectGUI;

public class Mouse implements MouseMotionListener, MouseListener, MouseWheelListener {

	public static Point pmp;
	public static Point mse = new Point(25, 25);

	private void getTarget() {
		boolean selected = false;
		getMse();

		for (int i = 0; i < Spawner.mobs.size(); i++) {
			if (mse.y < Spawner.mobs.get(i).yB && mse.y > Spawner.mobs.get(i).yT) {
				if (mse.x < Spawner.mobs.get(i).xR && mse.x > Spawner.mobs.get(i).xL) {
					if(Player.target == Spawner.mobs.get(i)){
						Player.startCast();
						selected = true;
						return;
					}
					Player.target = Spawner.mobs.get(i);
					selected = true;
					return;
				}
			}
		}

		if (!selected) Player.target = null;
	}

	private void getMse() {
		if (mse != null && pmp != null) {
			mse.x = ((pmp.x / Game.screen_scale) + Screen.xOffset);
			mse.y = ((pmp.y / Game.screen_scale) + Screen.yOffset);
		}
	}

	public void mouseReleased(MouseEvent e) {
		pmp = e.getPoint();
		getMse();

		for (int i = 0; i < HUD.qSlots.length; i++) {
			QuickSlotButton button = HUD.qSlots[i];
			if (pmp.x / Game.screen_scale < button.x1 && pmp.x / Game.screen_scale > button.x && pmp.y / Game.screen_scale < button.y1 && pmp.y / Game.screen_scale > button.y) {
				button.clicked("hud");
				return;
			}
		}

		if (HUD.spells) {
			for (int i = 0; i < SpellSelectGUI.spells.size(); i++) {
				QuickSlotButton button = SpellSelectGUI.spells.get(i);
				if (pmp.x / Game.screen_scale < button.x1 && pmp.x / Game.screen_scale > button.x && pmp.y / Game.screen_scale < button.y1 && pmp.y / Game.screen_scale > button.y) {
					button.clicked("pane");
					return;
				}
			}
		}

		if (Player.selectedWeapon.wand) {
			if (!Player.skill) {
				getTarget();
				return;
			}
			
			if (Player.aiming) {
				Player.skillX = mse.x;
				Player.skillY = mse.y;
				Player.aiming = false;
				Player.stopAiming = true;
				Player.startCast();
			}
		}
	}

	public void mouseMoved(MouseEvent e) {
		pmp = e.getPoint();
	}

	public void update() {
		getMse();
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		HUD.wheel(e.getWheelRotation());
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

}

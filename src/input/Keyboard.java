package input;

import headsUpDisplay.HUD;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{

	public boolean[] keys = new boolean[1200];
	public boolean up, down, left, right, space, num1, num2, num3, num4, num5, num6, num7, num8, num9, num0;

	public void update() {
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		space = keys[KeyEvent.VK_SPACE];		
		num1 = keys[KeyEvent.VK_1];
		num2 = keys[KeyEvent.VK_2];
		num3 = keys[KeyEvent.VK_3];
		num4 = keys[KeyEvent.VK_4];
		num5 = keys[KeyEvent.VK_5];
		num6 = keys[KeyEvent.VK_6];
		num7 = keys[KeyEvent.VK_7];
		num8 = keys[KeyEvent.VK_8];
		num9 = keys[KeyEvent.VK_9];
		num0 = keys[KeyEvent.VK_0];
		quickKeys();
	}

	public void quickKeys(){
		if(num1) HUD.qSlots[0].clicked("hud");
		if(num2) HUD.qSlots[1].clicked("hud");
		if(num3) HUD.qSlots[2].clicked("hud");
		if(num4) HUD.qSlots[3].clicked("hud");
		if(num5) HUD.qSlots[4].clicked("hud");
		if(num6) HUD.qSlots[5].clicked("hud");
		if(num7) HUD.qSlots[6].clicked("hud");
		if(num8) HUD.qSlots[7].clicked("hud");
		if(num9) HUD.qSlots[8].clicked("hud");
		if(num0) HUD.qSlots[9].clicked("hud");
	}
	
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {
	}
}

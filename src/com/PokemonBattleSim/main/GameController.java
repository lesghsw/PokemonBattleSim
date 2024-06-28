package com.PokemonBattleSim.main;

import java.awt.event.KeyEvent;

public class GameController {

	KeyHandler keyH;

	public GameController(KeyHandler keyH) {

		this.keyH = keyH;
	}
	
	public void update(GamePanel gp) {
		
		if(keyH.isKeyPressed(KeyEvent.VK_F2) && !keyH.isPrevKeyPressed(KeyEvent.VK_F2)) {
			
			gp.getSettings().toggleDebugMode();
		}
		keyH.updatePrevKeyState();
	}
}

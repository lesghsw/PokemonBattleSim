package com.PokemonBattleSim.main;

import javax.swing.JFrame;

public class Window {
	
	public Window() {
		JFrame window = new JFrame("Pokémon LES & KRB");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamePanel.startGameThread();
	}
}

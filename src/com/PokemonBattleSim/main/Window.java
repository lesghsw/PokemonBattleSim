package com.PokemonBattleSim.main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;

public class Window {
	
	private JFrame window;
	private JPanel mainPanel;
	private CardLayout cardLayout;
	
	private GamePanel gamePanel;

	public Window() {
		window = new JFrame("Pokémon LES & KRB");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);

		cardLayout = new CardLayout();
		mainPanel = new JPanel(cardLayout);

		gamePanel = new GamePanel();
		CharSelectPanel charSelectPanel = new CharSelectPanel(this);
		SettingsPanel settingsPanel = new SettingsPanel(this);
		LoadSavePanel loadSavePanel = new LoadSavePanel(this);
		MenuPanel menuPanel = new MenuPanel(this);

		mainPanel.add(menuPanel, "Menu");
		mainPanel.add(charSelectPanel, "CharSelect");
		mainPanel.add(settingsPanel, "Settings");
		mainPanel.add(loadSavePanel, "LoadSave");
		mainPanel.add(gamePanel, "Game");

		window.add(mainPanel);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}

	public void showPanel(String panelName) {
		cardLayout.show(mainPanel, panelName);
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}
}
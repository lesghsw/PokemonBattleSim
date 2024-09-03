package com.PokemonBattleSim.main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;

public class Window {
	
	private JFrame window;
	private JPanel mainPanel;
	private CardLayout cardLayout;

	public Window() {
		window = new JFrame("Pokémon LES & KRB");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);

		cardLayout = new CardLayout();
		mainPanel = new JPanel(cardLayout);

		GamePanel gamePanel = new GamePanel();
		MenuPanel menuPanel = new MenuPanel(this);

		mainPanel.add(menuPanel, "Menu");
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
		return (GamePanel) mainPanel.getComponent(1); // 1 = GamePanel
	}
}
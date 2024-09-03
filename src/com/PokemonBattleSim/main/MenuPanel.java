package com.PokemonBattleSim.main;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {

	private Window window;

	public MenuPanel(Window window) {
		this.window = window;
		JButton startButton = new JButton("Start Game");
		JButton useless = new JButton("Cliccami");
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameState.state = GameState.PLAYING;
				window.showPanel("Game");
				window.getGamePanel().startGameThread(); // Avvia il thread quando inizia la battaglia (penso(?))
			}
		});

		this.add(startButton);
		this.add(useless);
	}
}
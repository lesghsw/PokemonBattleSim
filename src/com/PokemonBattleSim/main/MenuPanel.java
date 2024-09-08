package com.PokemonBattleSim.main;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {

	private Window window;

	public MenuPanel(Window window) {
		this.window = window;
		
		JButton startButton = new JButton("Nuova Partita");
		JButton loadButton = new JButton("Carica Salvataggio"); // PLACEHOLDER
		JButton settingsButton = new JButton("Impostazioni");
		JButton exitButton = new JButton("Esci");
		
		settingsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameState.state = GameState.SETTINGS;
				window.showPanel("Settings");
			}
		});
		
		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameState.state = GameState.SAVEFILE;
				window.showPanel("LoadSave");
			}
		});
		
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameState.state = GameState.CHRSEL;
				window.showPanel("CharSelect");
			}
		});
		
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		this.add(startButton);
		this.add(loadButton);
		this.add(settingsButton);
		this.add(exitButton);
	}
}
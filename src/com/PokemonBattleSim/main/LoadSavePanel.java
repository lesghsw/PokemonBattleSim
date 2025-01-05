package com.PokemonBattleSim.main;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadSavePanel extends JPanel{
	
	private Window window;
	
	public LoadSavePanel(Window window) {
		this.window = window;
		
		JButton sf1Button = Pulzante.creaPulzante("Salvataggio 1", "sound/Button.png", Color.WHITE);
		JButton sf2Button = Pulzante.creaPulzante("Salvataggio 2", "sound/Button.png", Color.WHITE);
		JButton sf3Button = Pulzante.creaPulzante("Salvataggio 3", "sound/Button.png", Color.WHITE);
		JButton backButton = Pulzante.creaPulzante("Indietro", "sound/Button.png", Color.WHITE);
		
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameState.state = GameState.MENU;
				window.showPanel("Menu");
			}
		});
		
		this.add(sf1Button);
		this.add(sf2Button);
		this.add(sf3Button);
		this.add(backButton);
	}
}

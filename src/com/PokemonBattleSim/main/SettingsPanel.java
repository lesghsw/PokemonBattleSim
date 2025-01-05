package com.PokemonBattleSim.main;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPanel extends JPanel{
	
	private Window window;
	
	public SettingsPanel(Window window) {
		this.window = window;
		
		JButton s1Button = Pulzante.creaPulzante("Impostazioni Generiche 1", "sound/Button.png", Color.WHITE);
		JButton s2Button = Pulzante.creaPulzante("Impostazioni Generiche 2", "sound/Button.png", Color.WHITE);
		JButton s3Button = Pulzante.creaPulzante("Impostazioni Generiche 3", "sound/Button.png", Color.WHITE);
		JButton s4Button = Pulzante.creaPulzante("Impostazioni Generiche 4", "sound/Button.png", Color.WHITE);
		JButton vsyncButton = Pulzante.creaPulzante("V-Sync", "sound/Button.png", Color.WHITE);
		JButton mutemusicButton = Pulzante.creaPulzante("Silenzia Musica", "sound/Button.png", Color.WHITE);
		JButton backButton = Pulzante.creaPulzante("Indietro", "sound/Button.png", Color.WHITE);
		
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameState.state = GameState.MENU;
				window.showPanel("Menu");
			}
		});
		
		this.add(s1Button);
		this.add(s2Button);
		this.add(s3Button);
		this.add(s4Button);
		this.add(vsyncButton);
		this.add(mutemusicButton);
		this.add(backButton);
	}
}

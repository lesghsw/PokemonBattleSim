package com.PokemonBattleSim.main;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPanel extends JPanel{
	
	private Window window;
	
	public SettingsPanel(Window window) {
		this.window = window;
		
		JButton s1Button = new JButton("Impostazioni Generiche 1");
		JButton s2Button = new JButton("Impostazioni Generiche 2");
		JButton s3Button = new JButton("Impostazioni Generiche 3");
		JButton s4Button = new JButton("Impostazioni Generiche 4");
		JButton vsyncButton = new JButton("VSync >:O");
		JButton mutemusicButton = new JButton("Silenzia Musica >:O");
		JButton backButton = new JButton("Indietro");
		
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

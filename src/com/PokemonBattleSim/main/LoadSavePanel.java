package com.PokemonBattleSim.main;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadSavePanel extends JPanel{
	
	private Window window;
	
	public LoadSavePanel(Window window) {
		this.window = window;
		
		JButton sf1Button = new JButton("Save File 1");
		JButton sf2Button = new JButton("Save File 2");
		JButton sf3Button = new JButton("Save File 3");
		JButton backButton = new JButton("Indietro");
		
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

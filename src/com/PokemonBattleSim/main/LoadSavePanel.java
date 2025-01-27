package com.PokemonBattleSim.main;

import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadSavePanel extends JPanel{
	
	private Window window;
	
	public LoadSavePanel(Window window) {
		this.window = window;
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.WHITE);
		
		JButton sf1Button = Pulzante.creaPulzante("Salvataggio 1", "sound/Button.png", Color.WHITE);
		JButton sf2Button = Pulzante.creaPulzante("Salvataggio 2", "sound/Button.png", Color.WHITE);
		JButton sf3Button = Pulzante.creaPulzante("Salvataggio 3", "sound/Button.png", Color.WHITE);
		JButton backButton = Pulzante.creaPulzante("Indietro", "sound/Button.png", Color.WHITE);
		
		// Dimensioni Pulsanti
		sf1Button.setMaximumSize(new Dimension(300, 40));
        sf2Button.setMaximumSize(new Dimension(300, 40));
        sf3Button.setMaximumSize(new Dimension(300, 40));
        backButton.setMaximumSize(new Dimension(300, 40));
        
        // Centra i pulsanti
        sf1Button.setAlignmentX(CENTER_ALIGNMENT);
        sf2Button.setAlignmentX(CENTER_ALIGNMENT);
        sf3Button.setAlignmentX(CENTER_ALIGNMENT);
        backButton.setAlignmentX(CENTER_ALIGNMENT);
        
        // Aggiungi spazio tra pulsanti
        this.add(Box.createVerticalStrut(20));
        this.add(sf1Button);
        this.add(Box.createVerticalStrut(10));
        this.add(sf2Button);
        this.add(Box.createVerticalStrut(10));
        this.add(sf3Button);
        this.add(Box.createVerticalStrut(10));
        this.add(backButton);
		
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameState.state = GameState.MENU;
				window.showPanel("Menu");
			}
		});
	}
}

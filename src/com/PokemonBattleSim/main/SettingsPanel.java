package com.PokemonBattleSim.main;

import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPanel extends JPanel{
	
	private Window window;
	
	public SettingsPanel(Window window) {
		this.window = window;
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.WHITE);
		
		JButton s1Button = Pulzante.creaPulzante("Impostazioni Generiche 1", "sound/Button.png", Color.WHITE);
		JButton s2Button = Pulzante.creaPulzante("Impostazioni Generiche 2", "sound/Button.png", Color.WHITE);
		JButton s3Button = Pulzante.creaPulzante("Impostazioni Generiche 3", "sound/Button.png", Color.WHITE);
		JButton s4Button = Pulzante.creaPulzante("Impostazioni Generiche 4", "sound/Button.png", Color.WHITE);
		JButton vsyncButton = Pulzante.creaPulzante("V-Sync", "sound/Button.png", Color.WHITE);
		JButton mutemusicButton = Pulzante.creaPulzante("Silenzia Musica", "sound/Button.png", Color.WHITE);
		JButton backButton = Pulzante.creaPulzante("Indietro", "sound/Button.png", Color.WHITE);

        // Dimensioni Pulsanti
        s1Button.setMaximumSize(new Dimension(300, 40));
        s2Button.setMaximumSize(new Dimension(300, 40));
        s3Button.setMaximumSize(new Dimension(300, 40));
        s4Button.setMaximumSize(new Dimension(300, 40));
        vsyncButton.setMaximumSize(new Dimension(300, 40));
        mutemusicButton.setMaximumSize(new Dimension(300, 40));
        backButton.setMaximumSize(new Dimension(300, 40));

        // Centra i pulsanti
        s1Button.setAlignmentX(CENTER_ALIGNMENT);
        s2Button.setAlignmentX(CENTER_ALIGNMENT);
        s3Button.setAlignmentX(CENTER_ALIGNMENT);
        s4Button.setAlignmentX(CENTER_ALIGNMENT);
        vsyncButton.setAlignmentX(CENTER_ALIGNMENT);
        mutemusicButton.setAlignmentX(CENTER_ALIGNMENT);
        backButton.setAlignmentX(CENTER_ALIGNMENT);

        // Aggiungi spazio tra pulsanti
        this.add(Box.createVerticalStrut(20));
        this.add(s1Button);
        this.add(Box.createVerticalStrut(10));
        this.add(s2Button);
        this.add(Box.createVerticalStrut(10));
        this.add(s3Button);
        this.add(Box.createVerticalStrut(10));
        this.add(s4Button);
        this.add(Box.createVerticalStrut(10));
        this.add(vsyncButton);
        this.add(Box.createVerticalStrut(10));
        this.add(mutemusicButton);
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

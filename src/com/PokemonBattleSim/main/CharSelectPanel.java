package com.PokemonBattleSim.main;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CharSelectPanel extends JPanel{
	
	private Window window;
	private JLabel nomeTrainer;
	private JLabel pkdx;
	private JTextField fieldTrainer;
	
	public CharSelectPanel(Window window) {
		this.window = window;
		
		JButton battleButton = new JButton("Inizia!");
		
		nomeTrainer = new JLabel("Nome: ");
		fieldTrainer = new JTextField(15);
		
		pkdx = new JLabel("Pokédex:");
		ToggleButtonGrid gridPanel = new ToggleButtonGrid(3, 3);
		
		JButton backButton = new JButton("Indietro");
		
		battleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameState.state = GameState.PLAYING;
				window.showPanel("Game");
				window.getGamePanel().startGameThread(); // Avvia il thread quando inizia la battaglia (penso(?))
			}
		});
		
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameState.state = GameState.MENU;
				window.showPanel("Menu");
			}
		});
		
		this.add(battleButton);
		this.add(nomeTrainer);
		this.add(fieldTrainer);
		this.add(pkdx);
		this.add(gridPanel);
		this.add(backButton);
	}
}

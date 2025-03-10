package com.PokemonBattleSim.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Pannello del menu del simulatore.
 * Questa classe rappresenta il menu principale del simulatore,
 * permettendo all'utente navigare nella sezione dei salvataggi,
 * selezione dei Pokémon o chiudere il simulatore.
 * 
 * @author Giampietri2108347
 */
@SuppressWarnings("serial")
public class MenuPanel extends JPanel {
	@SuppressWarnings("unused")
	private Window window;

	/**
	 * Costruttore del pannello del menu.
     * Inizializza il logo e pulsanti di navigazione.
     *
     * @param window La finestra principale dell'applicazione, necessaria per navigare tra i vari pannelli.
     */
    public MenuPanel(Window window) {
        this.window = window;
        
        this.setBackground(Color.WHITE);
        
        // Imposta il BorderLayout
        this.setLayout(new BorderLayout());

        ImageIcon logo = new ImageIcon(getClass().getResource("ref/Logo.png")); // Carica Logo
        Image scaledImage = logo.getImage().getScaledInstance(500, 197, Image.SCALE_SMOOTH); // Dimensioni Logo
        JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
        logoLabel.setHorizontalAlignment(JLabel.CENTER);

        // Crea i pulsanti
        JButton startButton = Pulzante.creaPulzante("Inizia", "ref/Button.png", Color.WHITE);
        JButton loadButton = Pulzante.creaPulzante("Mostra Profili", "ref/Button.png", Color.WHITE);
        JButton exitButton = Pulzante.creaPulzante("Esci", "ref/Button.png", Color.WHITE);

        // Crea un pannello separato per i pulsanti per usare BoxLayout per metterli in verticale
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Color.WHITE);

        // Centra i pulsanti
        startButton.setAlignmentX(CENTER_ALIGNMENT);
        loadButton.setAlignmentX(CENTER_ALIGNMENT);
        exitButton.setAlignmentX(CENTER_ALIGNMENT);

        // Aggiungi i pulsanti al pannello e spazio tra loro e tra loro e logo
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(startButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(loadButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(exitButton);

        this.add(logoLabel, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.CENTER);

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	window.getProfilePanel().refreshPanel(); // Ricarica pulsanti profili
                window.showPanel("LoadSave");
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.showPanel("CharSelect");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
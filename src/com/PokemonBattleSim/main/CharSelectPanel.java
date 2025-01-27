package com.PokemonBattleSim.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CharSelectPanel extends JPanel {

    private Window window;
    private JLabel nomeTrainer;
    private JLabel pkdx;
    private JTextField fieldTrainer;

    public CharSelectPanel(Window window) {
        this.window = window;

        // Configura il pannello principale
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());

        // Etichetta e campo di testo per il nome del trainer
        nomeTrainer = new JLabel("Nome Allenatore:");
        nomeTrainer.setFont(new Font("Arial", Font.BOLD, 18));
        fieldTrainer = new JTextField(15);
        fieldTrainer.setMaximumSize(new Dimension(300, 40));

        // Etichetta per la Pokédex
        pkdx = new JLabel("Pokédex:");
        pkdx.setFont(new Font("Arial", Font.BOLD, 18));

        // Griglia per la selezione dei Pokémon
        ToggleButtonGrid gridPanel = new ToggleButtonGrid();

        // Pulsanti per navigare
        JButton battleButton = Pulzante.creaPulzante("Inizia!", "sound/Button.png", Color.WHITE);
        JButton backButton = Pulzante.creaPulzante("Indietro", "sound/Button.png", Color.WHITE);

        // Imposta le dimensioni dei pulsanti
        battleButton.setPreferredSize(new Dimension(150, 40));
        backButton.setPreferredSize(new Dimension(150, 40));

        // Pannello per il nome e la griglia
        JPanel tuttoPanel = new JPanel();
        tuttoPanel.setLayout(new BoxLayout(tuttoPanel, BoxLayout.Y_AXIS));
        tuttoPanel.setBackground(Color.WHITE);
        tuttoPanel.add(Box.createVerticalStrut(10)); // Spazio iniziale
        tuttoPanel.add(nomeTrainer);
        tuttoPanel.add(Box.createVerticalStrut(5)); // Spazio tra etichetta e campo
        tuttoPanel.add(fieldTrainer);
        tuttoPanel.add(Box.createVerticalStrut(20)); // Spazio tra il nome e la Pokédex
        tuttoPanel.add(pkdx);
        tuttoPanel.add(Box.createVerticalStrut(5));
        tuttoPanel.add(gridPanel);

        // Pannello per i pulsanti
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.setBackground(Color.WHITE);

        // Aggiungi i pulsanti ai lati opposti
        buttonPanel.add(battleButton, BorderLayout.WEST); // Pulsante "Inizia!" a sinistra
        buttonPanel.add(backButton, BorderLayout.EAST);  // Pulsante "Indietro" a destra

        // Aggiungi i pannelli al pannello principale
        this.add(tuttoPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        // Listener per il pulsante "Inizia!"
        battleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameState.state = GameState.PLAYING;
                window.showPanel("Game");
                window.getGamePanel().startGameThread(); // Avvia il thread della battaglia
            }
        });

        // Listener per il pulsante "Indietro"
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameState.state = GameState.MENU;
                window.showPanel("Menu");
            }
        });
    }
}

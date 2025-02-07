package com.PokemonBattleSim.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CharSelectPanel extends JPanel {

    private Window window;
    private JTextField fieldTrainer1, fieldTrainer2;
    private ToggleButtonGrid gridPanel1, gridPanel2;

    public CharSelectPanel(Window window) {
        this.window = window;

        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());

        // Etichette e campi per i nomi
        JLabel nomeTrainer1 = new JLabel("Nome Allenatore 1:");
        nomeTrainer1.setFont(new Font("Arial", Font.BOLD, 18));
        fieldTrainer1 = new JTextField(15);

        JLabel nomeTrainer2 = new JLabel("Nome Allenatore 2:");
        nomeTrainer2.setFont(new Font("Arial", Font.BOLD, 18));
        fieldTrainer2 = new JTextField(15);

        // Etichette Pokédex
        JLabel pkdx1 = new JLabel("Pokédex Allenatore 1:");
        pkdx1.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel pkdx2 = new JLabel("Pokédex Allenatore 2:");
        pkdx2.setFont(new Font("Arial", Font.BOLD, 18));

        // Griglie per la selezione Pokémon
        gridPanel1 = new ToggleButtonGrid();
        gridPanel2 = new ToggleButtonGrid();

        // Pulsanti di navigazione
        JButton battleButton = Pulzante.creaPulzante("Inizia!", "sound/Button.png", Color.WHITE);
        JButton backButton = Pulzante.creaPulzante("Indietro", "sound/Button.png", Color.WHITE);

        battleButton.setPreferredSize(new Dimension(150, 40));
        backButton.setPreferredSize(new Dimension(150, 40));

        // Pannello per il layout a due colonne
        JPanel trainerPanel = new JPanel(new GridLayout(1, 3));
        trainerPanel.setBackground(Color.WHITE);

        // Pannello del primo allenatore
        JPanel trainer1Panel = new JPanel();
        trainer1Panel.setLayout(new BoxLayout(trainer1Panel, BoxLayout.Y_AXIS));
        trainer1Panel.setBackground(Color.WHITE);
        trainer1Panel.add(nomeTrainer1);
        trainer1Panel.add(fieldTrainer1);
        trainer1Panel.add(Box.createVerticalStrut(10));
        trainer1Panel.add(pkdx1);
        trainer1Panel.add(gridPanel1);

        // Pannello del secondo allenatore
        JPanel trainer2Panel = new JPanel();
        trainer2Panel.setLayout(new BoxLayout(trainer2Panel, BoxLayout.Y_AXIS));
        trainer2Panel.setBackground(Color.WHITE);
        trainer2Panel.add(nomeTrainer2);
        trainer2Panel.add(fieldTrainer2);
        trainer2Panel.add(Box.createVerticalStrut(10));
        trainer2Panel.add(pkdx2);
        trainer2Panel.add(gridPanel2);

        // Aggiunta dei pannelli alla griglia principale
        trainerPanel.add(trainer1Panel);
        trainerPanel.add(trainer2Panel);

        // Pannello per i pulsanti
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(battleButton, BorderLayout.EAST);
        buttonPanel.add(backButton, BorderLayout.WEST);

        // Aggiunta dei pannelli al `CharSelectPanel`
        this.add(trainerPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        // Listener per il pulsante "Inizia!"
        battleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	// Nome Trainer 1 e 2
            	String trainer1Name = fieldTrainer1.getText().trim();
                String trainer2Name = fieldTrainer2.getText().trim();

                // Squadra 1 e 2
                List<String> selected1 = gridPanel1.getSelectedPokemon();
                List<String> selected2 = gridPanel2.getSelectedPokemon();

                // Stampa per debug
                System.out.println("Allenatore 1: " + trainer1Name + " ha scelto: " + selected1);
                System.out.println("Allenatore 2: " + trainer2Name + " ha scelto: " + selected2);
                
                window.getGamePanel().setupTrainers(trainer1Name, selected1, trainer2Name, selected2);
                window.showPanel("Game");
                window.getGamePanel().startGameThread(); // Avvia il thread della battaglia
            }
        });

        // Listener per il pulsante "Indietro"
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.showPanel("Menu");
            }
        });
    }
}

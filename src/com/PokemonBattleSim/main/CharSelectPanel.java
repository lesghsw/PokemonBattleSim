package com.PokemonBattleSim.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@SuppressWarnings("serial")
public class CharSelectPanel extends JPanel {
	@SuppressWarnings("unused")
	private Window window;
    private JTextField fieldTrainer1, fieldTrainer2;
    private ToggleButtonGrid gridPanel1, gridPanel2;

    public CharSelectPanel(Window window) {
        this.window = window;

        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());

        // Etichette e campi per i nomi
        JLabel nomeTrainer1 = new JLabel("Nome Allenatore 1:");
        nomeTrainer1.setFont(new Font("Arial", Font.BOLD, 32));
        nomeTrainer1.setAlignmentX(Component.CENTER_ALIGNMENT);
        fieldTrainer1 = new JTextField(15);
        fieldTrainer1.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel nomeTrainer2 = new JLabel("Nome Allenatore 2:");
        nomeTrainer2.setFont(new Font("Arial", Font.BOLD, 32));
        nomeTrainer2.setAlignmentX(Component.CENTER_ALIGNMENT);
        fieldTrainer2 = new JTextField(15);
        fieldTrainer2.setFont(new Font("Arial", Font.PLAIN, 20));

        // Etichette Pokédex
        JLabel pkdx1 = new JLabel("Squadra Allenatore 1:");
        pkdx1.setFont(new Font("Arial", Font.BOLD, 36));
        pkdx1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel pkdx2 = new JLabel("Squadra Allenatore 2:");
        pkdx2.setFont(new Font("Arial", Font.BOLD, 36));
        pkdx2.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Griglie per la selezione Pokémon
        gridPanel1 = new ToggleButtonGrid();
        gridPanel2 = new ToggleButtonGrid();

        // Pulsanti di navigazione
        JButton battleButton = Pulzante.creaPulzante("Inizia!", "ref/Button.png", Color.WHITE);
        JButton backButton = Pulzante.creaPulzante("Indietro", "ref/Button.png", Color.WHITE);

        // Pannello per il layout a due colonne
        JPanel trainerPanel = new JPanel(new GridLayout(1, 3));
        trainerPanel.setBackground(Color.WHITE);

        // Pannello del primo allenatore
        JPanel trainer1Panel = new JPanel();
        trainer1Panel.setLayout(new BoxLayout(trainer1Panel, BoxLayout.Y_AXIS));
        trainer1Panel.setBackground(Color.WHITE);
        trainer1Panel.add(nomeTrainer1);
        trainer1Panel.add(Box.createVerticalStrut(10));
        trainer1Panel.add(fieldTrainer1);
        trainer1Panel.add(Box.createVerticalStrut(620));
        trainer1Panel.add(pkdx1);
        trainer1Panel.add(gridPanel1);
        trainer1Panel.add(Box.createVerticalStrut(10));

        // Pannello del secondo allenatore
        JPanel trainer2Panel = new JPanel();
        trainer2Panel.setLayout(new BoxLayout(trainer2Panel, BoxLayout.Y_AXIS));
        trainer2Panel.setBackground(Color.WHITE);
        trainer2Panel.add(nomeTrainer2);
        trainer2Panel.add(Box.createVerticalStrut(10));
        trainer2Panel.add(fieldTrainer2);
        trainer2Panel.add(Box.createVerticalStrut(620));
        trainer2Panel.add(pkdx2);
        trainer2Panel.add(gridPanel2);
        trainer2Panel.add(Box.createVerticalStrut(10));

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
                
                // Controlla se entrambi hanno messo 3 pkmn
                if (selected1.size() != 3 || selected2.size() != 3) {
                    JOptionPane.showMessageDialog(CharSelectPanel.this,
                        "Entrambi gli allenatori devono selezionare esattamente 3 Pokémon!",
                        "Attenzione!",
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }
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
    
    // Resetta nomi trainers e griglia dopo una partita
    public void resetSelection() {
        fieldTrainer1.setText("");
        fieldTrainer2.setText("");
        gridPanel1.resetSelection();
        gridPanel2.resetSelection();
    }
}

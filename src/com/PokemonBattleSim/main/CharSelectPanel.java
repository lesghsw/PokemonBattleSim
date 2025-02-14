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
        nomeTrainer1.setFont(new Font("Arial", Font.BOLD, 18));
        nomeTrainer1.setAlignmentX(Component.CENTER_ALIGNMENT);
        fieldTrainer1 = new JTextField(15);

        JLabel nomeTrainer2 = new JLabel("Nome Allenatore 2:");
        nomeTrainer2.setFont(new Font("Arial", Font.BOLD, 18));
        nomeTrainer2.setAlignmentX(Component.CENTER_ALIGNMENT);
        fieldTrainer2 = new JTextField(15);
        
        Dimension fixedHeight = new Dimension(Integer.MAX_VALUE, 40);
        fieldTrainer1.setPreferredSize(new Dimension(fieldTrainer1.getPreferredSize().width, 40));
        fieldTrainer1.setMinimumSize(new Dimension(fieldTrainer1.getPreferredSize().width, 40));
        fieldTrainer1.setMaximumSize(fixedHeight);

        fieldTrainer2.setPreferredSize(new Dimension(fieldTrainer2.getPreferredSize().width, 40));
        fieldTrainer2.setMinimumSize(new Dimension(fieldTrainer2.getPreferredSize().width, 40));
        fieldTrainer2.setMaximumSize(fixedHeight);

        // Etichette Pokédex
        JLabel pkdx1 = new JLabel("Squadra Allenatore 1:");
        pkdx1.setFont(new Font("Arial", Font.BOLD, 18));
        pkdx1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel pkdx2 = new JLabel("Squadra Allenatore 2:");
        pkdx2.setFont(new Font("Arial", Font.BOLD, 18));
        pkdx2.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Griglie per la selezione Pokémon
        gridPanel1 = new ToggleButtonGrid();
        gridPanel2 = new ToggleButtonGrid();

        // Crea Panel per gli sprite
        PokemonDisplayPanel displayPanel1 = new PokemonDisplayPanel(gridPanel1);
        PokemonDisplayPanel displayPanel2 = new PokemonDisplayPanel(gridPanel2);

        // Pulsanti di navigazione
        JButton battleButton = Pulzante.creaPulzante("Inizia!", "ref/Button.png", Color.WHITE);
        JButton backButton = Pulzante.creaPulzante("Indietro", "ref/Button.png", Color.WHITE);

        battleButton.setPreferredSize(new Dimension(150, 40));
        backButton.setPreferredSize(new Dimension(150, 40));

        // Pannello per il layout a due colonne (diviso verticalmente)
        JPanel trainerPanel = new JPanel(new GridLayout(1, 2));
        trainerPanel.setBackground(Color.WHITE);

        // Pannello del primo allenatore
        JPanel trainer1Panel = new JPanel();
        trainer1Panel.setLayout(new BoxLayout(trainer1Panel, BoxLayout.Y_AXIS));
        trainer1Panel.setBackground(Color.WHITE);
        trainer1Panel.add(nomeTrainer1);
        trainer1Panel.add(fieldTrainer1);
        trainer1Panel.add(Box.createVerticalStrut(10));
        trainer1Panel.add(pkdx1);
        trainer1Panel.add(displayPanel1); // Added display panel for sprites
        trainer1Panel.add(Box.createVerticalStrut(10));
        trainer1Panel.add(gridPanel1);

        // Pannello del secondo allenatore
        JPanel trainer2Panel = new JPanel();
        trainer2Panel.setLayout(new BoxLayout(trainer2Panel, BoxLayout.Y_AXIS));
        trainer2Panel.setBackground(Color.WHITE);
        trainer2Panel.add(nomeTrainer2);
        trainer2Panel.add(fieldTrainer2);
        trainer2Panel.add(Box.createVerticalStrut(10));
        trainer2Panel.add(pkdx2);
        trainer2Panel.add(displayPanel2); // Added display panel for sprites
        trainer2Panel.add(Box.createVerticalStrut(10));
        trainer2Panel.add(gridPanel2);

        // Aggiunta dei pannelli alla griglia principale
        trainerPanel.add(trainer1Panel);
        trainerPanel.add(trainer2Panel);

        // Pannello per i pulsanti
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(battleButton, BorderLayout.EAST);
        buttonPanel.add(backButton, BorderLayout.WEST);

        // Aggiunta dei pannelli al CharSelectPanel
        this.add(trainerPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        // Timer per aggiornare (repaint) periodicamente i display dei Pokémon
        new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayPanel1.repaint();
                displayPanel2.repaint();
            }
        }).start();

        // Listener per il pulsante "Inizia!"
        battleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Nome Trainer 1 e 2
                String trainer1Name = fieldTrainer1.getText().trim();
                String trainer2Name = fieldTrainer2.getText().trim();

                // Squadra 1 e 2 (lista dei nomi selezionati)
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

    // Resetta nomi trainers e griglia dopo una partita
    public void resetSelection() {
        fieldTrainer1.setText("");
        fieldTrainer2.setText("");
        gridPanel1.resetSelection();
        gridPanel2.resetSelection();
    }

    // Classe interna per disegnare i Pokémon selezionati
    private class PokemonDisplayPanel extends JPanel {
        private ToggleButtonGrid grid;

        public PokemonDisplayPanel(ToggleButtonGrid grid) {
            this.grid = grid;
            setPreferredSize(new Dimension(200, 100)); // Imposta un'altezza fissa
            setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            
            List<String> selected = grid.getSelectedPokemon();

            int count = selected.size();
            if (count == 0) return;
            
            int baseWidth = 64;
            int baseHeight = 64;
            int scale = 3;
            int scaledWidth = baseWidth * scale;
            int scaledHeight = baseHeight * scale;
            
            // Calcolo spacing per avere distanza costante tra gli sprite
            int spacing = (getWidth() - count * scaledWidth) / (count + 1);
            int y = (getHeight() - scaledHeight) / 2;
            int x = spacing;
            
            for (String name : selected) {
                Pokemon p = PokemonPool.generatePokemon(name);
                if (p != null) {
                    String spritePath = p.getSpriteFront();
                    System.out.println("Drawing " + name + " using sprite: " + spritePath);
                    // Carico immagine e eseguo upscaling
                    Image img = Toolkit.getDefaultToolkit().getImage(getClass().getResource(spritePath));
                    if (img != null) {
                        g2d.drawImage(img, x, y, scaledWidth, scaledHeight, this);
                    } else {
                        System.out.println("Resource not found: " + spritePath);
                    }
                } else {
                    System.out.println("No Pokémon generated for: " + name);
                }
                x += scaledWidth + spacing;
            }
        }
    }
}

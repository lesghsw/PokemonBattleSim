package com.PokemonBattleSim.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WinPanel extends JPanel {
    private Window window;
    private JLabel winnerLabel;

    public WinPanel(Window window) {
        this.window = window;

        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());

        winnerLabel = new JLabel("", JLabel.CENTER); // Vuoto per aggiornarlo dinamicamente
        winnerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        winnerLabel.setForeground(Color.RED);

        JButton continueButton = Pulzante.creaPulzante("Continua!", "ref/Button.png", Color.WHITE);
        continueButton.setMaximumSize(new Dimension(300, 40));
        continueButton.setAlignmentX(CENTER_ALIGNMENT);

        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.getCharSelectPanel().resetSelection();
                window.showPanel("Menu");
            }
        });
        
        // Aggiunge testo
        this.add(winnerLabel, BorderLayout.CENTER);
        
        // Aggiunge pulsante al pannello e lo mette al centro in basso
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(continueButton);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    // Per aggiornare dinamicamente nome del vincitore
    public void setWinner(String winnerName) {
        winnerLabel.setText(winnerName + " ha vinto la battaglia!");
    }
}
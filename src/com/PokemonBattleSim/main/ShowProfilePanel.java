package com.PokemonBattleSim.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@SuppressWarnings("serial")
public class ShowProfilePanel extends JPanel {
    private Window window;

    public ShowProfilePanel(Window window) {
        this.window = window;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.WHITE);
        
        loadProfileButtons();
        
        JButton backButton = Pulzante.creaPulzante("Indietro", "ref/Button.png", Color.WHITE);
        backButton.setMaximumSize(new Dimension(300, 40));
        backButton.setAlignmentX(CENTER_ALIGNMENT);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.showPanel("Menu");
            }
        });
        
        this.add(Box.createVerticalStrut(10));
        this.add(backButton);
    }

    private void loadProfileButtons() {
        List<String> profileNames = SaveManager.getAllProfileFileNames(); // Prende tutti i nomi di salvataggi e crea pulsanti
        for (String profileName : profileNames) {
            JButton profileButton = Pulzante.creaPulzante(profileName, "ref/Button.png", Color.WHITE);
            profileButton.setMaximumSize(new Dimension(300, 40));
            profileButton.setAlignmentX(CENTER_ALIGNMENT);
            
            profileButton.addActionListener(e -> showProfileDetails(profileName));
            
            this.add(Box.createVerticalStrut(10));
            this.add(profileButton);
        }
    }

    private void showProfileDetails(String profileName) {
    	// Carica profilo a partire dal nome
        PlayerProfile profile = SaveManager.loadPlayerProfile(profileName);
        // Messaggio di errore in caso di profilo inesistente
        if (profile == null) {
            JOptionPane.showMessageDialog(this, "Errore nel caricamento del profilo!", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Stringa per avere corretta formattazione nella finestrella
        String message = "Profilo: " + profileName + "\n"
                + "Partite vinte: " + profile.getWonCount() + "\n"
                + "Partite perse: " + profile.getLostCount() + "\n"
                + "Partite giocate: " + profile.getPlayedCount();
        
        // Finestrella di dialogo
        JOptionPane.showMessageDialog(this, message, "Dettagli Profilo", JOptionPane.INFORMATION_MESSAGE);
    }
}
package com.PokemonBattleSim.main;

import javax.swing.*;
import java.awt.*;

public class ToggleButtonGrid extends JPanel {

    private String[] pokemonNames = {"Bulbasaur", "Charmander", "Squirtle"}; // Nomi dei pulsanti
    private JToggleButton[] buttons; // Array per i pulsanti
    private JToggleButton lastOnButton = null; // Traccia l'ultimo pulsante attivo

    public ToggleButtonGrid() {
        setLayout(new GridLayout(1, pokemonNames.length, 5, 5)); // Una riga con spaziatura
        buttons = new JToggleButton[pokemonNames.length];

        for (int i = 0; i < pokemonNames.length; i++) {
            JToggleButton toggleButton = new JToggleButton(pokemonNames[i]); // Nome del Pokémon sul pulsante
            buttons[i] = toggleButton;

            toggleButton.addActionListener(e -> handleButtonClick(toggleButton));
            this.add(toggleButton);
        }
    }

    private void handleButtonClick(JToggleButton button) {
        if (button.isSelected()) {
            if (lastOnButton != null && lastOnButton != button) {
                lastOnButton.setSelected(false); // Spegne il precedente pulsante
            }
            lastOnButton = button; // Aggiorna l'ultimo pulsante attivo
        } else if (lastOnButton == button) {
            lastOnButton = null; // Nessun pulsante attivo
        }
    }

    private String getButtonName(JToggleButton button) {
        for (int i = 0; i < pokemonNames.length; i++) {
            if (buttons[i] == button) {
                return pokemonNames[i]; // Restituisce il nome del Pokémon
            }
        }
        return "";
    }

    public JToggleButton getLastOnButton() {
        return lastOnButton;
    }
}
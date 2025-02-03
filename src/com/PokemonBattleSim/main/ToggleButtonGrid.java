package com.PokemonBattleSim.main;

import javax.swing.*;
import java.awt.*;

public class ToggleButtonGrid extends JPanel {

    private String[] pokemonNames = {"Bulbasaur","Ivysaur", "Venusaur", "Charmander", "Charmeleon", "Charizard", "Squirtle", "Wartortle", "Blastoise"}; // Nomi dei pulsanti
    private JToggleButton[] buttons; // Array per i pulsanti
    private int maxOnButtons = 3;
    private int currentOnButtons = 0;

    public ToggleButtonGrid() {
        setLayout(new GridLayout(3, 3, 5, 5)); // Una riga con spaziatura
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
        	if (currentOnButtons < maxOnButtons) {
                currentOnButtons++;
            } else {
                button.setSelected(false);
            }
        } else {
            currentOnButtons--;
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
}
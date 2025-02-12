package com.PokemonBattleSim.main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ToggleButtonGrid extends JPanel {
	private static final long serialVersionUID = 8563539763325249390L;
	
	private String[] pokemonNames = {"Bulbasaur","Charmander", "Squirtle", "Robert", "Urlox", "Cordol", "Sproloquio", "Domenico", "Bentley"}; 
    private JToggleButton[] buttons; 
    private int maxOnButtons = 3;
    private int currentOnButtons = 0;
    private List<String> selectedPokemon;

    public ToggleButtonGrid() {
        setLayout(new GridLayout(3, 3, 5, 5));
        buttons = new JToggleButton[pokemonNames.length];
        selectedPokemon = new ArrayList<>();

        for (int i = 0; i < pokemonNames.length; i++) {
            JToggleButton toggleButton = new JToggleButton(pokemonNames[i]); 
            buttons[i] = toggleButton;

            toggleButton.addActionListener(e -> handleButtonClick(toggleButton));
            this.add(toggleButton);
        }
    }

    private void handleButtonClick(JToggleButton button) {
        String name = button.getText();
        if (button.isSelected()) {
            if (currentOnButtons < maxOnButtons) {
                currentOnButtons++;
                selectedPokemon.add(name);
            } else {
                button.setSelected(false);
            }
        } else {
            currentOnButtons--;
            selectedPokemon.remove(name);
        }
    }

    public List<String> getSelectedPokemon() {
        return new ArrayList<>(selectedPokemon);
    }
    
    // Per resettare i pulsanti dopo una partita
    public void resetSelection() {
        for (JToggleButton button : buttons) {
            button.setSelected(false);
        }
        selectedPokemon.clear();
        currentOnButtons = 0;
    }
}
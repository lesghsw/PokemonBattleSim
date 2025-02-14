package com.PokemonBattleSim.main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe per la gestione di una griglia di pulsanti "toggle".
 * Permette di selezionare fino ad un massimo di 3 Pokémon tra quelli disponibili.
 * 
 * @author Giampietri2108347
 */
@SuppressWarnings("serial")
public class ToggleButtonGrid extends JPanel {
	private String[] pokemonNames = {"Bulbasaur","Charmander", "Squirtle", "Robert", "Urlox", "Cordol", "Sproloquio", "Domenico", "Bentley"}; 
    private JToggleButton[] buttons; 
    private int maxOnButtons = 3;
    private int currentOnButtons = 0;
    private List<String> selectedPokemon;

    /**
     * Costruttore della classe ToggleButtonGrid.
     * Inizializza la griglia dei pulsanti con i nomi dei Pokémon.
     */
    public ToggleButtonGrid() {
        setLayout(new GridLayout(3, 3, 5, 5));
        setBackground(Color.WHITE);
        buttons = new JToggleButton[pokemonNames.length];
        selectedPokemon = new ArrayList<>();

        for (int i = 0; i < pokemonNames.length; i++) {
            // Percorsi delle immagini
            String normalIcon = "ref/Button.png";
            String selectedIcon = "ref/SelectedButton.png";
            JToggleButton toggleButton = Pulzante.creaTogglePulzante(pokemonNames[i], normalIcon, selectedIcon, Color.WHITE);
            buttons[i] = toggleButton;

            toggleButton.addActionListener(e -> handleButtonClick(toggleButton));
            this.add(toggleButton);
        }
    }

    /**
     * Gestisce il click del pulsante per permettere la selezione dei Pokémon.ù
     * 
     * @param button Pulsante selezionato (o deselezionato).
     */
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

    /**
     * Restituisce la lista dei Pokémon selezionati.
     */
    public List<String> getSelectedPokemon() {
        return new ArrayList<>(selectedPokemon);
    }
    
    /**
     * Resetta la selezione dei pulsanti deselezionandoli tutti.
     */
    public void resetSelection() {
        for (JToggleButton button : buttons) {
            button.setSelected(false);
        }
        selectedPokemon.clear();
        currentOnButtons = 0;
    }
}
package com.PokemonBattleSim.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToggleButtonGrid extends JPanel {

    private int rows;
    private int cols;
    private JToggleButton[][] buttons;
    private int maxOnButtons = 6;
    private int currentOnButtons = 0;

    public ToggleButtonGrid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        // GridLayout per posizionare i pulsanti
        setLayout(new GridLayout(rows, cols, 5, 5)); // righe, colonne, spaziatura orizzontale, spaziatura verticale

        buttons = new JToggleButton[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                JToggleButton toggleButton = new JToggleButton("OFF");
                buttons[i][j] = toggleButton;

                toggleButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleButtonClick(toggleButton);
                    }
                });
                this.add(toggleButton);
            }
        }
    }

    private void handleButtonClick(JToggleButton button) {
        if (button.isSelected()) {
            if (currentOnButtons < maxOnButtons) {
                button.setText("ON");
                currentOnButtons++;
            } else {
                button.setSelected(false);
                button.setText("OFF");
            }
        } else {
            button.setText("OFF");
            currentOnButtons--;
        }
    }

    public boolean isButtonOn(int row, int col) {
        return buttons[row][col].isSelected();
    }
}

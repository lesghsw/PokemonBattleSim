package com.PokemonBattleSim.main;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@SuppressWarnings("serial")
public class WinPanel extends JPanel {
	@SuppressWarnings("unused")
    private Window window;
    private JLabel winnerLabel;
    private JPanel teamPanel;

    public WinPanel(Window window) {
        this.window = window;
        this.setBackground(Color.WHITE);
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 0, 20, 0);

        // Etichetta per il vincitore
        winnerLabel = new JLabel("", JLabel.CENTER);
        winnerLabel.setFont(new Font("Arial", Font.BOLD, 36));
        winnerLabel.setForeground(Color.RED);

        // Pannello centrale con solo il testo del vincitore
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);
        centerPanel.add(winnerLabel, BorderLayout.CENTER);

        // Pannello per gli sprite della squadra vincente
        teamPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        teamPanel.setBackground(Color.WHITE);

        JPanel teamContainer = new JPanel(new GridBagLayout());
        teamContainer.setBackground(Color.WHITE);
        teamContainer.add(teamPanel, new GridBagConstraints());

        // Pulsante per continuare
        JButton continueButton = Pulzante.creaPulzante("Continua!", "ref/Button.png", Color.WHITE);
        continueButton.addActionListener(e -> {
            window.getCharSelectPanel().resetSelection();
            window.showPanel("Menu");
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(continueButton);

        // Aggiunta componenti con GridBagLayout
        gbc.gridy = 0;
        this.add(centerPanel, gbc);

        gbc.gridy = 1;
        this.add(teamContainer, gbc);

        gbc.gridy = 2;
        this.add(buttonPanel, gbc);
    }

    public void setWinner(String winnerName) {
        winnerLabel.setText("  " + winnerName + " ha vinto la battaglia! ");
    }

    public void setWinnerTeam(List<Pokemon> team) {
        teamPanel.removeAll();

        int newWidth = 256; // Larghezza del resize
        int newHeight = 256; // Altezza del resize

        for (Pokemon pkmn : team) {
            ImageIcon originalIcon = new ImageIcon(getClass().getResource(pkmn.getSpriteFront()));

            // Scala l'immagine mantenendo il rapporto di aspetto
            Image originalImage = originalIcon.getImage();
            Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_REPLICATE);	// "SCALE" specifica per resize degli sprite

            ImageIcon resizedIcon = new ImageIcon(resizedImage);
            JLabel spriteLabel = new JLabel(resizedIcon);
            teamPanel.add(spriteLabel);
        }
        teamPanel.revalidate();
        teamPanel.repaint();
    }

}
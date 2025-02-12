package com.PokemonBattleSim.main;

import javax.swing.*;
import java.awt.*;

public class Pulzante {

	public static JButton creaPulzante(String text, String iconPath, Color textColor) {
	
		ImageIcon buttonImage = new ImageIcon(Pulzante.class.getResource(iconPath));
				
		// Crea il pulsante
        JButton customButton = new JButton(text);
        customButton.setIcon(buttonImage); // Imposta l'immagine come icona
        customButton.setContentAreaFilled(false); // Rimuove il background predefinito QUESTO ME SA
        customButton.setBorderPainted(false); // Rimuove i bordi predefiniti
        customButton.setFocusPainted(false); // Rimuove il bordo di focus
        customButton.setMargin(null); // Rimuove il margine
        
     // Imposta la posizione del testo
        customButton.setHorizontalTextPosition(SwingConstants.CENTER); // Testo centrato orizzontalmente
        customButton.setVerticalTextPosition(SwingConstants.CENTER);   // Testo centrato verticalmente

     // Imposta il font del testo
        customButton.setFont(new Font("Arial", Font.BOLD, 16)); // Cambia il font
        customButton.setForeground(textColor); // Cambia il colore del testo
        
        // Imposta le dimensioni del pulsante in base all'immagine
        customButton.setPreferredSize(new Dimension(
            buttonImage.getIconWidth(),
            buttonImage.getIconHeight()
        ));
        
        return customButton;
	}
}
	package com.PokemonBattleSim.main;
	
	import javax.swing.*;
	import java.awt.*;
	
	/**
	 * Classe per la creazione di pulsanti personalizzati.
	 * Questa classe fornisce metodi per creare pulsanti "normali" e pulsanti "toggle"
	 * con immagini di sfondo e testo personalizzato.
	 * 
	 * @author Giampietri2108347
	 */
	public class Pulzante {
	
		/**
	     * Crea un pulsante "normale" personalizzato che ha come sfondo l'icona stessa e testo centrato.
	     *
	     * @param text Il testo da visualizzare sul pulsante.
	     * @param iconPath Il percorso dell'icona da utilizzare.
	     * @param textColor Il colore del testo.
	     */
		public static JButton creaPulzante(String text, String iconPath, Color textColor) {
		
			ImageIcon buttonImage = new ImageIcon(Pulzante.class.getResource(iconPath));
					
			// Crea il pulsante
	        JButton customButton = new JButton(text);
	        customButton.setIcon(buttonImage); // Imposta l'immagine come icona
	        customButton.setContentAreaFilled(false); // Rimuove il background predefinito
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
		
		/**
	     * Crea un pulsante "toggle" personalizzato che ha come sfondo l'icona stessa e testo centrato.
	     *
	     * @param text Il testo da visualizzare sul pulsante.
	     * @param iconPath Il percorso dell'icona da utilizzare normalmente.
	     * @param selectedIconPath Il percorso dell'icona da utilizzare quando il pulsante è selezionato.
	     * @param textColor Il colore del testo.
     	*/
		public static JToggleButton creaTogglePulzante(String text, String iconPath, String selectedIconPath, Color textColor) {
		    ImageIcon buttonImage = new ImageIcon(Pulzante.class.getResource(iconPath));
		    ImageIcon selectedButtonImage = new ImageIcon(Pulzante.class.getResource(selectedIconPath));
		    JToggleButton customToggleButton = new JToggleButton(text);
		    customToggleButton.setIcon(buttonImage);
		    customToggleButton.setSelectedIcon(selectedButtonImage); 
		    customToggleButton.setContentAreaFilled(false);
		    customToggleButton.setBorderPainted(false);
		    customToggleButton.setFocusPainted(false);
		    customToggleButton.setMargin(null);
		    customToggleButton.setHorizontalTextPosition(SwingConstants.CENTER);
		    customToggleButton.setVerticalTextPosition(SwingConstants.CENTER);
		    customToggleButton.setFont(new Font("Arial", Font.BOLD, 16));
		    customToggleButton.setForeground(textColor);
		    customToggleButton.setPreferredSize(new Dimension(
		        buttonImage.getIconWidth(),
		        buttonImage.getIconHeight()
		    ));
	
		    return customToggleButton;
		}
	}
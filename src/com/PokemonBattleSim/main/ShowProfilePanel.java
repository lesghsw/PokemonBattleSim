package com.PokemonBattleSim.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("serial")
public class ShowProfilePanel extends JPanel {
	@SuppressWarnings("unused")
	private Window window;
	private JPanel profilesContainer;
	private JScrollPane scrollPane;

	public ShowProfilePanel(Window window) {
		this.window = window;

		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);

		// Creo container
		profilesContainer = new JPanel();
		profilesContainer.setLayout(new BoxLayout(profilesContainer, BoxLayout.Y_AXIS));
		profilesContainer.setBackground(Color.WHITE);

		// Creo lo scrollPane per contenere pulsanti di numero indeterminato
		scrollPane = new JScrollPane(profilesContainer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		this.add(scrollPane, BorderLayout.CENTER);

		// Pannello per il pulsante indietro
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.WHITE);
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JButton backButton = Pulzante.creaPulzante("Indietro", "ref/Button.png", Color.WHITE);
		backButton.addActionListener((ActionEvent e) -> window.showPanel("Menu"));
		bottomPanel.add(backButton);
		this.add(bottomPanel, BorderLayout.SOUTH);

		// Carica subito tutti i pulsanti
		loadProfileButtons();
	}

	// Ricarica la lista dei profili,
	// così da poter aggiornare il panel post battaglia
	public void refreshPanel() {
		profilesContainer.removeAll();
		loadProfileButtons();
		profilesContainer.revalidate();
		profilesContainer.repaint();
	}

	private void loadProfileButtons() {
		List<String> profileNames = SaveManager.getAllProfileFileNames(); // Prende tutti i nomi dei salvataggi
		Collections.sort(profileNames); // Sorting ordine alfabetico
		for (String profileName : profileNames) {
			// Crea un Panel orizzontale
			final JPanel rowPanel = new JPanel();
			rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
			rowPanel.setBackground(Color.WHITE);

			// Pulsante per vedere i dettagli del profilo
			JButton profileButton = Pulzante.creaPulzante(profileName, "ref/Button.png", Color.WHITE);
			profileButton.setAlignmentX(Component.LEFT_ALIGNMENT);
			profileButton.addActionListener(e -> showProfileDetails(profileName));

			// Pulsante cancella
			JButton deleteButton = Pulzante.creaPulzante("Elimina", "ref/Button.png", Color.WHITE);
			deleteButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
			deleteButton.addActionListener(e -> {
				int confirm = JOptionPane.showConfirmDialog(this,
						"Sei sicuro di voler eliminare il profilo '" + profileName + "'?", "Conferma Eliminazione",
						JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					boolean success = SaveManager.deleteProfile(profileName);
					if (success) {
						JOptionPane.showMessageDialog(this, "Profilo eliminato con successo!", "Eliminazione",
								JOptionPane.INFORMATION_MESSAGE);
						// Ricarica il pannello per rimuovere il profilo eliminato
						refreshPanel();
					} else {
						JOptionPane.showMessageDialog(this, "Errore durante l'eliminazione del profilo!", "Errore",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			});

			// Aggiungi i pulsanti al Panel con un gap
			rowPanel.add(profileButton);
			rowPanel.add(Box.createHorizontalStrut(10));
			rowPanel.add(deleteButton);

			// Aggiungi gap verticale
			profilesContainer.add(Box.createVerticalStrut(10));
			profilesContainer.add(rowPanel);
		}
	}

	private void showProfileDetails(String profileName) {
		// Carica profilo a partire dal nome
		PlayerProfile profile = SaveManager.loadPlayerProfile(profileName);

		// Messaggio di errore in caso di profilo inesistente
		if (profile == null) {
			JOptionPane.showMessageDialog(this, "Errore nel caricamento del profilo!", "Errore",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Stringa per avere corretta formattazione nella finestrella
		String message = "Profilo: " + profileName + "\n" + "Partite vinte: " + profile.getWonCount() + "\n"
				+ "Partite perse: " + profile.getLostCount() + "\n" + "Partite giocate: " + profile.getPlayedCount();

		// Finestrella di dialogo
		JOptionPane.showMessageDialog(this, message, "Dettagli Profilo", JOptionPane.INFORMATION_MESSAGE);
	}
}

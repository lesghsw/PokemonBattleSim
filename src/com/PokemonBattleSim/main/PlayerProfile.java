package com.PokemonBattleSim.main;

import java.io.Serializable;
/**
 * Classe per creare i profili utente da zero, così da aggiornarli e salvarli, oppure caricarli da uno già esistente.
 * <p>
 * Per permettere il salvataggio implementa Serializable
 * 
 * @author Aloisi2107981
 * 
 * @see Serializable
 */
public class PlayerProfile implements Serializable {
	
	/**
	 * Versione della classe, indentificatore univoco così da identificare errori in caso si cerchi di caricare profili di una versione non compatibile con quella implementata attualmente.
	 */
	private static final long serialVersionUID = 1L;
	
	int gWon, gLost, gPlayed;
	
	/**
	 *  Costruttore senza arogmenti implicito, poiché le variabili avranno il valore default di 0
	 */
	
	
	/**
	 * Aggiorna il profilo aggiungendo una partita giocata e una vinta
	 */
	public void playerWon() {
		this.gWon += 1;
		this.gPlayed += 1;
	}
	
	/**
	 * Aggiorna il profilo aggiungendo una partita giocata e una persa
	 */
	public void playerLost() {
		this.gLost += 1;
		this.gPlayed += 1;
	}
	
	/**
	 * Getter per il numero di partite vinte
	 * 
	 * @return Numero di partite vinte
	 */
	public int getWonCount() {
		return this.gWon;
	}
	
	/**
	 * Getter per il numero di partite perse 
	 * 
	 * @return Numero di partite perse
	 */
	public int getLostCount() {
		return this.gLost;
	}
	
	/**
	 * Getter per il numero di partite giocate
	 * 
	 * @return Numero di partite giocate
	 */
	public int getPlayedCount() {
		return this.gPlayed;
	}
}

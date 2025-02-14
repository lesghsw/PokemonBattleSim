package com.PokemonBattleSim.main;

/**
 * Classe principale per l'avvio del simulatore di battaglia Pokémon.
 * Questa classe inizializza la finestra principale del gioco.
 * 
 * @author Giampietri2108347
 */
public class JPokeBattle {
	
	/**
     * Costruttore della classe JPokeBattle.
     * Avvia la finestra principale del gioco.
     */
	public JPokeBattle() {
		
		new Window();
	}

	/**
     * Metodo principale che avvia il programma.
     */
	public static void main(String args[]) {
		
		new JPokeBattle();
	}
}

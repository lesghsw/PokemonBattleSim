package com.PokemonBattleSim.main;

/**
 * Classe per creare una battaglia, contiene un metodo che permette l'esecuzione di un ciclo di battaglia
 * 
 * @author Aloisi2107981
 * 
 * @see Trainer
 */
public class Battle {
	private Trainer player1, player2;
	
	/**
	 * Costruttore battaglia tra due trainer
	 * 
	 * @param player1 Il trainer che fungerà da Player 1
	 * @param player2 Il trainer che fungerà da Player 2
	 */
	public Battle(Trainer player1, Trainer player2) {
		this.player1 = player1;
		this.player2 = player2;
	}
	
	/**
	 * Fa girare un ciclo di battaglia, non permette ad un Pokèmon di attaccare se è morto o se non ha una mossa attiva selezionata.
	 * Fa attaccare per primo il Pokémon con la velocità maggiore.
	 * In caso di morte di uno dei due Pokémon ritorna un valore diverso da 0
	 * 
	 * @return Esito ciclo di battaglia (0: ciclo senza avvenimenti particolari, 1: Morto Pokémon del Player 1, 2: Morto Pokémon del Player 2)
	 * 
	 * @see Pokemon
	 */
	public int runBattle() {
		Pokemon pok1 = this.player1.getActivePokemon(), pok2 = this.player2.getActivePokemon();
		
		if (pok1.getSpd() > pok2.getSpd()) {
			if(pok1.getActiveMove() != null) pok1.attack(pok2);
			if(pok2.getHp() > 0.0f && pok2.getActiveMove() != null) pok2.attack(pok1);
		} else {
			if(pok2.getActiveMove() != null) pok2.attack(pok1);
			if(pok1.getHp() > 0.0f && pok1.getActiveMove() != null) pok1.attack(pok2);
		}
		
		if(pok1.getHp() <= 0.0f) {
			player1.pokemonDied();
			return 1;
		}
		
		if(pok2.getHp() <= 0.0f) {
			player2.pokemonDied();
			return 2;
		}
		
		return 0;
	}
}
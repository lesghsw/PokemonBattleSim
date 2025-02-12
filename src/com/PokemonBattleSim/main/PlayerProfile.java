package com.PokemonBattleSim.main;

import java.io.Serializable;

public class PlayerProfile implements Serializable {
	private static final long serialVersionUID = 1L;
	
	int gWon, gLost, gPlayed;
	
	// Costruttore senza arogmenti implicito,
	// poiché le variabili avranno il valore default di 0

	public void playerWon() {
		this.gWon += 1;
		this.gPlayed += 1;
	}
	
	public void playerLost() {
		this.gLost += 1;
		this.gPlayed += 1;
	}
	
	public int getWonCount() {
		return this.gWon;
	}
	
	public int getLostCount() {
		return this.gLost;
	}
	
	public int getPlayedCount() {
		return this.gPlayed;
	}
}

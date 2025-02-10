package com.PokemonBattleSim.main;
import java.io.Serializable;

public class PlayerProfile implements Serializable {
	int gWon, gLost, gPlayed;
	
	// Costruttore senza arogmenti implicito,
	// poiché le variabili avranno il valore default di 0
	
	void playerWon() {
		this.gWon += 1;
		this.gPlayed += 1;
	}
	
	void playerLost() {
		this.gLost += 1;
		this.gPlayed += 1;
	}
	
	int getWonCount() {
		return this.gWon;
	}
	
	int getLostCount() {
		return this.gLost;
	}
	
	int getPlayedCount() {
		return this.gPlayed;
	}
}

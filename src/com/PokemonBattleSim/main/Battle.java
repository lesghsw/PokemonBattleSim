package com.PokemonBattleSim.main;
import java.util.List;

public class Battle {
	private Trainer player1, player2;
	
	public Battle(Trainer player1, Trainer player2) {
		this.player1 = player1;
		this.player2 = player2;
	}
	
	public int runBattle() {
		Pokemon pok1 = this.player1.getActivePokemon(), pok2 = this.player2.getActivePokemon();
		
		if (pok1.getSpd() > pok2.getSpd()) {
			if(pok1.getActiveMove() != null) pok1.attack(pok2);
			System.out.println(pok2.getName() + " = " + pok2.getHp());
			if(pok2.getHp() > 0.0f && pok2.getActiveMove() != null) pok2.attack(pok1);
			System.out.println(pok1.getName() + " = " + pok1.getHp());
		} else {
			if(pok2.getActiveMove() != null) pok2.attack(pok1);
			System.out.println(pok1.getName() + " = " + pok1.getHp());
			if(pok1.getHp() > 0.0f && pok1.getActiveMove() != null) pok1.attack(pok2);
			System.out.println(pok2.getName() + " = " + pok2.getHp());
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
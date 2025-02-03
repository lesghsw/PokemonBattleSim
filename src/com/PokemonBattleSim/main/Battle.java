package com.PokemonBattleSim.main;

public class Battle {
	private Trainer player1, player2;
	
	public Battle(Trainer player1, Trainer player2) {
		this.player1 = player1;
		this.player2 = player2;
	}
	
	public void runBattle() {
		Pokemon pok1 = this.player1.getActivePokemon(), pok2 = this.player2.getActivePokemon();
		
		if (pok1.getSpd() > pok2.getSpd()) {
			pok1.attack(pok2);
			System.out.println(pok2.getName() + " = " + pok2.getHp());
			pok2.attack(pok1);
			System.out.println(pok1.getName() + " = " + pok1.getHp());
		} else {
			pok2.attack(pok1);
			System.out.println(pok1.getName() + " = " + pok1.getHp());
			pok1.attack(pok2);
			System.out.println(pok2.getName() + " = " + pok2.getHp());
		}
	}
}

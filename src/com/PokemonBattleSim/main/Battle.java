package com.PokemonBattleSim.main;

public class Battle {
	private Trainer trainer1, trainer2;
	
	public Battle(Trainer trainer1, Trainer trainer2) {
		this.trainer1 = trainer1;
		this.trainer2 = trainer2;
	}
	
	public void runBattle() {
		Pokemon pok1 = this.trainer1.getActivePokemon(), pok2 = this.trainer2.getActivePokemon();
		
		if (pok1.getSpd() > pok2.getSpd()) {
			pok1.attack(pok2);
			System.out.println("Pok2 = " + pok1.getHp());
			pok2.attack(pok1);
			System.out.println("Pok1 = " + pok2.getHp());
		} else {
			pok2.attack(pok1);
			System.out.println("Pok1 = " + pok1.getHp());
			pok1.attack(pok2);
			System.out.println("Pok2 = " + pok2.getHp());
		}
	}
}

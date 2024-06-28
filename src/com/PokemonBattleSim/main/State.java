package com.PokemonBattleSim.main;

public abstract class State {
	
	KeyHandler keyH;

	public State(KeyHandler keyH) {
		
		this.keyH = keyH;
	}
}

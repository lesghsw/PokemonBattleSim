package com.PokemonBattleSim.main;

import java.util.ArrayList;
import java.util.List;

public class Trainer {
	private String name;
	private List<Pokemon> pokemons = new ArrayList<Pokemon>();
	private Pokemon activePokemon;
	
	public Trainer(String name, Pokemon pok1, Pokemon pok2, Pokemon pok3) {
		this.name = name;
		this.pokemons.add(pok1);
		this.pokemons.add(pok2);
		this.pokemons.add(pok3);
		this.activePokemon = this.pokemons.get(0);
	}

	public Pokemon getActivePokemon() {
		return this.activePokemon;
	}

	public String getName() {
		return this.name;
	}

	public void setActivePokemon(Pokemon activePokemon) {
		this.activePokemon = activePokemon;
	}
	
	
}

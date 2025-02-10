package com.PokemonBattleSim.main;

import java.util.ArrayList;
import java.util.List;

public class Trainer {
	private String name;
	private List<Pokemon> pokemons = new ArrayList<Pokemon>();
	private Pokemon activePokemon;
	private int deadPokemonCount;
	
	public Trainer() {}
	
	public Trainer(String name) {
		this.name = name;
	}
	
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
	
	public List<Pokemon> getPokemonList() {
		return new ArrayList<>(this.pokemons);
	}

	public String getName() {
		return this.name;
	}
	
	public int getDeadPokemonCount() {
		return this.deadPokemonCount;
	}

	public void setActivePokemon(Pokemon activePokemon) {
		this.activePokemon = activePokemon;
	}
	
	public void setActivePokemon(int idx) {
		this.activePokemon = pokemons.get(idx);
	}
	
	public void addPokemon(Pokemon newPokemon) {
		this.pokemons.add(newPokemon);
		if (this.pokemons.size() == 1) {
			this.activePokemon = newPokemon;
		}
	}
	
	public void pokemonDied() {
		this.deadPokemonCount += 1;
		if (this.deadPokemonCount != 3) {
			for(Pokemon pokemon : this.pokemons) {
				if (pokemon.getHp() > 0.0f) {
					this.setActivePokemon(pokemon);
				}
			}
		} else { this.activePokemon = null; }
	}
}
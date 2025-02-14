package com.PokemonBattleSim.main;

import java.util.ArrayList;
import java.util.List;

public class Trainer {
	private String name;
	private List<Pokemon> pokemons = new ArrayList<Pokemon>();
	private Pokemon activePokemon;
	private int deadPokemonCount;
	private PlayerProfile trainerProfile;
	
	public Trainer(String name) {
		this.name = name;
		
		if (SaveManager.profileExists(name)) {
			this.trainerProfile = SaveManager.loadPlayerProfile(name);
		} else {
			this.trainerProfile = new PlayerProfile();
		}
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
	
	public PlayerProfile getTrainerProfile() {
		return this.trainerProfile;
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
		}
	}
	
	public void trainerWon() {
		this.trainerProfile.playerWon();
	}
	
	public void trainerLost() {
		this.trainerProfile.playerLost();
	}
	
	public void saveTrainerProfile() {
		SaveManager.savePlayerProfile(this.trainerProfile, this.name);
	}
}
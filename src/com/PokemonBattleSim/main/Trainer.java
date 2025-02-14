package com.PokemonBattleSim.main;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe per creare istanze di Trainer, i Trainer hanno nome, Pokémon (lista), PokemonAttivo, numero di Pokémon morti, e il profilo corrispondente al Trainer
 * 
 * @author Aloisi2107981
 * 
 * @see Pokemon
 * @see PlayerProfile
 * @see SaveManager
 * @see Pokemon 
 */
public class Trainer {
	private String name;
	private List<Pokemon> pokemons = new ArrayList<Pokemon>();
	private Pokemon activePokemon;
	private int deadPokemonCount;
	private PlayerProfile trainerProfile;
	
	/**
	 * Costruttore della classe
	 * 
	 * @param name Nome del trainer
	 */
	public Trainer(String name) {
		this.name = name;
		
		if (SaveManager.profileExists(name)) {
			this.trainerProfile = SaveManager.loadPlayerProfile(name);
		} else {
			this.trainerProfile = new PlayerProfile();
		}
	}

	/**
	 * Getter per il Pokémon attivo (Quello che verrà usato per attaccare in battaglia)
	 * 
	 * @return Istanza Pokemon Contenente il Pokémon attivo
	 * 
	 * @see Battle
	 */
	public Pokemon getActivePokemon() {
		return this.activePokemon;
	}
	
	/**
	 * Getter per la lista dei Pokémon del trainer
	 * 
	 * @return List contenente le istanze di Pokemon appartenenti al trainer
	 */
	public List<Pokemon> getPokemonList() {
		return new ArrayList<>(this.pokemons);
	}

	/**
	 * Getter per il nome del trainer
	 * 
	 * @return Nome del trainer
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Getter per il numero di Pokémon morti al trainer
	 * 
	 * @return Numero Pokémon morti
	 */
	public int getDeadPokemonCount() {
		return this.deadPokemonCount;
	}
	
	/**
	 * Getter Per il profilo del trainer
	 * 
	 * @return Istanza di PlayerProfile associata al trainer
	 */
	public PlayerProfile getTrainerProfile() {
		return this.trainerProfile;
	}
	
	/**
	 * Setter per il Pokémon attivo tramite istanza
	 * 
	 * @param activePokemon Istanza di Pokemon del nuovo Pokémon attivo
	 */
	public void setActivePokemon(Pokemon activePokemon) {
		this.activePokemon = activePokemon;
	}
	
	/**
	 * Setter per il Pokémon attivo tramite indice
	 * 
	 * @param idx Indice del Pokémon nella List activePokemon del trainer
	 */
	public void setActivePokemon(int idx) {
		this.activePokemon = pokemons.get(idx);
	}
	
	/**
	 * Metodo per aggiungere Pokémon alla lista del trainer
	 * 
	 * @param newPokemon Pokémon da aggiungere
	 */
	public void addPokemon(Pokemon newPokemon) {
		this.pokemons.add(newPokemon);
		if (this.pokemons.size() == 1) {
			this.activePokemon = newPokemon;
		}
	}
	
	/**
	 * Aggiornare activePokemon e incrementare deadPokemonCount
	 */
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
	
	/**
	 * Aggiorna profilo dopo una vittoria
	 */
	public void trainerWon() {
		this.trainerProfile.playerWon();
	}
	
	/**
	 * Aggiorna profilo dopo una sconfitta
	 */
	public void trainerLost() {
		this.trainerProfile.playerLost();
	}
	
	/**
	 * Salva il profilo del trainer con il nome del trainer stesso
	 */
	public void saveTrainerProfile() {
		SaveManager.savePlayerProfile(this.trainerProfile, this.name);
	}
}
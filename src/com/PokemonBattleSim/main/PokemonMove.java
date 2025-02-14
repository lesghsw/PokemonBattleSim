package com.PokemonBattleSim.main;

/**
 * Classe per creare istanze di mosse Pokémon, le mosse hanno nome, potenza, accuratezza e tipo.
 * 
 * @author Aloisi2107981
 * 
 * @see PokemonType
 */
public class PokemonMove {
	private String name;
	private int power;
	private boolean sp;
	private float accuracy;
	private PokemonType type;
	
	/**
	 * Costruttore della classe
	 * 
	 * @param name Nome
	 * @param power Potenza (Base per l'attacco)
	 * @param accuracy Accuratezza
	 * @param type Tipo
	 * @param sp Flag per capire se una mosas è speciale
	 */
	public PokemonMove(String name, int power, float accuracy, PokemonType type, boolean sp) {
		this.name = name;
		this.power = power;
		this.accuracy = accuracy;
		this.type = type;
		this.sp = sp;
	}
	
	/**
	 * Getter per il nome della mossa 
	 * 
	 * @return Nome della mossa
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Getter per la potenza della mossa 
	 * 
	 * @return Potenza della mossa
	 */
	public int getPower() {
		return this.power;
	}
	
	/**
	 * Getter per l'accuratezza della mossa 
	 * 
	 * @return Accuratezza della mossa
	 */
	public float getAccuracy() {
		return this.accuracy;
	}
	
	/**
	 * Getter per il flag della mossa 
	 * 
	 * @return True se la mossa è speciale, False altrimenti
	 */
	public boolean isSp() {
		return this.sp;
	}
	
	/**
	 * Getter per il tipo della mossa 
	 * 
	 * @return Tipo della mossa
	 */
	public PokemonType getType() {
		return this.type;
	}
}

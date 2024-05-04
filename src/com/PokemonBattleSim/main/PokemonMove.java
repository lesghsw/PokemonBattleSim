package com.PokemonBattleSim.main;

public class PokemonMove {
	private String name;
	private int power;
	private int durability;
	private PokemonType type;
	
	public PokemonMove(String name, int power, int durability, PokemonType type) {
		this.name = name;
		this.power = power;
		this.durability = durability;
		this.type = type;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getPower() {
		return this.power;
	}
	
	public int getDurability() {
		return this.durability;
	}
	
	public PokemonType getType() {
		return this.type;
	}
	
	public void updateDurability(int qtn) {
		this.durability+=qtn;
	}
}

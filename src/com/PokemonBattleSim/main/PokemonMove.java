package com.PokemonBattleSim.main;

public class PokemonMove {
	private String name;
	private int power;
	private int durability;
	private boolean sp;
	private PokemonType type;
	
	public PokemonMove(String name, int power, int durability, PokemonType type, boolean sp) {
		this.name = name;
		this.power = power;
		this.durability = durability;
		this.type = type;
		this.sp = sp;
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
	
	public boolean isSp() {
		return this.sp;
	}
	
	public PokemonType getType() {
		return this.type;
	}
	
	public void updateDurability(int qtn) {
		this.durability+=qtn;
	}
}

package com.PokemonBattleSim.main;

public class PokemonMovePool {
	public PokemonMovePool() {}
	
	public static PokemonMove genGrowl() {
		return new PokemonMove("Growl", 0, 40, 1.0f, PokemonType.NORMAL, false);
	}
	
	public static PokemonMove genScratch() {
		return new PokemonMove("Scratch", 40, 35, 1.0f, PokemonType.NORMAL, false);
	}
	
	public static PokemonMove genEmber() {
		return new PokemonMove("Ember", 40, 25, 1.0f, PokemonType.FIRE, true);
	}
	
	public static PokemonMove genLeer() {
		return new PokemonMove("Leer", 0, 30, 1.0f, PokemonType.NORMAL, false);
	}
	
	public static PokemonMove genFlamethrower() {
		return new PokemonMove("Flamethrower", 95, 15, 1.0f, PokemonType.FIRE, true);
	}
	
	public static PokemonMove genTackle() {
		return new PokemonMove("Tackle", 35, 35, 1.0f, PokemonType.NORMAL, false);
	}
	
	public static PokemonMove genLeechSeed() {
		return new PokemonMove("Leech Seed", 0, 10, 0.9f, PokemonType.GRASS, false);
	}
	
	public static PokemonMove genVineWhip() {
		return new PokemonMove("Vine Whip", 35, 25, 1.0f, PokemonType.GRASS, false);
	}
	
	public static PokemonMove genTailWhip () {
		return new PokemonMove("Tail Whip", 0, 25, 1.0f, PokemonType.NORMAL, false);
	}
	
	public static PokemonMove genBubble() {
		return new PokemonMove("Bubble", 20, 30, 1.0f, PokemonType.WATER, true);
	}
	
	public static PokemonMove genWaterGun() {
		return new PokemonMove("Water Gun", 40, 25, 1.0f, PokemonType.WATER, true);
	}
	
}

package com.PokemonBattleSim.main;

public class PokemonPool {
	public static Pokemon genCharmander() {
		return new Pokemon("Charmander", 39, PokemonType.FIRE, PokemonMovePool.genGrowl(), PokemonMovePool.genScratch(), 5, 52, 43, 60, 50, 65);
	}
	
	public static Pokemon genBulbasaur() {
		return new Pokemon("Bulbasaur", 45, PokemonType.GRASS, PokemonMovePool.genGrowl(), PokemonMovePool.genTackle(), 5, 49, 49, 65, 65, 45);
	}
	
	public static Pokemon genSquirtle() {
		return new Pokemon("Squirtle", 44, PokemonType.WATER, PokemonMovePool.genTailWhip(), PokemonMovePool.genTackle(), 5, 48, 65, 50, 64, 43);
	}
}
package com.PokemonBattleSim.main;

public class PokemonPool {
	private PokemonMovePool movePool;

	public PokemonPool() {
		movePool = new PokemonMovePool();
	}
	
	public Pokemon genCharmander() {
		return new Pokemon("Charmander", 39, PokemonType.FIRE, this.movePool.genGrowl(), this.movePool.genScratch(), 5, 52, 43, 60, 50, 65);
	}
	
	public Pokemon genBulbasaur() {
		return new Pokemon("Bulbasaur", 45, PokemonType.GRASS, this.movePool.genGrowl(), this.movePool.genTackle(), 5, 49, 49, 65, 65, 45);
	}
	
	public Pokemon genSquirtle() {
		return new Pokemon("Squirtle", 44, PokemonType.WATER, this.movePool.genTailWhip(), this.movePool.genTackle(), 5, 48, 65, 50, 64, 43);
	}
}
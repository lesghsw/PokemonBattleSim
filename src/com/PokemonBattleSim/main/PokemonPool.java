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
	
	public static Pokemon genRobert() {
		return new Pokemon("Robert", 48, PokemonType.NORMAL, PokemonMovePool.genGrowl(), PokemonMovePool.genTackle(), 5, 55, 50, 48, 50, 64);
	}
	
	public static Pokemon genUrlox() {
		return new Pokemon("Urlox", 35, PokemonType.GRASS, PokemonMovePool.genTailWhip(), PokemonMovePool.genVineWhip(), 5, 50, 40, 70, 42, 70);
	}
	
	public static Pokemon genCordol() {
		return new Pokemon("Cordol", 42, PokemonType.NORMAL, PokemonMovePool.genGrowl(), PokemonMovePool.genTackle(), 5, 55, 60, 47, 55, 50);
	}
	
	public static Pokemon genSproloquio() {
		return new Pokemon("Sproloquio", 45, PokemonType.WATER, PokemonMovePool.genTailWhip(), PokemonMovePool.genWaterGun(), 5, 52, 45, 61, 45, 80);
	}
	
	public static Pokemon genDomenico() {
		return new Pokemon("Domenico", 43, PokemonType.NORMAL, PokemonMovePool.genGrowl(), PokemonMovePool.genTackle(), 5, 40, 73, 40, 75, 38);
	}
	
	public static Pokemon genBentley() {
		return new Pokemon("Bentley", 41, PokemonType.FIRE, PokemonMovePool.genTailWhip(), PokemonMovePool.genFlamethrower(), 5, 45, 69, 55, 67, 46);
	}
}
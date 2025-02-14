package com.PokemonBattleSim.main;

/**
 * Classe utility per generare mosse Pokémon.
 * I metodi ritornano un'instanza di PokemonMove, la quale contiene la mossa omonima del metodo.
 * <p>
 * I metodi sono statici, per cui si possono chiamare senza creare un istanza della classe.
 * 
 * @author Aloisi2107981
 * 
 * @see PokemonMove
 * @see PokemonType
 */
public class PokemonMovePool {
	public PokemonMovePool() {}
	
	public static PokemonMove genGrowl() {
		return new PokemonMove("Growl", 5, 40, 1.0f, PokemonType.NORMAL, false);
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
		return new PokemonMove("Tackle", 40, 35, 1.0f, PokemonType.NORMAL, false);
	}
	
	public static PokemonMove genLeechSeed() {
		return new PokemonMove("Leech Seed", 5, 10, 0.9f, PokemonType.GRASS, false);
	}
	
	public static PokemonMove genVineWhip() {
		return new PokemonMove("Vine Whip", 45, 25, 1.0f, PokemonType.GRASS, true);
	}
	
	public static PokemonMove genTailWhip () {
		return new PokemonMove("Tail Whip", 5, 30, 1.0f, PokemonType.NORMAL, false);
	}
	
	public static PokemonMove genBubble() {
		return new PokemonMove("Bubble", 40, 30, 1.0f, PokemonType.WATER, true);
	}
	
	public static PokemonMove genWaterGun() {
		return new PokemonMove("Water Gun", 40, 25, 1.0f, PokemonType.WATER, true);
	}
	
	public static PokemonMove genRazorLeaf() {
		return new PokemonMove("Razor Leaf", 55, 25, 0.95f, PokemonType.GRASS, true);
	}
	
	public static PokemonMove genSolarBeam() {
		return new PokemonMove("Solar Beam", 120, 10, 1.0f, PokemonType.GRASS, true);
	}
	
	public static PokemonMove genBite() {
		return new PokemonMove("Bite", 60, 25, 1.0f, PokemonType.NORMAL, false);
	}
	
	public static PokemonMove genSkullBash() {
		return new PokemonMove("Skull Bash", 130, 10, 1.0f, PokemonType.NORMAL, false);
	}
	
	public static PokemonMove genHydroPump() {
		return new PokemonMove("Hydro Pump", 110, 5, 0.8f, PokemonType.WATER, true);
	}
	
	public static PokemonMove genRage() {
		return new PokemonMove("Rage", 20, 20, 1.0f, PokemonType.NORMAL, false);
	}
	
	public static PokemonMove genSlash() {
		return new PokemonMove("Slash", 70, 20, 1.0f, PokemonType.NORMAL, false);
	}
	
	public static PokemonMove genFireSpin() {
		return new PokemonMove("Fire Spin", 35, 15, 0.85f, PokemonType.FIRE, true);
	}
}

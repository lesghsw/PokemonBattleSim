package com.PokemonBattleSim.main;

/**
 * Classe utility per generare Pokémon.
 * I metodi ritornano un'instanza di Pokemon, la quale contiene il Pokémon omonimo del metodo.
 * <p>
 * I metodi sono statici, per cui si possono chiamare senza creare un istanza della classe.
 * 
 * @author Aloisi2107981
 * 
 * @see Pokemon
 * @see PokemonType
 * @see PokemonMovePool
 */
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
		return new Pokemon("Robert", 48, PokemonType.NORMAL, PokemonMovePool.genGrowl(), PokemonMovePool.genSlash(), 5, 55, 50, 48, 50, 64);
	}
	
	public static Pokemon genUrlox() {
		return new Pokemon("Urlox", 35, PokemonType.GRASS, PokemonMovePool.genLeer(), PokemonMovePool.genRazorLeaf(), 5, 50, 40, 70, 42, 70);
	}
	
	public static Pokemon genCordol() {
		return new Pokemon("Cordol", 42, PokemonType.NORMAL, PokemonMovePool.genGrowl(), PokemonMovePool.genBite(), 5, 55, 60, 47, 55, 50);
	}
	
	public static Pokemon genSproloquio() {
		return new Pokemon("Sproloquio", 45, PokemonType.WATER, PokemonMovePool.genLeer(), PokemonMovePool.genHydroPump(), 5, 52, 45, 61, 45, 80);
	}
	
	public static Pokemon genDomenico() {
		return new Pokemon("Domenico", 43, PokemonType.NORMAL, PokemonMovePool.genGrowl(), PokemonMovePool.genSkullBash(), 5, 40, 73, 40, 75, 38);
	}
	
	public static Pokemon genBentley() {
		return new Pokemon("Bentley", 41, PokemonType.FIRE, PokemonMovePool.genTailWhip(), PokemonMovePool.genFireSpin(), 5, 45, 69, 55, 67, 46);
	}
	
	/**
	 * Genera Pokémon a partire dal nome
	 * 
	 * @param name Nome Pokémon da generare
	 * @return Istanza della classe Pokèmon contentente il Pokémon richiesto
	 */
	public static Pokemon generatePokemon(String name) {
        switch (name) {
	        case "Charmander": return genCharmander();
	        case "Bulbasaur": return genBulbasaur();
	        case "Squirtle": return genSquirtle();
	        case "Robert": return genRobert();
	        case "Urlox": return genUrlox();
	        case "Cordol": return genCordol();
	        case "Sproloquio": return genSproloquio();
	        case "Domenico": return genDomenico();
	        case "Bentley": return genBentley();
	        default: return null;
        }
	}
}
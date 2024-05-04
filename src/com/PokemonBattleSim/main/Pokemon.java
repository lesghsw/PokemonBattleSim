package com.PokemonBattleSim.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Pokemon {
	private String name;
	private float hp;
	private List<PokemonType> types = new ArrayList<PokemonType>();
	private HashMap<String, PokemonMove> moves = new HashMap<String, PokemonMove>();
	private PokemonMove activeMove;
	private int lvl;
	private int atk;
	private int dfn;
	private int spd;
	
	public Pokemon(String name, int hp, PokemonType type, PokemonMove move1, PokemonMove move2, int lvl, int atk, int dfn, int spd) {
		this.name = name;
		this.hp = hp;
		this.types.add(type);
		this.moves.put(move1.getName(), move1);
		this.moves.put(move2.getName(), move2);
		this.activeMove = move1;
		this.lvl = lvl;
		this.atk = atk;
		this.dfn = dfn;
		this.spd = spd;
	}
	
	public Pokemon(String name, int hp, PokemonType type1, PokemonType type2, PokemonMove move1, PokemonMove move2, int lvl, int atk, int dfn, int spd) {
		this.name = name;
		this.hp = hp;
		this.types.add(type1);
		this.types.add(type2);
		this.moves.put(move1.getName(), move1);
		this.moves.put(move2.getName(), move2);
		this.activeMove = move1;
		this.lvl = lvl;
		this.atk = atk;
		this.dfn = dfn;
		this.spd = spd;
	}
	
	public float getHp() {
		return this.hp;
	}
	
	public int getDfn() {
		return this.dfn;
	}
	
	public int getSpd() {
		return this.spd;
	}

	public String getName() {
		return name;
	}

	public PokemonMove getActiveMove() {
		return activeMove;
	}	
	
	public PokemonMove getMove(String name) {
		return this.moves.get(name);
	}
	
	public void damage(float dmg) {
		this.hp -= dmg;
	}
	
	private float calculateStab(List<PokemonType> pokTypes, PokemonType movType) {
		return pokTypes.contains(movType) ? 1.5f : 1.0f;
	}
	
	public void attack(Pokemon target) {
		PokemonMove move = this.activeMove;
		float lvlMod = (2.0f/5.0f*this.lvl);
		float atkODfn = this.atk / target.getDfn();
		float stab = calculateStab(this.types, move.getType());
		target.damage((( lvlMod * move.getPower() * atkODfn)/50.0f + 2) * stab);
		move.updateDurability(-1);
	}
	
	
}

package com.PokemonBattleSim.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Pokemon {
	private String name;
	private float hp;
	private final float maxHp;
	private List<PokemonType> types = new ArrayList<PokemonType>();
	private HashMap<String, PokemonMove> moves = new HashMap<String, PokemonMove>();
	private PokemonMove activeMove;
	private int lvl;
	private int atk;
	private int spAtk;
	private int def;
	private int spDef;
	private int spd;
	
	public Pokemon(String name, int hp, PokemonType type, PokemonMove move1, PokemonMove move2, int lvl, int atk, int def, int spAtk, int spDef, int spd) {
		this.name = name;
		this.hp = hp;
		this.maxHp = hp;
		this.types.add(type);
		this.moves.put(move1.getName(), move1);
		this.moves.put(move2.getName(), move2);
		this.activeMove = move1;
		this.lvl = lvl;
		this.atk = atk;
		this.spAtk = spAtk;
		this.def = def;
		this.spDef = spDef;
		this.spd = spd;
	}
	
	public Pokemon(String name, int hp, PokemonType type1, PokemonType type2, PokemonMove move1, PokemonMove move2, int lvl, int atk, int def, int spAtk, int spDef, int spd) {
		this.name = name;
		this.hp = hp;
		this.maxHp = hp;
		this.types.add(type1);
		this.types.add(type2);
		this.moves.put(move1.getName(), move1);
		this.moves.put(move2.getName(), move2);
		this.activeMove = move1;
		this.lvl = lvl;
		this.atk = atk;
		this.spAtk = spAtk;
		this.def = def;
		this.spDef = spDef;
		this.spd = spd;
	}
	
	public float getHp() {
		return this.hp;
	}
	
	public int getDef() {
		return this.def;
	}
	
	public int getSpDef() {
		return this.spDef;
	}
	
	public int getSpd() {
		return this.spd;
	}

	public String getName() {
		return this.name;
	}

	public PokemonMove getActiveMove() {
		return this.activeMove;
	}
	
	public PokemonMove getMove(String name) {
		return this.moves.get(name);
	}
	
	public void setActiveMove(String moveName) {
		this.activeMove = this.moves.getOrDefault(moveName, this.activeMove);
	}
	
	public void addMove(PokemonMove move) {
		if (this.moves.size() < 4) {
			this.moves.put(move.getName(), move);
		}
	}
	
	public void damage(float dmg) {
		this.hp -= dmg;
	}
	
	private float calculateStab(List<PokemonType> pokTypes, PokemonType movType) {
		return pokTypes.contains(movType) ? 1.5f : 1.0f;
	}
	
	public void attack(Pokemon target) {
		PokemonMove move = this.activeMove;
		if (move.getPower() > 0) {
			int attack = move.isSp() ? this.spAtk : this.atk;
			int defence = move.isSp() ? target.getSpDef() : target.getDef();
			
			float lvlMod = (2.0f/5.0f*this.lvl + 2);
			float atkODef = attack / (float)defence;
			
			float stab = calculateStab(this.types, move.getType());
			target.damage((( lvlMod * move.getPower() * atkODef)/50.0f + 2) * stab);
		}
		move.updateDurability(-1);
	}
	
	
}

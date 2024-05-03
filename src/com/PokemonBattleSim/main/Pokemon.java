package com.PokemonBattleSim.main;

import java.util.ArrayList;
import java.util.List;

enum PokemonType {
	FIRE,
	GRASS,
	WATER
}

public class Pokemon {
	private String name;
	private int hp;
	private List<PokemonType> types = new ArrayList<PokemonType>();
	private int lvl;
	private int atk;
	private int dfn;
	private int spd;
	
	public Pokemon(String name, int hp, PokemonType type, int lvl, int atk, int dfn, int spd) {
		this.name = name;
		this.hp = hp;
		this.types.add(type);
		this.lvl = lvl;
		this.atk = atk;
		this.dfn = dfn;
		this.spd = spd;
	}
	
	public Pokemon(String name, int hp, PokemonType type1, PokemonType type2, int lvl, int atk, int dfn, int spd) {
		this.name = name;
		this.hp = hp;
		this.types.add(type1);
		this.types.add(type2);
		this.lvl = lvl;
		this.atk = atk;
		this.dfn = dfn;
		this.spd = spd;
	}
	
	public int gethp() {
		return this.hp;
	}
	
	public void damage(int dmg) {
		this.hp -= dmg;
	}
	
	public void Attack(Pokemon target) {
		target.damage(this.atk);
	}
	
	
}

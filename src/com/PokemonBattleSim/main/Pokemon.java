package com.PokemonBattleSim.main;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

public class Pokemon {
	private String name;
	private float hp;
	private final float maxHp;
	private List<PokemonType> types = new ArrayList<PokemonType>();
	private LinkedHashMap<String, PokemonMove> moves = new LinkedHashMap<String, PokemonMove>();
	private PokemonMove activeMove;
	private int lvl;
	private int atk;
	private int spAtk;
	private int def;
	private int spDef;
	private int spd;
	private final String imgSrcFront;
	private final String imgSrcBack;
	
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
		this.imgSrcFront = "ref/" + name.toLowerCase() + "/front.png";
		this.imgSrcBack = "ref/" + name.toLowerCase() + "/back.png";
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
		this.imgSrcFront = "ref/" + name.toLowerCase() + "/front.png";
		this.imgSrcBack = "ref/" + name.toLowerCase() + "/back.png";
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
	
	public List<PokemonMove> getMoves() {
		List<PokemonMove> movesList = new ArrayList<PokemonMove>();
		for(PokemonMove value : this.moves.values()) {
			movesList.add(value);
		}
		return movesList;
	}
	
	public List<PokemonType> getTypes() {
		return new ArrayList<>(this.types);
	}
	
	public float getMaxHp() {
		return this.maxHp;
	}
	
	public String getSpriteFront() {
		return this.imgSrcFront;
	}
	
	public String getSpriteBack() {
		return this.imgSrcBack;
	}
	
	public void setActiveMove(String moveName) {
		if(moveName != null) {
			this.activeMove = this.moves.getOrDefault(moveName, this.activeMove);
		} else {
			this.activeMove = null;
		}
	}
	
	public void setActiveMove(int idx) {
		this.activeMove = getMoves().get(idx);
	}
	
	public void addMove(PokemonMove move) {
		if (this.moves.size() < 4) {
			this.moves.put(move.getName(), move);
		}
	}
	
	public void damage(float dmg) {
		this.hp -= dmg;
	}
	
	private float calculateStab() {
		return this.types.contains(this.getActiveMove().getType()) ? 1.5f : 1.0f;
	}
	
	private float calculateEffectiveness(List<PokemonType> targetTypes) {
		PokemonType moveType = this.getActiveMove().getType();
		
		float eff = 1.0f;
		
		switch(moveType) {
			case NORMAL:
				break;
			case FIRE:
				if (targetTypes.contains(PokemonType.FIRE)) eff*=0.5f;
				if (targetTypes.contains(PokemonType.WATER)) eff*=0.5f;
				if (targetTypes.contains(PokemonType.GRASS)) eff*=2.0f;
				break;
			case WATER:
				if (targetTypes.contains(PokemonType.FIRE)) eff*=2.0f;
				if (targetTypes.contains(PokemonType.WATER)) eff*=0.5f;
				if (targetTypes.contains(PokemonType.GRASS)) eff*=0.5f;
				break;
			case GRASS:
				if (targetTypes.contains(PokemonType.FIRE)) eff*=0.5f;
				if (targetTypes.contains(PokemonType.WATER)) eff*=2.0f;
				if (targetTypes.contains(PokemonType.GRASS)) eff*=0.5f;
				break;
			default:
				break;
		}
		
		return eff;
	}
	
	public void attack(Pokemon target) {
		PokemonMove move = this.activeMove;
		Random rand = new Random();
		
		System.out.println(this.name + " used " + move.getName());
		
		if (move.getPower() > 0) {
			int attack = move.isSp() ? this.spAtk : this.atk;
			int defence = move.isSp() ? target.getSpDef() : target.getDef();
			
			float lvlMod = (2.0f/5.0f*this.lvl + 2);
			float atkODef = attack / (float)defence;
			float eff = calculateEffectiveness(target.getTypes());
			
			if (eff > 1.0f) System.out.println("It's super effective!");
			else if (eff < 1.0f) System.out.println("It's not very effective...");
			
			float stab = calculateStab();
			float rndAcc = rand.nextFloat();
			float rndMod = rand.nextFloat(0.15f) + 0.85f;
			if (rndAcc <= move.getAccuracy())
				target.damage((( lvlMod * move.getPower() * atkODef)/50.0f + 2) * stab * eff * rndMod);
		}
		move.updateDurability(-1);
	}
	
	
}

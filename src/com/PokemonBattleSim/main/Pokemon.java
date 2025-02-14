package com.PokemonBattleSim.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pokemon {
	private String name;
	private float hp;
	private final float maxHp;
	private List<PokemonType> types = new ArrayList<PokemonType>();
	private List<PokemonMove> moves = new ArrayList<PokemonMove>();
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
		this.moves.add(move1);
		this.moves.add(move2);
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
	
	public PokemonMove getMove(int idx) {
		return this.moves.get(idx);
	}
	
	public List<PokemonMove> getMoves() {
		return new ArrayList<>(this.moves);
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
	
	public void setActiveMove(Integer idx) {
		if (idx != null) {
			if (idx < this.moves.size()) this.activeMove = this.moves.get(idx);
		} else {
			this.activeMove = null;
		}
	}
	
	public void addMove(PokemonMove move) {
		if (this.moves.size() < 4) {
			this.moves.add(move);
		}
	}
	public void addType(PokemonType type) {
		if (this.types.size() < 2) {
			this.types.add(type);
		}
	}
	
	private void damage(float dmg) {
		this.hp -= dmg;
	}
	
	private float calculateStab() {
		return this.types.contains(this.getActiveMove().getType()) ? 1.5f : 1.0f;
	}
	
	public float calculateEffectiveness(List<PokemonType> targetTypes) {
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
		
		if (move.getPower() > 0) {
			int attack = move.isSp() ? this.spAtk : this.atk;
			int defence = move.isSp() ? target.getSpDef() : target.getDef();
			
			float lvlMod = (2.0f/5.0f*this.lvl + 2);
			float atkODef = attack / (float)defence;
			float eff = calculateEffectiveness(target.getTypes());
			
			float stab = calculateStab();
			float rndAcc = rand.nextFloat();
			float rndMod = rand.nextFloat(0.15f) + 0.85f;
			if (rndAcc <= move.getAccuracy())
				target.damage((( lvlMod * move.getPower() * atkODef)/50.0f + 2) * stab * eff * rndMod);
		}
		move.updateDurability(-1);
	}
	
}

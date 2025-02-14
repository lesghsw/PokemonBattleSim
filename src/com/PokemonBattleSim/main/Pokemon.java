package com.PokemonBattleSim.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe per creare istanze di Pokémon, contiene anche metodi getter e setter e metodi per gestire le dinamiche tra Pokémon
 * 
 * @author Aloisi2107981
 */
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
	
	/**
	 * Costruttore per classe Pokémon
	 * 
	 * @param name Nome
	 * @param hp Vita (Vale anche come vita massima)
	 * @param type Tipo (Ne può essere aggiunto uno successivamente tramite il metodo addType)
	 * @param move1 Prima mossa
	 * @param move2 Seconda mossa
	 * @param lvl Livello
	 * @param atk Valore della statistica "Attacco"
	 * @param def Valore della statistica "Difesa"
	 * @param spAtk Valore della statistica "Attacco Speciale"
	 * @param spDef Valore della statistica "Difesa Speciale"
	 * @param spd Velocità
	 * 
	 * @see addType
	 */
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
	
	/**
	 * Getter per la vita del Pokémon
	 * 
	 * @return Vita attuale del Pokémon
	 */
	public float getHp() {
		return this.hp;
	}
	
	/**
	 * Getter per la difesa del Pokémon
	 * 
	 * @return Difesa del Pokémon
	 */
	public int getDef() {
		return this.def;
	}
	
	/**
	 * Getter per la difesa speciale del Pokémon
	 * 
	 * @return Difesa speciale del Pokémon
	 */
	public int getSpDef() {
		return this.spDef;
	}
	
	/**
	 * Getter per la velocità del Pokémon
	 * 
	 * @return Velocità del Pokémon
	 */
	public int getSpd() {
		return this.spd;
	}

	/**
	 * Getter per il nome del Pokémon
	 * 
	 * @return Nome del Pokémon
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Getter per la mossa attiva del Pokémon (Quella che utilizzerà al prossimo attacco)
	 * 
	 * @return Mossa attiva del Pokémon
	 */
	public PokemonMove getActiveMove() {
		return this.activeMove;
	}
	
	/**
	 * Getter per una mossa del Pokémon
	 * 
	 * @param idx indice della mossa nell'array moves
	 * @return Nome del Pokémon
	 */
	public PokemonMove getMove(int idx) {
		return this.moves.get(idx);
	}
	
	/**
	 * Getter per le mosse del Pokémon
	 * 
	 * @return ArrayList contenente le mosse del Pokémon
	 */
	public List<PokemonMove> getMoves() {
		return new ArrayList<>(this.moves);
	}
	
	/**
	 * Getter per i tipi del Pokémon
	 * 
	 * @return ArrayList contenente i tipi del Pokémon
	 * 
	 * @see PokemonType
	 */
	public List<PokemonType> getTypes() {
		return new ArrayList<>(this.types);
	}
	
	/**
	 * Getter per la vita massima del Pokémon
	 * 
	 * @return Vita massima del Pokémon
	 */
	public float getMaxHp() {
		return this.maxHp;
	}
	
	/**
	 * Getter per il path alla immagine frontale del Pokémon (Deve esistere in ref/{NomePokémon/front.png})
	 * 
	 * @return Path allo sprite frontale
	 */
	public String getSpriteFront() {
		return this.imgSrcFront;
	}
	
	/**
	 * Getter per il path alla immagine posteriore del Pokémon (Deve esistere in ref/{NomePokémon/back.png})
	 * 
	 * @return Path allo sprite posteriore
	 */
	public String getSpriteBack() {
		return this.imgSrcBack;
	}
	
	/**
	 * Setter per la mossa attiva (Quella che verrà usata al prossimo attacco)
	 * 
	 * @param idx Indice della mossa nell'array, usa Integer così da poter passare valore null
	 * 
	 * @see Integer
	 */
	public void setActiveMove(Integer idx) {
		if (idx != null) {
			if (idx < this.moves.size()) this.activeMove = this.moves.get(idx);
		} else {
			this.activeMove = null;
		}
	}
	
	/**
	 * Aggiunge una mossa alla lista mosse (Se possibile)
	 * 
	 * @param move Mossa da aggiungere
	 */
	public void addMove(PokemonMove move) {
		if (this.moves.size() < 4) {
			this.moves.add(move);
		}
	}
	
	/**
	 * Aggiunge un tipo alla lista tipi (Se possibile)
	 * 
	 * @param type tipo da aggiungere
	 */
	public void addType(PokemonType type) {
		if (this.types.size() < 2) {
			this.types.add(type);
		}
	}
	
	/**
	 * Riduce la vita del Pokémon
	 * 
	 * @param dmg
	 */
	private void damage(float dmg) {
		this.hp -= dmg;
	}
	
	/**
	 * Calcola il modificatore di danno STAB, tpico dei giochi Pokémon
	 * 
	 * @return Valore del modificatore da applicare al danno
	 */
	private float calculateStab() {
		return this.types.contains(this.getActiveMove().getType()) ? 1.5f : 1.0f;
	}
	
	/**
	 * Calcola efficacia della mossa sul Pokémon su cui verrà inflitto l'attacco
	 * 
	 * @param targetTypes I tipi del Pokémon attaccato
	 * @return Valore del modificatore da applicare al danno
	 * 
	 * @see PokemonType
	 */
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
	
	/**
	 * Funzione attacco, calcola di danni da infliggere in base a tutti i modificatori e basandosi sulle statistiche dei Pokémon e della mossa.
	 * 
	 * @param target Pokémon che verrà attaccato
	 * 
	 * @see PokemonMove
	 * @see Random
	 */
	public void attack(Pokemon target) {
		PokemonMove move = this.activeMove;
		Random rand = new Random();
		
		if (move.getPower() > 0) {
			int attack = move.isSp() ? this.spAtk : this.atk;
			int defence = move.isSp() ? target.getSpDef() : target.getDef();
			
			float lvlMod = (2.0f/5.0f*this.lvl + 2);
			float atkODef = attack / (float)defence;
			float eff = calculateEffectiveness(target.getTypes());
			
//			if (eff > 1.0f) System.out.println("It's super effective!");
//			else if (eff < 1.0f) System.out.println("It's not very effective...");
			
			float stab = calculateStab();
			float rndAcc = rand.nextFloat();
			float rndMod = rand.nextFloat(0.15f) + 0.85f;
			if (rndAcc <= move.getAccuracy())
				target.damage((( lvlMod * move.getPower() * atkODef)/50.0f + 2) * stab * eff * rndMod);
		}
	}
	
}

package com.PokemonBattleSim.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.PokemonBattleSim.main.Trainer;
import com.PokemonBattleSim.main.Battle;
import com.PokemonBattleSim.main.PokemonPool;

class BattleTest {
	Trainer trainer1 = new Trainer("TESTUNIT1");
	Trainer trainer2 = new Trainer("TESTUNIT2");
	Battle battle;
	
	@BeforeEach
	void setUp() {
		trainer1.addPokemon(PokemonPool.genRobert());
		trainer1.getActivePokemon().setActiveMove(1);
		trainer2.addPokemon(PokemonPool.genCharmander());
		trainer2.addPokemon(PokemonPool.genSquirtle());
		trainer2.getActivePokemon().setActiveMove(1);
		battle = new Battle(trainer1, trainer2);
	}
	
	@Test
	void testRunBattle() {
		// Check if attack happened
		battle.runBattle();
		assertTrue(trainer1.getActivePokemon().getHp() < trainer1.getActivePokemon().getMaxHp());
		assertTrue(trainer2.getActivePokemon().getHp() < trainer2.getActivePokemon().getMaxHp());
		
		// Check if detects PokemonDeath
		trainer2.setActivePokemon(1);
		int res;
		do {
			res = battle.runBattle();
		}
		while (res == 0);
		assertEquals(2, res); // check if correct player wins for this test case
		
		// Check if first alive Pokemon is chosen as active Pokemon
		assertEquals(trainer2.getActivePokemon().getName(), "Charmander");
	}

}

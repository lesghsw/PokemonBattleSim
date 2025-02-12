package com.PokemonBattleSim.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.PokemonBattleSim.main.Pokemon;
import com.PokemonBattleSim.main.PokemonMovePool;
import com.PokemonBattleSim.main.PokemonPool;

class PokemonTest {
	Pokemon charmander = PokemonPool.genCharmander(), squirtle = PokemonPool.genSquirtle();
	
	@Test
	void testSetActiveMove() {
		charmander.setActiveMove(1);
		assertEquals(charmander.getActiveMove(), charmander.getMove(1));
	}

	@Test
	void testAddMove() {
		int oldSize = charmander.getMoves().size();
		charmander.addMove(PokemonMovePool.genWaterGun());
		assertEquals(oldSize + 1, charmander.getMoves().size());
		assertEquals(charmander.getMove(2).getName(), "Water Gun");
	}

	@Test
	void testAttack() {
		charmander.setActiveMove(1);
		charmander.attack(squirtle);
		assertTrue(squirtle.getHp() < squirtle.getMaxHp());
	}

}

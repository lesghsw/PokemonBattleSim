package com.PokemonBattleSim.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.PokemonBattleSim.main.PlayerProfile;
import com.PokemonBattleSim.main.SaveManager;

class SaveManagerTest {
	PlayerProfile profile1;
	PlayerProfile profile2;
	
	@BeforeEach
	void setUp() {
		profile1 = new PlayerProfile();
	}
	
	@Test
	void testSaveAndLoadPlayerProfile() {
		profile1.playerWon();
		profile1.playerLost();
		SaveManager.savePlayerProfile(profile1, "UnitTestSaveLoad");
		profile2 = SaveManager.loadPlayerProfile("UnitTestSaveLoad");
		assertEquals(profile1.getPlayedCount(), profile2.getPlayedCount());
		assertEquals(profile1.getWonCount(), profile2.getWonCount());
		assertEquals(profile1.getLostCount(), profile2.getLostCount());
	}

	@Test
	void testDeleteProfile() {
		profile1.playerWon();
		profile1.playerLost();
		SaveManager.savePlayerProfile(profile1, "UnitTestDelete");
		SaveManager.deleteProfile("UnitTestDelete");
		assertEquals(SaveManager.loadPlayerProfile("UnitTestDelete"), null);
	}

	@Test
	void testProfileExists() {
		profile1.playerWon();
		profile1.playerLost();
		SaveManager.savePlayerProfile(profile1, "UnitTestProfileExists");
		assertTrue(SaveManager.profileExists("UnitTestProfileExists"));
		SaveManager.deleteProfile("UnitTestProfileExists");
		assertFalse(SaveManager.profileExists("UnitTestProfileExists"));
	}

}

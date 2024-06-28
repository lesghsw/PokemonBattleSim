package com.PokemonBattleSim.main;

public class GameSettings {

	private boolean debugMode;

	public GameSettings(boolean debugMode) {

		this.debugMode = debugMode;
	}

	public boolean isDebugMode() { return debugMode; }
	
	public void toggleDebugMode() {
		
		debugMode = !debugMode;
		System.out.println("Debug Mode: " + debugMode);
	}
}

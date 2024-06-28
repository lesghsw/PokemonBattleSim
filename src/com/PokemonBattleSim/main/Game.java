package com.PokemonBattleSim.main;

public class Game {
	
	public Game() {
		
		new Window();
	}
		
/*	-----------------------------------
	
		PokemonPool pokPool = new PokemonPool();
		PokemonMovePool pokMovePool = new PokemonMovePool();
		
		Trainer trainer1 = new Trainer("Luca", pokPool.genSquirtle(), pokPool.genSquirtle(), pokPool.genSquirtle());
		Trainer trainer2 = new Trainer("Giancarlo", pokPool.genCharmander(), pokPool.genCharmander(), pokPool.genCharmander());
		Trainer trainer3 = new Trainer("Leonardo", pokPool.genBulbasaur(), pokPool.genBulbasaur(), pokPool.genBulbasaur());
		
		trainer1.getActivePokemon().addMove(pokMovePool.genWaterGun());
		trainer2.getActivePokemon().addMove(pokMovePool.genFlamethrower());
		
		Battle battle1 = new Battle(trainer1, trainer2);
		
		System.out.println("Round 1:");
		battle1.runBattle();
		
		trainer1.getActivePokemon().setActiveMove("Tackle");
		trainer2.getActivePokemon().setActiveMove("Scratch");
		
		System.out.println("Round 2:");
		battle1.runBattle();
		
		trainer1.getActivePokemon().setActiveMove("Water Gun");
		trainer2.getActivePokemon().setActiveMove("Flamethrower");
		
		System.out.println("Round 3:");
		battle1.runBattle();
		
		trainer1.getActivePokemon().setActiveMove("Tackle");
		trainer2.getActivePokemon().setActiveMove("Scratch");
		
		System.out.println("Round 4:");
		battle1.runBattle();
		
-----------------------------------
		
		
		BufferedImage image1 = null;
		BufferedImage image2 = null;
		BufferedImage image3 = null;
		BufferedImage image4 = null;
		BufferedImage image5 = null;
		BufferedImage image6 = null;
		
		try {
			image1 = ImageIO.read(getClass().getResourceAsStream(trainer1.getActivePokemon().getSpriteFront()));
			image2 = ImageIO.read(getClass().getResourceAsStream(trainer1.getActivePokemon().getSpriteBack()));
			image3 = ImageIO.read(getClass().getResourceAsStream(trainer2.getActivePokemon().getSpriteFront()));
			image4 = ImageIO.read(getClass().getResourceAsStream(trainer2.getActivePokemon().getSpriteBack()));
			image5 = ImageIO.read(getClass().getResourceAsStream(trainer3.getActivePokemon().getSpriteFront()));
			image6 = ImageIO.read(getClass().getResourceAsStream(trainer3.getActivePokemon().getSpriteBack()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		trainer1.getActivePokemon().setActiveMove("Water Gun");
		trainer2.getActivePokemon().setActiveMove("Water Gun");
		System.out.println("Round 3:");
		battle1.runBattle();
		
-----------------------------------	*/	

	public static void main(String args[]) {
		
		new Game();
	}
}

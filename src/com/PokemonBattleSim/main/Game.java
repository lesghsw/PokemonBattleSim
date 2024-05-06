package com.PokemonBattleSim.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -905039917332265024L;

	public static final int WIDTH = 800;
	public static final int HEIGHT = 800;
	
	private Thread thread;
	private boolean running = false;
	
	public Game() {
		new Window(WIDTH, HEIGHT, "title", this);
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		long t1 = System.nanoTime();
		double nticks = 120.0;
		double ns = 1000000000 / nticks;
		double delta = 0;
		long timer = 0;
		int fps = 0;
		
// -----------------------------------
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
		
		trainer1.getActivePokemon().setActiveMove("Water Gun");
		trainer2.getActivePokemon().setActiveMove("Flamethrower");
		
		System.out.println("Round 2:");
		battle1.runBattle();
		
		
		
		// ------------------
		
		
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
		
		/*trainer1.getActivePokemon().setActiveMove("Water Gun");
		trainer2.getActivePokemon().setActiveMove("Water Gun");
		System.out.println("Round 3:");
		battle1.runBattle();*/
		
// -----------------------------------			
		while(running) {
			long t2 = System.nanoTime();
			delta += (t2 - t1) / ns;
			timer += t2 - t1;
			t1 = t2;
			
			while(delta >= 1) {
				tick();
				render(image1, image2, image3, image4, image5, image6);
				delta--;
				fps++;
			}
			
			if (timer >= 1000000000) {
				//System.out.println(fps);
				fps = 0;
				timer=0;
			}
		}
		stop();
	}
	
	private void tick() {
		
	}
	
	private void render(BufferedImage image1, BufferedImage image2, BufferedImage image3, BufferedImage image4, BufferedImage image5, BufferedImage image6) {
		/*BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(2);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.WHITE);
		g.fillRect(0,0,WIDTH,HEIGHT);
		
		g.setColor(Color.CYAN);
		g.drawString(Integer.toString(fps), 200, 400);
		
		g.dispose();
		bs.show();*/
		
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(2);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(image1, 20, 20, null);
		g.drawImage(image2, 100, 20, null);
		g.drawImage(image3, 180, 20, null);
		g.drawImage(image4, 260, 20, null);
		g.drawImage(image5, 320, 20, null);
		g.drawImage(image6, 400, 20, null);
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String args[]) {
		new Game();
	}
}

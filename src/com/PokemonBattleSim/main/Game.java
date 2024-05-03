package com.PokemonBattleSim.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

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
		
		PokemonMove graffio = new PokemonMove("Graffio", 10, 3, PokemonType.FIRE);
		PokemonMove pugno = new PokemonMove("Pugno", 12, 3, PokemonType.WATER);
		
		Pokemon a = new Pokemon("Charmander", 100, PokemonType.FIRE, graffio, pugno, 12, 23, 12, 1);
		Pokemon b = new Pokemon("Bulbasaur", 232, PokemonType.WATER, graffio, pugno, 11, 12, 12, 1);
		
		a.attack(b, a.getMove("Graffio"));
		System.out.println(b.getHp());
		
		while(running) {
			long t2 = System.nanoTime();
			delta += (t2 - t1) / ns;
			timer += t2 - t1;
			t1 = t2;
			
			while(delta >= 1) {
				tick();
				delta--;
				fps++;
			}
			
			if(timer >= 1000000000)
				render(fps);
			
			if (timer >= 1000000000) {
				System.out.println(fps);
				fps = 0;
				timer=0;
			}
		}
		stop();
	}
	
	private void tick() {
		
	}
	
	private void render(int fps) {
		BufferStrategy bs = this.getBufferStrategy();
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
		bs.show();
	}
	
	public static void main(String args[]) {
		new Game();
	}
}

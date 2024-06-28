package com.PokemonBattleSim.main;

import java.awt.Color; // per colori
import java.awt.Dimension; // per dimensione finestra
import java.awt.Graphics;
//import java.awt.Graphics2D;

import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable{

	// IMPOSTAZIONI SCHERMO
	final int screenWidth = 1280;
	final int screenHeight = 720;
	
	// AGGIUNGERE GAME STATES

	// FPS
	int FPS = 60;
	
	// VARIE
	KeyHandler keyH;
	Thread gameThread;
	State state;
	GameSettings settings;
	GameController gameController;
	
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		
		keyH = new KeyHandler(this);
		this.addKeyListener(keyH);
		this.addMouseListener(keyH);
		this.addMouseMotionListener(keyH);
		
		state = new	GameState(keyH);
		settings = new GameSettings(false);
		gameController = new GameController(keyH);
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while (gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime -lastTime);
			lastTime = currentTime;
			
			if (delta >= 1) {
							
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if (timer >= 1000000000) {
				System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}
			
			
		}
	}
	
	public void update() {
		
		gameController.update(this);
		handleMouseInput();
/*		DA IMPLEMENTARE
		
		if (gameState == 1) {
		
			player.update();	
		}
		
		if (gameState == pauseState) {
		} */
	}
	
	private void handleMouseInput() {
		
		if(keyH.isMouseClicked()) {
			System.out.println(String.format("Mouse cliccato in posizione x:%d y:%d", keyH.getMousePosition().getX(), keyH.getMousePosition().getY()));
		}
		
		keyH.clearMouseClick();
	}

	public void paintComponent(Graphics g) {
//		DA IMPLEMENTARE
		super.paintComponent(g);
/*		
		Graphics2D g2 = (Graphics2D)g; simile a Graphics ma con funzionalità in più
		
		TITLE SCREEN
		if (gameState == titleState) {	
		}
		
		else {
		}
		
 		g2.dispose(); per risparmiare memoria */
	}

	public void playMusic(int i) {
/*	DA IMPLEMENTARE
	sound.setFile(i);
	sound.play();
	sound.loop(); */
	}

	public void stopMusic() {
/*	DA IMPLEMENTARE
	sound.stop(); */
	}

	public void playSE(int i) {
/*	DA IMPLEMENTARE
	sound.setFile(i);
	sound.play(); */
	}

	public GameSettings getSettings() {
		return settings;
	}
}


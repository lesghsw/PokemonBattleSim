package com.PokemonBattleSim.main;

import java.awt.Color; // per colori
import java.awt.Dimension; // per dimensione finestra
import java.awt.Graphics;
import java.awt.Image;
//import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable{

	// IMPOSTAZIONI SCHERMO
	final int screenWidth = 1280;
	final int screenHeight = 720;
	
	// FPS
	int FPS = 60;
	
	// VARIE
	private Thread gameThread;
	private Sound sound;
	
	// BATTAGLIA
	private Trainer player1;
	private Trainer player2;
	private JButton atButton1;
	private JButton atButton2;
	private JButton atButton3;
	private JButton atButton4;
	private JButton swButton;
	private Turn currentTurn = Turn.PLAYER1;
	
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		
		atButton1 = new JButton("Mossa 1");
	    atButton2 = new JButton("Mossa 2");
	    atButton3 = new JButton("Mossa 3");
	    atButton4 = new JButton("Mossa 4");
	    swButton = new JButton("Switch");

	    atButton1.setActionCommand("MOVE1");
	    atButton2.setActionCommand("MOVE2");
	    atButton3.setActionCommand("MOVE3");
	    atButton4.setActionCommand("MOVE4");
	    swButton.setActionCommand("SWITCH");

	    atButton1.addActionListener(e -> handleAction(e.getActionCommand()));
	    atButton2.addActionListener(e -> handleAction(e.getActionCommand()));
	    atButton3.addActionListener(e -> handleAction(e.getActionCommand()));
	    atButton4.addActionListener(e -> handleAction(e.getActionCommand()));
	    swButton.addActionListener(e -> handleSwitch());
		
/*		atButton1.setFocusable(false);
		atButton2.setFocusable(false);
		atButton3.setFocusable(false);
		atButton4.setFocusable(false);
		swButton.setFocusable(false); **/
		
		this.add(atButton1);
	    this.add(atButton2);
	    this.add(atButton3);
	    this.add(atButton4);
	    this.add(swButton);
		
		sound = new Sound();
		
		PokemonPool pool = new PokemonPool();
		
		player1 = new Trainer("PietroSmusi", pool.genCharmander());
		player2 = new Trainer("Oksana", pool.genCharmander());
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
		playMusic(0);
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
		}
	
	private enum Turn {
	    PLAYER1,
	    PLAYER2
	}
	
	private void handleAction(String actionCommand) {
	    if (currentTurn == Turn.PLAYER1) {
	        Pokemon activePokemon = player1.getActivePokemon();
	        String moveName = "";
	        // Mappa i pulsanti alla mossa corretta (modificare alle mosse corrette)
	        switch (actionCommand) {
	            case "MOVE1":
	                moveName = "Growl";
	                break;
	            case "MOVE2":
	                moveName = "Scratch";
	                break;
	            case "MOVE3":
	                moveName = "Tackle";
	                break;
	            case "MOVE4":
	                moveName = "Tail Whip";
	                break;
	        }

	        // Imposta la mossa attiva
	        if (!moveName.isEmpty()) {
	            activePokemon.setActiveMove(moveName);
	        }
	        endTurn(); // Passa al turno del player2
	    } else if (currentTurn == Turn.PLAYER2) {
	    	Pokemon activePokemon = player2.getActivePokemon();
	        String moveName = "";
	        // Mappa i pulsanti alla mossa corretta (modificare alle mosse corrette)
	        switch (actionCommand) {
	            case "MOVE1":
	                moveName = "Growl";
	                break;
	            case "MOVE2":
	                moveName = "Scratch";
	                break;
	            case "MOVE3":
	                moveName = "Tackle";
	                break;
	            case "MOVE4":
	                moveName = "Tail Whip";
	                break;
	        }

	        // Imposta la mossa attiva
	        if (!moveName.isEmpty()) {
	            activePokemon.setActiveMove(moveName);
	        }
	        executeTurn(); // Esegui la battaglia solo dopo la scelta di entrambi
	    }
	}
	
	private void executeTurn() {
	        Battle battle = new Battle(player1, player2);
	        battle.runBattle();

	        // Passa il turno a player1
	        currentTurn = Turn.PLAYER1;
	}

	private void endTurn() {
	    if (currentTurn == Turn.PLAYER1) {
	        currentTurn = Turn.PLAYER2;
	    } else {
	        executeTurn(); // Se entrambi hanno scelto, esegui la battaglia
	    }
	}
	
    private void handleSwitch() {
    }


	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Pokemon player1Pokemon = player1.getActivePokemon();
	    Pokemon player2Pokemon = player2.getActivePokemon();
		
	    Image playerSprite = Toolkit.getDefaultToolkit().getImage(getClass().getResource("sound/Robert.png"));
	    Image opponentSprite = Toolkit.getDefaultToolkit().getImage(getClass().getResource("sound/Robert.png"));
	    
	    g.drawImage(playerSprite, 100, 400, this);
	    g.drawImage(opponentSprite, 1117, 100, this);
	    
	    drawHealthBar(g, player1Pokemon, 100, 370);
	    drawHealthBar(g, player2Pokemon, 980, 70);
	}

	private void drawHealthBar(Graphics g, Pokemon pokemon, int x, int y) {
	    // Lunghezza barra in base alla percentuale di HP rimasti
	    int barWidth = 200;
	    int hpWidth = (int)((pokemon.getHp() / pokemon.getMaxHp()) * barWidth);
	    
	    // Colore della barra (verde, giallo, rosso in base alla percentuale)
	    Color barColor = Color.GREEN;
	    if (pokemon.getHp() / pokemon.getMaxHp() < 0.5) barColor = Color.YELLOW;
	    if (pokemon.getHp() / pokemon.getMaxHp() < 0.2) barColor = Color.RED;
//	    if (pokemon.getHp() == 0) = MUORI;
	    
	    g.setColor(barColor);
	    g.fillRect(x, y, hpWidth, 20); // Barra vita
	    
	    g.setColor(Color.BLACK);
	    g.drawRect(x, y, barWidth, 20); // Contorno della barra
	}
	
	public void playMusic(int i) {
		sound.setFile(i);
		sound.play();
		sound.loop();
	}

	public void stopMusic() {
		sound.stop();
	}

	public void playSE(int i) {
		sound.setFile(i);
		sound.play();
	}
}
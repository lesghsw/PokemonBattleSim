package com.PokemonBattleSim.main;

import java.awt.Color; // per colori
import java.awt.Dimension; // per dimensione finestra
import java.awt.Graphics;
import java.awt.Image;
//import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.List;

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
	private Battle battle;
	private JButton atButton1;
	private JButton atButton2;
	private JButton atButton3;
	private JButton atButton4;
	private JButton swButton;
	private JButton pk1Button;
	private JButton pk2Button;
	private JButton pk3Button;
	private Turn currentTurn = Turn.PLAYER1;
	
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		
		atButton1 = new JButton("Mossa 1");
	    atButton2 = new JButton("Mossa 2");
/*	    atButton3 = new JButton("Mossa 3");
	    atButton4 = new JButton("Mossa 4");		*/
	    swButton = new JButton("Switch");
	    pk1Button = new JButton("1");
	    pk2Button = new JButton("2");
	    pk3Button = new JButton("3");

	    atButton1.setActionCommand("MOVE1");
	    atButton2.setActionCommand("MOVE2");
/*	    atButton3.setActionCommand("MOVE3");
	    atButton4.setActionCommand("MOVE4");	*/
	    pk1Button.setActionCommand("1");
	    pk2Button.setActionCommand("2");
	    pk3Button.setActionCommand("3");

	    atButton1.addActionListener(e -> handleAction(e.getActionCommand()));
	    atButton2.addActionListener(e -> handleAction(e.getActionCommand()));
/*	    atButton3.addActionListener(e -> handleAction(e.getActionCommand()));
	    atButton4.addActionListener(e -> handleAction(e.getActionCommand()));	*/
	    swButton.addActionListener(e -> switchButtons());
	    pk1Button.addActionListener(e -> handleSwitch(e.getActionCommand()));
	    pk2Button.addActionListener(e -> handleSwitch(e.getActionCommand()));
	    pk3Button.addActionListener(e -> handleSwitch(e.getActionCommand()));;
	    
	    pk1Button.setVisible(false);
	    pk2Button.setVisible(false);
	    pk3Button.setVisible(false);
		
/*		atButton1.setFocusable(false);
		atButton2.setFocusable(false);
		atButton3.setFocusable(false);
		atButton4.setFocusable(false);
		swButton.setFocusable(false); **/
		
		this.add(atButton1);
	    this.add(atButton2);
/*	    this.add(atButton3);
	    this.add(atButton4);	*/
	    this.add(swButton);
	    this.add(pk1Button);
	    this.add(pk2Button);
	    this.add(pk3Button);
		
		sound = new Sound();
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
				// System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}
			
			
		}
	}
	
	public void update() {
		}
	
	private Pokemon generatePokemon(String name) {
	    switch (name) {
	        case "Charmander": return PokemonPool.genCharmander();
	        case "Bulbasaur": return PokemonPool.genBulbasaur();
	        case "Squirtle": return PokemonPool.genSquirtle();
	        case "Robert": return PokemonPool.genRobert();
	        case "Urlox": return PokemonPool.genUrlox();
	        case "Cordol": return PokemonPool.genCordol();
	        case "Sproloquio": return PokemonPool.genSproloquio();
	        case "Domenico": return PokemonPool.genDomenico();
	        case "Bentley": return PokemonPool.genBentley();
	        default: return null;
	    }
	}
	
	public void setupTrainers(String trainer1Name, List<String> selected1, String trainer2Name, List<String> selected2) {
	    // Crea Trainers con Nome (per profilo futuro)
	    player1 = new Trainer(trainer1Name);
	    player2 = new Trainer(trainer2Name);

	    // Aggiunge Pokémon da Stringa usando metodo sorpa
	    for (String pokemonName : selected1) {
	        player1.addPokemon(generatePokemon(pokemonName));
	    }
	    
	    for (String pokemonName : selected2) {
	        player2.addPokemon(generatePokemon(pokemonName));
	    }

	    battle = new Battle(player1, player2);
	}
	
	private enum Turn {
	    PLAYER1,
	    PLAYER2
	}
	
	private void handleAction(String actionCommand) {
	    if (currentTurn == Turn.PLAYER1) {
	        Pokemon activePokemon = player1.getActivePokemon();
	        List<PokemonMove> moves = activePokemon.getMoves();
	        PokemonMove moveName = null;
	        switch (actionCommand) {
	            case "MOVE1":
	                moveName = moves.get(0);
	                break;
	            case "MOVE2":
	            	moveName = moves.get(1);
	                break;
/*	            case "MOVE3":
	            	moveName = moves.get(2);
	                break;
	            case "MOVE4":
	            	moveName = moves.get(3);
	                break; 						*/
	        }

	        // Imposta la mossa attiva
	        if (moveName != null) {
	            activePokemon.setActiveMove(moveName.getName());
	        }
	        endTurn(); // Passa al turno del player2
	    } else if (currentTurn == Turn.PLAYER2) {
	    	Pokemon activePokemon = player2.getActivePokemon();
	        List<PokemonMove> moves = activePokemon.getMoves();
	        PokemonMove moveName = null;
	        switch (actionCommand) {
	            case "MOVE1":
	                moveName = moves.get(0);
	                break;
	            case "MOVE2":
	            	moveName = moves.get(1);
	                break;
/*	            case "MOVE3":
	            	moveName = moves.get(2);
	                break;
	            case "MOVE4":
	            	moveName = moves.get(3);
	                break; 						*/
	        }

	        // Imposta la mossa attiva
	        if (moveName != null) {
	            activePokemon.setActiveMove(moveName.getName());
	        }
	        initBattle(); // Esegui la battaglia solo dopo la scelta di entrambi
	    }
	}
	
	private void handleSwitch(String actionCommand) {
		if (currentTurn == Turn.PLAYER1) {
//	        Pokemon activePokemon = player1.getActivePokemon();
	        switch (actionCommand) {
	            case "1":
	                player1.setActivePokemon(player1.getPokemonList().get(0));
	                player1.getActivePokemon().setActiveMove(null);
	                break;
	            case "2":
	            	player1.setActivePokemon(player1.getPokemonList().get(1));
	            	player1.getActivePokemon().setActiveMove(null);
	                break;
	            case "3":
	            	player1.setActivePokemon(player1.getPokemonList().get(2));
	            	player1.getActivePokemon().setActiveMove(null);
	                break;
	        }
	        
	        atButton1.setVisible(true);
		    atButton2.setVisible(true);
/*	        atButton3.setVisible(true);
		    atButton4.setVisible(true);	*/
		    swButton.setVisible(true);
		    
		    pk1Button.setVisible(false);
		    pk2Button.setVisible(false);
		    pk3Button.setVisible(false);
	        
	        endTurn(); // Passa al turno del player2
	    } else if (currentTurn == Turn.PLAYER2) {
//	    	Pokemon activePokemon = player2.getActivePokemon();
	        switch (actionCommand) {
	            case "1":
	                player2.setActivePokemon(player2.getPokemonList().get(0));
	                player2.getActivePokemon().setActiveMove(null);
	                break;
	            case "2":
	            	player2.setActivePokemon(player2.getPokemonList().get(1));
	                player2.getActivePokemon().setActiveMove(null);
	                break;
	            case "3":
	            	player2.setActivePokemon(player2.getPokemonList().get(2));
	                player2.getActivePokemon().setActiveMove(null);
	                break;
	        }
	        
	        atButton1.setVisible(true);
		    atButton2.setVisible(true);
/*	        atButton3.setVisible(true);
		    atButton4.setVisible(true);	*/
		    swButton.setVisible(true);
		    
		    pk1Button.setVisible(false);
		    pk2Button.setVisible(false);
		    pk3Button.setVisible(false);
	        
	        initBattle(); // Esegui la battaglia solo dopo la scelta di entrambi
	    }
	}
	
	private void switchButtons() {	
		atButton1.setVisible(false);
	    atButton2.setVisible(false);
/*	    atButton3.setVisible(false);
	    atButton4.setVisible(false);	*/
	    swButton.setVisible(false);
	    
	    pk1Button.setVisible(true);
	    pk2Button.setVisible(true);
	    pk3Button.setVisible(true);
	}
	
	private void initBattle() {
	        battle.runBattle();

	        // Passa il turno a player1
	        currentTurn = Turn.PLAYER1;
	}

	private void endTurn() {
	    if (currentTurn == Turn.PLAYER1) {
	        currentTurn = Turn.PLAYER2;
	    } else {
	    	initBattle(); // Se entrambi hanno scelto, esegui la battaglia
	    }
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
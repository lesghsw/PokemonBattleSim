package com.PokemonBattleSim.main;

import java.awt.BorderLayout;
import java.awt.Color; // per colori
import java.awt.Dimension; // per dimensione finestra
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Pannello di gioco della partita.
 * Questa classe rappresenta l'interfaccia grafica principale della battaglia,
 * permettendo ai giocatori di scegliere mosse, cambiare Pokémon, visualizzare gli sprite e 
 * le barre vita, interagire con l'audio e ricevere informazioni sull'efficacia delle mosse.
 * 
 * @author Giampietri2108347
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable{
	// IMPOSTAZIONI SCHERMO
	private final int screenWidth = 1280;
	private final int screenHeight = 720;
	
	// FPS
	private int FPS = 60;
	
	// VARIE
	private Thread gameThread;
	private Sound sound;
	private Window window;
	private JLabel turnoLabel;
	private String battleMessage = "";
	private boolean messageVisible = false;
	
	// BATTAGLIA
	private Trainer player1;
	private Trainer player2;
	private Battle battle;
	private JButton atButton1;
	private JButton atButton2;
	private JButton swButton;
	private JButton pk1Button;
	private JButton pk2Button;
	private JButton pk3Button;
	private JToggleButton muteButton;
	private Turn currentTurn = Turn.PLAYER1;
	
	/**
	 * Costruttore del pannello di gioco.
	 * Inizializza i componenti grafici e i pulsanti di azione per la battaglia.
	 * 
	 * @param window La finestra principale dell'applicazione, necessaria per navigare tra i vari pannelli.
	 */
	public GamePanel(Window window) {
		this.window = window;
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.setLayout(new BorderLayout());
		
		atButton1 = Pulzante.creaPulzante("", "ref/Button.png", Color.WHITE);
		atButton2 = Pulzante.creaPulzante("", "ref/Button.png", Color.WHITE);
		swButton = Pulzante.creaPulzante("Switch", "ref/Button.png", Color.WHITE);
		pk1Button = Pulzante.creaPulzante("", "ref/Button.png", Color.WHITE);
		pk2Button = Pulzante.creaPulzante("", "ref/Button.png", Color.WHITE);
		pk3Button = Pulzante.creaPulzante("", "ref/Button.png", Color.WHITE);
		String Mon = "ref/MButton.png";
        String Moff= "ref/SelectedMButton.png";
        muteButton = Pulzante.creaTogglePulzante("", Mon, Moff, Color.WHITE);

	    atButton1.setActionCommand("0");
	    atButton2.setActionCommand("1");
	    pk1Button.setActionCommand("0");
	    pk2Button.setActionCommand("1");
	    pk3Button.setActionCommand("2");

	    atButton1.addActionListener(e -> handleAction(e.getActionCommand()));
	    atButton2.addActionListener(e -> handleAction(e.getActionCommand()));
	    swButton.addActionListener(e -> switchButtons());
	    pk1Button.addActionListener(e -> handleSwitch(e.getActionCommand()));
	    pk2Button.addActionListener(e -> handleSwitch(e.getActionCommand()));
	    pk3Button.addActionListener(e -> handleSwitch(e.getActionCommand()));;
	    muteButton.addActionListener(e -> handleSound());
	    
	    turnoLabel = new JLabel("Turno di: ");
	    turnoLabel.setFont(new Font("Arial", Font.BOLD, 32));
	    
	    JPanel topPanel = new JPanel(new BorderLayout());
	    topPanel.setPreferredSize(new Dimension(screenWidth, 50));
	    topPanel.setBackground(Color.WHITE);

	    JPanel topLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    topLeftPanel.add(muteButton);
	    topLeftPanel.setBackground(Color.WHITE);

	    JPanel topRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	    topRightPanel.add(turnoLabel);
	    topRightPanel.setBackground(Color.WHITE);

	    topPanel.add(topLeftPanel, BorderLayout.WEST);
	    topPanel.add(topRightPanel, BorderLayout.EAST);

	    this.add(topPanel, BorderLayout.NORTH);
	    
	    // Pannello tasti battaglia + cambio pkmn
	    JPanel buttonPanel = new JPanel();
	    buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
	    buttonPanel.add(atButton1);
	    buttonPanel.add(atButton2);
	    buttonPanel.add(swButton);
	    buttonPanel.add(pk1Button);
		buttonPanel.add(pk2Button);
		buttonPanel.add(pk3Button);
		buttonPanel.setBackground(Color.WHITE);
		this.add(buttonPanel, BorderLayout.SOUTH);
	    
	    pk1Button.setVisible(false);
	    pk2Button.setVisible(false);
	    pk3Button.setVisible(false);
		
		sound = new Sound();
	}
	
	/**
     * Avvia il thread di gioco e la musica. Aggiorna i pulsanti e l'etichetta del turno.
     */
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
		Random rand = new Random();
		int randomMusic = rand.nextInt(5);
		playMusic(randomMusic);
		updateMoveButtons();
		updateTurnLabel();
	}

	/**
     * Metodo principale del thread di gioco. Gestisce il loop di aggiornamento e rendering.
     */
	@Override
	public void run() {
		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		
		while (gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime -lastTime);
			lastTime = currentTime;
			
			if (delta >= 1) {
				repaint();
				delta--;
			}
			
			if (timer >= 1000000000) {
				timer = 0;
			}
			
			
		}
	}
	
	/**
     * Configura i due allenatori con i nomi e le squadre selezionate.
     * 
     * @param trainer1Name Nome del primo allenatore
     * @param selected1 Lista dei Pokémon scelti dal primo allenatore
     * @param trainer2Name Nome del secondo allenatore
     * @param selected2 Lista dei Pokémon scelti dal secondo allenatore
     */
	public void setupTrainers(String trainer1Name, List<String> selected1, String trainer2Name, List<String> selected2) {
	    // Crea Trainers con Nome
	    player1 = new Trainer(trainer1Name);
	    player2 = new Trainer(trainer2Name);

	    // Aggiunge Pokémon da Stringa usando metodo sorpa
	    for (String pokemonName : selected1) { player1.addPokemon(PokemonPool.generatePokemon(pokemonName)); }  
	    for (String pokemonName : selected2) { player2.addPokemon(PokemonPool.generatePokemon(pokemonName)); }
	    
	    battle = new Battle(player1, player2);
	    currentTurn = Turn.PLAYER1;		// Setta il turno al player 1 per sicurezza
	}
	
	private enum Turn {
	    PLAYER1,
	    PLAYER2
	}
	
	/**
     * Gestisce la mossa scelta dal giocatore durante il proprio turno.
     * 
     * @param actionCommand Indice della mossa selezionata
     */
	private void handleAction(String actionCommand) {
	    Trainer currentTrainer= (currentTurn == Turn.PLAYER1) ? player1 : player2;
	    Pokemon activePokemon = currentTrainer.getActivePokemon();
	    
	    activePokemon.setActiveMove(Integer.parseInt(actionCommand));
	                
	    if (currentTurn == Turn.PLAYER1) {
	    	endTurn(); // Passa al turno del player2
	    } else {
	        initBattle(); // Esegui la battaglia dopo la scelta di entrambi
	    }
	}
	
	  /**
     * Permette al giocatore di cambiare il Pokémon attualmente in campo (attivo).
     * 
     * @param actionCommand Indice del Pokémon selezionato per il cambio
     */
	private void handleSwitch(String actionCommand) {
	    Trainer currentTrainer = (currentTurn == Turn.PLAYER1) ? player1 : player2;
	    
	    Pokemon selectedPokemon = currentTrainer.getPokemonList().get(Integer.parseInt(actionCommand));
	    
	    // Controllo se pkmn è già attivo
	    if (selectedPokemon == currentTrainer.getActivePokemon()) {
	    	showBattleMessage("Non puoi selezionare di nuovo il Pokémon attivo.");
	        return;
	    }
	    
	    // Controllo se pkmn è morto
	    if (selectedPokemon.getHp() <= 0) {
	    	showBattleMessage("Non puoi selezionare un Pokémon esausto!");
	        return;
	    }
	    
	    currentTrainer.setActivePokemon(selectedPokemon);
	    currentTrainer.getActivePokemon().setActiveMove(null);
	    
	    atButton1.setVisible(true);
	    atButton2.setVisible(true);
	    swButton.setVisible(true);
	    
	    pk1Button.setVisible(false);
	    pk2Button.setVisible(false);
	    pk3Button.setVisible(false);
	    
	    if (currentTurn == Turn.PLAYER1) {
	        endTurn();
	    } else {
	        initBattle();
	    }
	}
	
	 /**
     * Aggiorna i pulsanti a schermo durante il cambio dei Pokémon.
     */
	private void switchButtons() {	
		
		// Controllo se sono rimasti pkmn
		Trainer currentTrainer = (currentTurn == Turn.PLAYER1) ? player1 : player2;

		if (currentTrainer.getDeadPokemonCount() == 2) {
		    showBattleMessage("Non puoi cambiare Pokémon, ne hai solo uno vivo!");
		    return;}
		
		atButton1.setVisible(false);
	    atButton2.setVisible(false);
	    swButton.setVisible(false);
	    
	    pk1Button.setVisible(true);
	    pk2Button.setVisible(true);
	    pk3Button.setVisible(true);
	    
	    updateSwitchButtons();
	}
	
	/**
     * Aggiorna i pulsanti di selezione dei Pokémon disponibili per il cambio.
     */
	private void updateSwitchButtons() {
	    Trainer currentTrainer = (currentTurn == Turn.PLAYER1) ? player1 : player2; // Prende trainer attuale
	    List<Pokemon> pokemonList = currentTrainer.getPokemonList();

	    if (pokemonList.size() > 0) pk1Button.setText(pokemonList.get(0).getName());
	    if (pokemonList.size() > 1) pk2Button.setText(pokemonList.get(1).getName());
	    if (pokemonList.size() > 2) pk3Button.setText(pokemonList.get(2).getName());
	}
	
	/**
     * Aggiorna i pulsanti delle mosse disponibili per il Pokémon attivo.
     */
	private void updateMoveButtons() {
	    Pokemon activePokemon = (currentTurn == Turn.PLAYER1) ? player1.getActivePokemon() : player2.getActivePokemon();
	    List<PokemonMove> moves = activePokemon.getMoves();

	    if (moves.size() > 0) atButton1.setText(moves.get(0).getName());
	    if (moves.size() > 1) atButton2.setText(moves.get(1).getName());
	}

    /**
     * Esegue il turno di battaglia tramite le mosse scelte dai giocatori.
     */
	private void initBattle() {
		// Recupera le mosse usate in base alle scelte
		if (player1.getActivePokemon().getActiveMove() != null && player2.getActivePokemon().getActiveMove() != null) {
	    String move1 = player1.getActivePokemon().getActiveMove().getName();
	    String move2 = player2.getActivePokemon().getActiveMove().getName();
	    String pkmn1 = player1.getActivePokemon().getName();
	    String pkmn2 = player2.getActivePokemon().getName();
	    float eff1 = player1.getActivePokemon().calculateEffectiveness(player2.getActivePokemon().getTypes());
	    float eff2 = player2.getActivePokemon().calculateEffectiveness(player1.getActivePokemon().getTypes());
	    String m1 = messEff(eff1);
	    String m2 = messEff(eff2);
	    
	    String finalMessage = pkmn1 + " ha usato " + move1 + "!\n" + m1 + "\n" + pkmn2 + " ha usato " + move2 + "!\n" + m2 + "\n";
	    
	    showBattleMessage(finalMessage);} else if (player1.getActivePokemon().getActiveMove() != null && player2.getActivePokemon().getActiveMove() == null) {
		    String move1 = player1.getActivePokemon().getActiveMove().getName();
		    String pkmn1 = player1.getActivePokemon().getName();
		    float eff1 = player1.getActivePokemon().calculateEffectiveness(player2.getActivePokemon().getTypes());
		    String m1 = messEff(eff1);
		    
		    String finalMessage = pkmn1 + " ha usato " + move1 + "!\n" + m1 + "\n";
		    
		    showBattleMessage(finalMessage);} else if (player1.getActivePokemon().getActiveMove() == null && player2.getActivePokemon().getActiveMove() != null) {
		    String move2 = player2.getActivePokemon().getActiveMove().getName();
		    String pkmn2 = player2.getActivePokemon().getName();
		    float eff2 = player2.getActivePokemon().calculateEffectiveness(player1.getActivePokemon().getTypes());
		    String m2 = messEff(eff2);
		    
		    String finalMessage = pkmn2 + " ha usato " + move2 + "!\n" + m2 + "\n";
		    
		    showBattleMessage(finalMessage);}
	    
		// Esegue battaglia
		int battleRet = battle.runBattle();


	    if (battleRet == 1 && player1.getDeadPokemonCount() == 3) {
	        endGame(player2); // L'argomento di endGame() è il vincitore
	        return;
	    } else if (battleRet == 2 && player2.getDeadPokemonCount() == 3) {
	        endGame(player1);
	        return;
	    }

	    currentTurn = Turn.PLAYER1;
	    updateTurnLabel();
	    updateMoveButtons();
	}

	/**
     * Termina il turno attuale e passa al giocatore successivo.
     */
	private void endTurn() {
	    if (currentTurn == Turn.PLAYER1) {
	        currentTurn = Turn.PLAYER2;
	        updateTurnLabel();
	        if (player2.getActivePokemon() != null) {
	            updateMoveButtons();
	        }
	    } else {
	        if (player1.getActivePokemon() != null) {
	            initBattle();
	        }
	    }
	    updateTurnLabel();
	}
	
	/**
     * Gestisce la fine della partita dichiarando il vincitore.
     * 
     * @param winner Stringa che rappresenta il nome del vincitore della battaglia
     */
	private void endGame(Trainer winner) {
	    winner.trainerWon();
	    if (winner == player1) {
	        player2.trainerLost();
	    } else {
	        player1.trainerLost();
	    }

	    player1.saveTrainerProfile();
	    player2.saveTrainerProfile();
	    
	    gameThread = null;													// Ferma il thread
	    sound.stop();														// Ferma del tutto la musica
	    currentTurn = Turn.PLAYER1;											// Resetta turno per la prossima partita
	    window.getWinPanel().setWinner(winner.getName()); 					// Serve per passare nome vincitore
	    window.getWinPanel().setWinnerTeam(winner.getPokemonList()); 		// Ottieni squadra del vincitore
	    window.showPanel("Win");											// Vai a win
	}
	
	/**
     * Attiva o disattiva la musica di sottofondo.
     */
	private void handleSound() {
		if (sound.isPlaying()) {
	        sound.pause();
	    } else {
	        sound.play();
	        sound.loop();
	    }
	}
	
	/**
     * Aggiorna l'etichetta che indica di chi è il turno attuale.
     */
	private void updateTurnLabel() {
		Trainer currentTrainer= (currentTurn == Turn.PLAYER1) ? player1 : player2;
	    String name = currentTrainer.getName();
	    turnoLabel.setText("Turno di: " + name);
	}
	
	/**
     * Mostra un messaggio a schermo con le informazioni della battaglia.
     * 
     * @param message Il messaggio da visualizzare
     */
	private void showBattleMessage(String message) {
	    battleMessage = message;
	    messageVisible = true;
	    
	    // Nasconde il messaggio dopo 2 secondi
	    new Timer().schedule(new TimerTask() {
	    	@Override
	    	public void run() {
	    	messageVisible = false;}
	    },2000);
	}
	
	/**
     * Restituisce un messaggio basato sull'efficacia di una mossa.
     * 
     * @param eff Il valore in virgola mobile dell'efficacia della mossa rispetto al Pokémon avversario.
     */
	private String messEff(float eff) {
		if (eff > 1.0f) return "It's super effective!";
		else if (eff < 1.0f) return "It's not very effective...";
		else return "";
	}
	
	/**
     * Disegna gli sprite dei Pokémon e gli elementi grafici della battaglia.
     * 
     * @param g L'oggetto Graphics utilizzato per il rendering
     */
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    Graphics2D g2d = (Graphics2D) g;

	    Pokemon player1Pokemon = player1.getActivePokemon();
	    Pokemon player2Pokemon = player2.getActivePokemon();

	    Image playerSprite = Toolkit.getDefaultToolkit().getImage(getClass().getResource(player1Pokemon.getSpriteBack()));
	    Image opponentSprite = Toolkit.getDefaultToolkit().getImage(getClass().getResource(player2Pokemon.getSpriteFront()));

	    int newSize = 256; // Dimensione sprite

	    // Disegna gli sprite
	    g2d.drawImage(playerSprite, 10, 420, newSize, newSize, this);
	    g2d.drawImage(opponentSprite, 920, 110, newSize, newSize, this);

	    // Disegna le barre della vita
	    drawHealthBar(g2d, player1Pokemon, 50, 370);
	    drawHealthBar(g2d, player2Pokemon, 950, 70);

	    if (messageVisible) {
	        g2d.setColor(new Color(0, 0, 0, 150));  // Sfondo semitrasparente
	        g2d.fillRoundRect(400, 500, 480, 120, 15, 15);  // Rettangolo arrotondato

	        g2d.setColor(Color.WHITE);
	        g2d.setFont(new Font("Arial", Font.BOLD, 20));
	        
	        String[] lines = battleMessage.split("\n"); // Dividi il messaggio in righe
	        for (int i = 0; i < lines.length; i++) {
	            g2d.drawString(lines[i], 420, 530 + (i * 25));  // Disegna ogni riga
	        }
	    }
	}

	/**
     * Disegna la barra della vita di un Pokémon.
     * 
     * @param g L'oggetto Graphics usato per disegnare
     * @param pokemon Il Pokémon di cui disegnare la barra della vita
     * @param x Coordinata X della barra
     * @param y Coordinata Y della barra
     * 
     * @author Aloisi2107981
     */
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
	
	/**
     * Riproduce un brano musicale in background.
     * 
     * @param i Indice del brano musicale da riprodurre
     * 
     * @author Giampietri2108347
     */
	public void playMusic(int i) {
		sound.setFile(i);
		sound.play();
		sound.loop();
	}
}
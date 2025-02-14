package com.PokemonBattleSim.main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;

/**
 * Classe principale per la gestione della finestra dell'applicazione.
 * Utilizza un CardLayout per navigare tra diversi pannelli del simulatore.
 * 
 * @author Giampietri2108347
 */
public class Window {
    
    private JFrame window;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private WinPanel winPanel;
    
    private GamePanel gamePanel;
    private CharSelectPanel charSelectPanel;
    private ShowProfilePanel loadSavePanel;
    
    /**
     * Costruttore della classe Window.
     * Inizializza la finestra principale e tutti i pannelli del simulatore.
     */
    public Window() {
        window = new JFrame("Pokémon LES & KRB");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        gamePanel = new GamePanel(this);
        charSelectPanel = new CharSelectPanel(this);
        winPanel = new WinPanel(this);
        loadSavePanel = new ShowProfilePanel(this);
        MenuPanel menuPanel = new MenuPanel(this);
        
        mainPanel.add(menuPanel, "Menu");
        mainPanel.add(charSelectPanel, "CharSelect");
        mainPanel.add(loadSavePanel, "LoadSave");
        mainPanel.add(gamePanel, "Game");
        mainPanel.add(winPanel, "Win");
        
        window.add(mainPanel);
        
        window.setSize(1200, 1000);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
    
    /**
     * Mostra il pannello specificato dal nome.
     * 
     * @param panelName Il nome del pannello da visualizzare.
     */
    public void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }
    
    /**
     * Restituisce il pannello di gioco della partita.
     */
    public GamePanel getGamePanel() {
        return gamePanel;
    }

    /**
     * Restituisce il pannello di selezione dei Pokémon per la partita.
     */
    public CharSelectPanel getCharSelectPanel() {
        return charSelectPanel;
    }
    
    /**
     * Restituisce il pannello di vittoria.
     */
    public WinPanel getWinPanel() {
        return winPanel;
    }
    
    /**
     * Restituisce il pannello dei salvataggi.
     */
    public ShowProfilePanel getProfilePanel() {
        return loadSavePanel;
    }
}
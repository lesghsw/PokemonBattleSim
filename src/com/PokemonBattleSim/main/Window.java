package com.PokemonBattleSim.main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;

public class Window {
    
    private JFrame window;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private WinPanel winPanel;
    
    private GamePanel gamePanel;
    private CharSelectPanel charSelectPanel;
    
    public Window() {
        window = new JFrame("Pokémon LES & KRB");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        gamePanel = new GamePanel(this);
        charSelectPanel = new CharSelectPanel(this);
        winPanel = new WinPanel(this);
        ShowProfilePanel loadSavePanel = new ShowProfilePanel(this);
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
    
    public void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }
    
    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public CharSelectPanel getCharSelectPanel() {
        return charSelectPanel;
    }
    
    public WinPanel getWinPanel() {
        return winPanel;
    }
}
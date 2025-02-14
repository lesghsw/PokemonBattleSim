package com.PokemonBattleSim.main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Classe per la gestione dei suoni nel simulatore di battaglia Pokémon.
 * Questa classe permette di caricare, riprodurre, fermare e mettere in loop effetti sonori e musiche di sottofondo.
 * 
 * @author Giampietri2108347
 */
public class Sound {
	
	Clip clip;
	URL soundURL[] = new URL[30];
	
	/**
	 * Costruttore della classe Sound.
	 * Inizializza gli URL dei file audio utilizzati.
	 */
	public Sound() {
		
		soundURL[0] = getClass().getResource("ref/Battle1.wav");
		soundURL[1] = getClass().getResource("ref/Battle2.wav");
		soundURL[2] = getClass().getResource("ref/Battle3.wav");
		soundURL[3] = getClass().getResource("ref/Battle4.wav");
		soundURL[4] = getClass().getResource("ref/Battle5.wav");
}
	
	/**
	 * Imposta il file da riprodurre.
	 * 
	 * @param i Indice del file.
	 */
	public void setFile(int i) {
		
		try {
			
			//	FORMAT per aprire file audio in java
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Riproduce il file.
	 */
	public void play() {
		
		clip.start();
	}
	
	/**
	 * Mette in "loop" il file.
	 */
	public void loop() {
		
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	/**
	 * Stoppa temporaneamente la riproduzione del file.
	 */
	public void pause() {
		clip.stop();
	}
	
	/**
	 * Stoppa completamente la riproduzione del file e chiude la "queue".
	 */
	public void stop() {
	    if (clip != null) {
	        clip.stop();
	        clip.close();
	    }
	}
	
	/**
	 * Verifica se un file è attualmente in riproduzione
	 */
	public boolean isPlaying() {
	    return clip != null && clip.isRunning();
	}
}
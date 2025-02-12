package com.PokemonBattleSim.main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	Clip clip;
	URL soundURL[] = new URL[30];
	
	public Sound() {
		
		soundURL[0] = getClass().getResource("ref/Battle1.wav");
		soundURL[1] = getClass().getResource("ref/Battle2.wav");
		soundURL[2] = getClass().getResource("ref/Battle3.wav");
		soundURL[3] = getClass().getResource("ref/Battle4.wav");
		soundURL[4] = getClass().getResource("ref/Battle5.wav");
}
	
	public void setFile(int i) {
		
		try {
			
//			FORMAT per aprire file audio in java
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void play() {
		
		clip.start();
	}
	
	public void loop() {
		
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	// Stoppa temporaneamente la musica
	public void pause() {
		clip.stop();
	}
	
	// Stoppa del tutto la musica e la "elimina" dalla queue
	public void stop() {
	    if (clip != null) {
	        clip.stop();
	        clip.close();
	    }
	}
	
	public boolean isPlaying() {
	    return clip != null && clip.isRunning();
	}
}
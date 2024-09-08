package com.PokemonBattleSim.main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	Clip clip;
	URL soundURL[] = new URL[30];
	
	public Sound() {
		
		soundURL[0] = getClass().getResource("sound/Battle1.wav");
		soundURL[1] = getClass().getResource("sound/Battle2.wav");
		soundURL[2] = getClass().getResource("sound/Battle3.wav");
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
	public void stop() {
	
		clip.stop();
	}
}
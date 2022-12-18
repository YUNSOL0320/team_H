package game;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class FirstMusic {
	private Clip song;
	private File musicFile;
	private AudioInputStream audioStream;
	private boolean musicPlaying;
	
	public FirstMusic(boolean musicPlaying) {
		try {
			song = AudioSystem.getClip();
			musicFile = new File("music/LastChristmas.wav");
			audioStream = AudioSystem.getAudioInputStream(musicFile);
			song.open(audioStream);
			
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void start() {
		//song.setFramePosition(0);
		song.start();
		if(musicPlaying) {
			song.loop(song.LOOP_CONTINUOUSLY);
		}
	}
	
	public void stop() {
		song.stop();
		song.setMicrosecondPosition(0);
	}
	
}
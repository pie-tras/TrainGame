package main.audio;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music {
	
	 public Clip clip;
	 
	    public Music(String fileName) {
	        // specify the sound to play
	        // (assuming the sound can be played by the audio system)
	        // from a wave File
	        try {
	           File file = new File(fileName);
	            if (file.exists()) {
	            	 AudioInputStream sound = AudioSystem.getAudioInputStream(file);
	                 AudioFormat format = sound.getFormat();
	                 DataLine.Info info = new DataLine.Info(Clip.class, format);
	                 clip = (Clip)AudioSystem.getLine(info);
	                 clip.open(sound);
	            }
	            else {
	                throw new RuntimeException("Sound: file not found: " + fileName);
	            }
	        }
	        catch (MalformedURLException e) {
	            e.printStackTrace();
	            throw new RuntimeException("Sound: Malformed URL: " + e);
	        }
	        catch (UnsupportedAudioFileException e) {
	            e.printStackTrace();
	            throw new RuntimeException("Sound: Unsupported Audio File: " + e);
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	            throw new RuntimeException("Sound: Input/Output Error: " + e);
	        }
	        catch (LineUnavailableException e) {
	            e.printStackTrace();
	            throw new RuntimeException("Sound: Line Unavailable Exception Error: " + e);
	        }

	    // play, stop, loop the sound clip
	    }
	    public void play(){
	        clip.setFramePosition(0);  // Must always rewind!
	        clip.start();
	    }
	    public void loop(){
	    	clip.loop(Clip.LOOP_CONTINUOUSLY);
	    	
	    }
	    public void stop(){
	    	clip.setFramePosition(0);  // Must always rewind!
	    	clip.stop();
	    }
	    
	    public void reduceVol(float amt){
	    	FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	    	float currentVol=volume.getValue();
	    	
	    	volume.setValue(currentVol-amt);
	    }
	    
	    public void increaseVol(){
	    	
	    }
}

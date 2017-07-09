package main;

import main.audio.Music;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableAutoConfiguration
public class Settings {

    @Value("${audio.banjo:res/audio/Banjo.wav}")
	private String banjo;
	
    @Value("${audio.coin:res/audio/Coin.wav}")
	private String coinSound;
	
    @Value("${audio.tracks:res/audio/Tracks.wav}")
	private String trackSound; 
    
    @Value("${audio.tracks:res/audio/ambientCave.wav}")
	private String ambientCave; 
	
	
	@Bean(name="banjoSound")
	public Music getBanjoSound() {
	    return new Music(banjo);
	}
	
	@Bean(name="coinSound")
	public Music getCoinSound() {
	    return new Music(coinSound);
	}
	
	@Bean(name="trackSound")
	public Music getTrackSound() {
	    return new Music(trackSound);
	}
	
	@Bean(name="ambientCaveSound")
	public Music getAmbientCaveSound() {
	    return new Music(ambientCave);
	}
}

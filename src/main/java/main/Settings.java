package main;

import java.awt.Dimension;

import javax.sql.DataSource;
import javax.swing.JFrame;

import main.audio.Music;
import main.gfx.Assets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Settings {
    
    static {
        System.setProperty("java.awt.headless", "false");
        Assets.init();
    }
    
    @Value("${window.height:600}") 
	private int height;
    
    @Value("${window.width:800}") 
	private int width;
    
    @Value("${window.title:Train Game}") 
	private String title;


    @Value("${audio.banjo}") // :res/audio/Banjo.wav}")
	private String banjoFile;
	
    @Value("${audio.coin:res/audio/Coin.wav}")
	private String coinFile;
	
    @Value("${audio.tracks:res/audio/Tracks.wav}")
	private String trackFile; 
    
    @Value("${audio.tracks:res/audio/ambientCave.wav}")
	private String ambientFile; 
	
	@Value("${spring.datasource.driver-class-name:org.sqlite.JDBC}")
	private String jdbcDriver;
	
	@Value("${spring.datasource.url:jdbc:sqlite:test.db}")
	private String jdbcUrl;
    
	@Bean(name="banjoSound")
	public Music banjoSound() {
	    return new Music(banjoFile);
	}
	
	@Bean(name="coinSound")
	public Music coinSound() {
	    return new Music(coinFile);
	}
	
	@Bean(name="trackSound")
	public Music trackSound() {
	    return new Music(trackFile);
	}
	
	@Bean(name="ambientCaveSound")
	public Music ambientCaveSound() {
	    return new Music(ambientFile);
	}
	
	@Bean
	public DataSource dataSource() {
	    DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
	    dataSourceBuilder.driverClassName(jdbcDriver);
	    dataSourceBuilder.url(jdbcUrl);
	    return dataSourceBuilder.build();   
	}
	
	@Bean
	public JFrame window() {
	    JFrame window= new JFrame(title);
	        
	    window.setPreferredSize(new Dimension(width, height));
	    window.setMaximumSize(new Dimension(width, height));
	    window.setMinimumSize(new Dimension(width, height));
	        
	    window.setResizable(false);
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setLocationRelativeTo(null);
	        
	    return window;
	}
	
	
	@Bean
	public int windowHeight() { return height; }
	
	@Bean
	public int windowWidth() { return width; }
}

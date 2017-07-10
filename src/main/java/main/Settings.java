package main;

import javax.sql.DataSource;

import main.audio.Music;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableAutoConfiguration
public class Settings {

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
	
}

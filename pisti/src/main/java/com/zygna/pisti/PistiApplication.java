package com.zygna.pisti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.zygna.pisti.service.GameService;

@SpringBootApplication
public class PistiApplication implements CommandLineRunner{
	
	@Autowired
	private ApplicationContext applicationContext;

	public static void main(String[] args) { 
		SpringApplication.run(PistiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		GameService game = applicationContext.getBean(GameService.class);
		game.startGame();
		
	}

}

package com.zygna.pisti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.zygna.pisti.bot.BotService;
import com.zygna.pisti.manager.GameManager;
import com.zygna.pisti.pojo.Bot;

@SpringBootApplication
public class PistiApplication implements CommandLineRunner {
	
	@Autowired
	private ApplicationContext applicationContext;

	public static void main(String[] args) { 
		SpringApplication.run(PistiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		String errorMessage = String.join("\n", "\n\n Error: Please control the args, must be like (N) (R) (SC) (DC) where " ,
															" (N) is total player count, 4 or 4 multiples ex:12 " ,
															" (R) is game count for each group of players, >0 ex:12 " ,
															" (SC) is smartbot class name ex:com.zygna.pisti.bot.SmartBotService " ,
															" (DC) is dummybot class name ex:com.zygna.pisti.bot.DummyBotService " , 
															" !(SC) and (DC) must be a BotService interface implementation class");
		
		if(args.length<4){
			throw new Exception(errorMessage);
		}

		Integer totalPlayerCount=null;
		Integer gameCountForPlayersGroup=null;
		String smartBotClassName=null;
		String dummyBotClassName=null;
		
		BotService smartBotService=null;
		BotService dummyBotService=null;
		
		try {
			totalPlayerCount = Integer.valueOf(args[0]);
			gameCountForPlayersGroup = Integer.valueOf(args[1]);
			
			if(totalPlayerCount%4!=0 || gameCountForPlayersGroup<=0){
				throw new Exception();
			}
			
			smartBotClassName = args[2];
			dummyBotClassName = args[3];
			smartBotService = (BotService) applicationContext.getBean(Class.forName(smartBotClassName));
			dummyBotService = (BotService) applicationContext.getBean(Class.forName(dummyBotClassName));
		} catch (Exception e) {
			throw new Exception(errorMessage);
		}
		
		Bot smartBot = new Bot("smart-bot",smartBotService);
		Bot dummyBot = new Bot("dummy-bot",dummyBotService);
		
		GameManager game = applicationContext.getBean(GameManager.class);
		game.initGames(totalPlayerCount,gameCountForPlayersGroup,smartBot,dummyBot);
		
	}

}

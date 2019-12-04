package com.zygna.pisti.manager.impl;

import java.util.Random;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zygna.pisti.manager.GameManager;
import com.zygna.pisti.manager.TableManager;
import com.zygna.pisti.pojo.Bot;
import com.zygna.pisti.pojo.Player;
import com.zygna.pisti.pojo.Table;
import com.zygna.pisti.service.DealerService;
import com.zygna.pisti.service.impl.DealerServiceImpl;
import com.zygna.pisti.service.impl.PlayerServiceImpl;

@Service
public class GameManagerImpl implements GameManager {
	
	Logger logger = LoggerFactory.getLogger(GameManagerImpl.class);
	
	@Override
	public void initGames(Integer playersNo,Integer gamesNo,Bot smartBot,Bot dummyBot) {
		
		logger.info("init new game is started, playersNo {} gamesNo {}",playersNo,gamesNo);

		//Create N players and assign an ID 
		Stack<Player> players = new Stack<Player>();
		for(int i=1;i<=playersNo;i++) {
			boolean isDealer = playersNo%4==0?true:false;
			
			boolean smartPlayer;
			int rand = new Random().nextInt(11);
			if(rand>5){
				smartPlayer=true;
			} else {
				smartPlayer=false;
			}
			
			Player player = new Player(i, isDealer,smartPlayer);
			players.push(player);
			
		}

		while (players.size()>=4) {

			new Thread(()->{

				Player rightPlayer = null;
				Player nextPlayer = null;
				Player leftPlayer = null;
				Player dealer = null;

				synchronized (players) {
					if(players.size()>=4) {
						rightPlayer = players.pop();
						nextPlayer = players.pop();
						leftPlayer = players.pop();
						dealer = players.pop();
					}
				}
				
				if(rightPlayer!=null) {
					Table table = new Table();
					table.setGamesNo(gamesNo);
					TableManager tableManager = new TableManagerImpl(table);

					Bot rightPlayerBot = rightPlayer.isSmartPlayer()?smartBot:dummyBot;
					Bot nextPlayerBot = nextPlayer.isSmartPlayer()?smartBot:dummyBot;
					Bot leftPlayerBot = leftPlayer.isSmartPlayer()?smartBot:dummyBot;
					Bot dealerBot = dealer.isSmartPlayer()?smartBot:dummyBot;
					
					new PlayerServiceImpl(rightPlayer, tableManager, rightPlayerBot);
					new PlayerServiceImpl(nextPlayer, tableManager, nextPlayerBot);
					new PlayerServiceImpl(leftPlayer, tableManager, leftPlayerBot);
					DealerService dealerService =  new DealerServiceImpl(dealer, tableManager, dealerBot);
					
					dealerService.startGames(gamesNo);
				}
			}).start();

		}
		
	}

}

package com.zygna.pisti.service.impl;

import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zygna.pisti.bot.DummyBotService;
import com.zygna.pisti.pojo.Bot;
import com.zygna.pisti.pojo.Card;
import com.zygna.pisti.pojo.Deck;
import com.zygna.pisti.pojo.Player;
import com.zygna.pisti.pojo.Table;
import com.zygna.pisti.service.DealerService;
import com.zygna.pisti.service.GameService;
import com.zygna.pisti.utils.CardsUtil;

@Service
public class GameServiceImpl implements GameService {
	
	Logger logger = LoggerFactory.getLogger(GameServiceImpl.class);
	
	@Override
	public void startGame() {
		
		logger.info("startGame is started");
		
		Stack<Card> cards = CardsUtil.getOrderedCards();

		Deck deck = new Deck(cards);
		
		Table table = new Table(deck);
		
		Player player1 = new Player(1, table, false);
		Player player2 = new Player(2, table, false);
		Player player3 = new Player(3, table, false);
		Player dealer = new Player(4, table, true);
		
		new PlayerServiceImpl(player1, table, new Bot("boot-1", new DummyBotService()));
		new PlayerServiceImpl(player2, table, new Bot("boot-2", new DummyBotService()));
		new PlayerServiceImpl(player3, table, new Bot("boot-3", new DummyBotService()));
		DealerService dealerService =  new DealerServiceImpl(dealer, table, new Bot("boot-4", new DummyBotService()));
		
		dealerService.startGame();
		
		
	}

}

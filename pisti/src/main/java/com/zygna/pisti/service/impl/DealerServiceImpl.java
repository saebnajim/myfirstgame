package com.zygna.pisti.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zygna.pisti.manager.TableManager;
import com.zygna.pisti.pojo.Bot;
import com.zygna.pisti.pojo.Card;
import com.zygna.pisti.pojo.Deck;
import com.zygna.pisti.pojo.Player;
import com.zygna.pisti.pojo.Table;
import com.zygna.pisti.service.DealerService;
import com.zygna.pisti.utils.CardsUtil;

public class DealerServiceImpl extends PlayerServiceImpl implements DealerService {

	Logger logger = LoggerFactory.getLogger(DealerServiceImpl.class);
 
	public DealerServiceImpl(Player player,TableManager tableManager,Bot bot){
		super(player, tableManager, bot);
	}

	@Override
	public void startGames(int gamesNo) {
		
		Table table = tableManager.getTable();
		List<Player> players = table.getPlayers();
		for(int k=1;k<=gamesNo;k++) {

			logger.info("{} game is started , players ({} , {} , {} , {})",k,players.get(0).getId(),players.get(1).getId(),players.get(2).getId(),players.get(3).getId());
			
			Stack<Card> orderedCards = CardsUtil.getOrderedCards();
			
			Deck deck = new Deck(orderedCards);
			table.setDeck(deck);
			table.setPile(new Stack<>());
			table.setCardsEndupPlayers(0);

			this.startGame();
		}
	}
	
	private void startGame() {
		
		Table table = tableManager.getTable();
		
		Deck deck = table.getDeck();

		// Shuffle the deck
		Stack<Card> cards = deck.getCards();
		Collections.shuffle(cards);
		deck.setCards(cards);
		
		// Deal 4 cards facing up in the center of table
		cards = deck.getCards();
		for(int i=0;i<4;i++){
			Card card = cards.pop();
			card.setFacingUp(true);
		}
		
		// Deals 4 cards to each player (beginning from the dealer’s right and ending with the dealer). 
		dealCardsToPlayers();
		
	}

	@Override
	public void dealCardsToPlayers() {
		
		Table table = tableManager.getTable();
		Deck deck = table.getDeck();
		
		if(deck.getCards().size()>0){
			table.getPlayers().forEach(p->{
				List<Card> cardsOnHand = new ArrayList<>();
				Stack<Card> cards = deck.getCards();
				for(int i=0;i<4;i++){
					Card card = cards.pop();
					cardsOnHand.add(card);
				}
				p.setCardsOnHand(cardsOnHand);
			});
			
			

			tableManager.startGame();
			
		}
	}
	
	
	
}

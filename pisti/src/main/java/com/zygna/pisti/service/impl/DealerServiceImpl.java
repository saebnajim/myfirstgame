package com.zygna.pisti.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zygna.pisti.pojo.Bot;
import com.zygna.pisti.pojo.Card;
import com.zygna.pisti.pojo.Deck;
import com.zygna.pisti.pojo.Player;
import com.zygna.pisti.pojo.Table;
import com.zygna.pisti.service.DealerService;

public class DealerServiceImpl extends PlayerServiceImpl implements DealerService {

	Logger logger = LoggerFactory.getLogger(Table.class);
 
	public DealerServiceImpl(Player player,Table table,Bot bot){
		super(player, table, bot);
	}

	@Override
	public void startGame() {
		
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
		
		Deck deck = table.getDeck();
		
		logger.info("Cards Size : {}",deck.getCards().size());
		
		if(deck.getCards().size()>0){
			table.getPlayerServices().forEach(ps->{
				Player p = ps.getPlayer();
				List<Card> cardsOnHand = new ArrayList<>();
				Stack<Card> cards = deck.getCards();
				for(int i=0;i<4;i++){
					Card card = cards.pop();
					cardsOnHand.add(card);
				}
				p.setCardsOnHand(cardsOnHand);
			});
			
			table.startGame();
			
		}
	}
	
	
	
}

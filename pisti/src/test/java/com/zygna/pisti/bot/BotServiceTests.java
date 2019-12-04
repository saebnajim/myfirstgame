package com.zygna.pisti.bot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zygna.pisti.enums.CardName;
import com.zygna.pisti.pojo.Card;
import com.zygna.pisti.pojo.Table;
import com.zygna.pisti.utils.CardsUtil;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(classes={DummyBotService.class,SmartBotService.class})
public class BotServiceTests {
	
	
	Logger logger = LoggerFactory.getLogger(BotServiceTests.class);
	
	@Autowired
	@Qualifier("dummyBotService")
	BotService dummyBotService;

	
	@Autowired
	@Qualifier("smartBotService")
	BotService smartBotService;

	
	@Test
	public void dummyBotServiceTest(){
		
		logger.info("dummyBotServiceTest is start");
		
		Stack<Card> orderedCards = CardsUtil.getOrderedCards();
		
		List<Card> cardsOnHand =  new ArrayList<Card>();
		
		for(int i=0;i<25;i++){
			Card card= orderedCards.pop();
			logger.info("{} - {} has been added to cardsOnHand ", card.getCardName().toString(), card.getCardType().toString());
			cardsOnHand.add(card);
		}
		
		for(int i=0;i<4;i++){
			Card card= orderedCards.pop();
			logger.info("{} - {} has been added to cardsOnHand of another user ", card.getCardName().toString(), card.getCardType().toString());
		}
		
		Card lastDiscardedCard = orderedCards.pop();
		Table table = new Table();
		table.setLastDiscardedCard(lastDiscardedCard);
		
		logger.info("Last Discarded Card is {} - {} " , lastDiscardedCard.getCardName(), lastDiscardedCard.getCardType().toString());
		
		Card suitableCard = dummyBotService.getSuitableCard(cardsOnHand, table); 
		
		logger.info("Suitable Card is {} - {} ",suitableCard.getCardName() , suitableCard.getCardType().toString());
		
	}

	
	@Test
	public void smartBotServiceTest(){
		
		logger.info("smartBotServiceTest is start");
		
		Stack<Card> orderedCards = CardsUtil.getOrderedCards();
		
		List<Card> cardsOnHand =  new ArrayList<Card>();
		
		for(int i=0;i<2;i++){
			Card card= orderedCards.pop();
			logger.info("{} - {} has been added to cardsOnHand ", card.getCardName().toString(), card.getCardType().toString());
			cardsOnHand.add(card);
		}
		
		for(int i=0;i<3;i++){
			Card card= orderedCards.pop();
			logger.info("{} - {} has been added to cardsOnHand of another user ", card.getCardName().toString(), card.getCardType().toString());
		}
		
		Map<String,Integer> allPlayedCardsCounter=new ConcurrentHashMap<>();
		allPlayedCardsCounter.put(CardName.C_Queen.toString(), 3);
		
//		for(int i=0;i<25;i++){
//			
//			Card card= orderedCards.pop();
//			logger.info("{} - {} has been faced up on the table ", card.getCardName().toString(), card.getCardType().toString());
//			String key = card.getCardName().toString(); 
//			if(allPlayedCardsCounter.containsKey(key)){
//				Integer count = allPlayedCardsCounter.get(key);
//				count++;
//				allPlayedCardsCounter.put(key, count);
//			} else {
//				allPlayedCardsCounter.put(key, 1);
//			}
//
//		}
		
		Card lastDiscardedCard = orderedCards.pop();
		Table table = new Table();
		table.setLastDiscardedCard(lastDiscardedCard);
		table.setAllPlayedCardsCounter(allPlayedCardsCounter);
		
		logger.info("Last Discarded Card is {} - {} " , lastDiscardedCard.getCardName(), lastDiscardedCard.getCardType().toString());
		
		Card suitableCard = smartBotService.getSuitableCard(cardsOnHand, table); 
		
		logger.info("Suitable Card is {} - {} ",suitableCard.getCardName() , suitableCard.getCardType().toString());
		
	}


}

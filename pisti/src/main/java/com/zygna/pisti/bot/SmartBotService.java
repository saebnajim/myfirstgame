package com.zygna.pisti.bot;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zygna.pisti.enums.CardName;
import com.zygna.pisti.pojo.Card;
import com.zygna.pisti.pojo.Table;

@Service
@Qualifier("smartBotService")
public class SmartBotService implements BotService {

	@Override
	public Card getSuitableCard(List<Card> cardsOnHand,Table table) {
		
		Card  lastDiscardedCard = table.getLastDiscardedCard();
		Map<String,Integer> allPlayedCardsCounter = table.getAllPlayedCardsCounter();
		
		Card result=null;
		
		Card same=null;
		Card jack=null;
		Card playedCard=null;
		Card minRank = null;
		
		for(Card card:cardsOnHand){
			
			CardName cardName = card.getCardName();
			if(lastDiscardedCard!=null && cardName.equals(lastDiscardedCard.getCardName())) {
				//  If the bot has a card that matches the last played card, bot plays that card.
				same = card;
				break;
			} else if(lastDiscardedCard!=null && CardName.C_Jack.equals(cardName)) {
				//  If the bot has a jack and there are cards on the discard pile, bot plays jack.
				jack = card;
			} 
			
			if(allPlayedCardsCounter.containsKey(cardName.toString())){
				
				Integer count = allPlayedCardsCounter.get(cardName.toString());
				if(count==3){
					//  If the bot has a card that matches a three times played card, bot plays that card. 
					playedCard = card;
				}
				
			}
			
			// Else; the bot plays the smallest ranking card in his hand.
			if(minRank==null || card.getRank()<minRank.getRank()){
				minRank = card;
			}
			
		}
		
		if(same!=null) {
			result = same;
		} else  if(jack!=null) {
			result = jack;
		} else  if(playedCard!=null) {
			result = playedCard;
		} else {
			result = minRank;
		}
		
		cardsOnHand.remove(result);
		return result;
	}

}

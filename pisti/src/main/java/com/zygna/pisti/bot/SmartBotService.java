package com.zygna.pisti.bot;

import java.util.List;

import com.zygna.pisti.enums.CardName;
import com.zygna.pisti.pojo.Card;

public class SmartBotService implements BotService {

	@Override
	public Card getSuitableCard(List<Card> cardsOnHand,Card lastDiscardedCard) {
		
		Card result=null;
		
		Card same=null;
		Card jack=null;
		Card minRank = null;
		
		
		for(Card card:cardsOnHand){
			
			if(card.getCardName().equals(lastDiscardedCard.getCardName())) {
				//  If the bot has a card that matches the last played card, bot plays that card.
				same = card;
				break;
			} else if(CardName.C_Jack.equals(card.getCardName())) {
				//  If the bot has a jack and there are cards on the discard pile, bot plays jack.
				jack = card;
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
		} else {
			result = minRank;
		}
		
		cardsOnHand.remove(result);
		return result;
	}

}

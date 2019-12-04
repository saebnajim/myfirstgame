package com.zygna.pisti.bot;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zygna.pisti.enums.CardName;
import com.zygna.pisti.pojo.Card;
import com.zygna.pisti.pojo.Table;

@Service
@Qualifier("dummyBotService")
public class DummyBotService implements BotService{

	@Override
	public Card getSuitableCard(List<Card> cardsOnHand,Table table) {

		Card  lastDiscardedCard = table.getLastDiscardedCard();
		
		Card result=null;
		
		Card same=null;
		Card jack=null;
		Card minRank = null;
		
		for(Card card:cardsOnHand){
			
			if(lastDiscardedCard!=null && card.getCardName().equals(lastDiscardedCard.getCardName())) {
				//  If the bot has a card that matches the last played card, bot plays that card.
				same = card;
				break;
			} else if(lastDiscardedCard!=null && CardName.C_Jack.equals(card.getCardName())) {
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

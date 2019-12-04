package com.zygna.pisti.bot;

import java.util.List;

import com.zygna.pisti.pojo.Card;

public interface BotService {

	Card getSuitableCard(List<Card> cardsOnHand,Card lastDiscardedCard);
	
}

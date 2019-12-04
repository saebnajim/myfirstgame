package com.zygna.pisti.bot;

import java.util.List;

import com.zygna.pisti.pojo.Card;
import com.zygna.pisti.pojo.Table;

public interface BotService {

	Card getSuitableCard(List<Card> cardsOnHand,Table table);
	
}

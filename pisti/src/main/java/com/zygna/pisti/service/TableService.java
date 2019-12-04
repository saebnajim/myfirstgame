package com.zygna.pisti.service;

import com.zygna.pisti.pojo.Card;

public interface TableService {

	public void startGame();
	
	public void endGame();
	
	public void addPlayerToTable(PlayerService playerService);
	
	public void discardedCard(PlayerService playerService,Card card);

}

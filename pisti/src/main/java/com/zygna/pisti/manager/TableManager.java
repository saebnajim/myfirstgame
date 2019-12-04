package com.zygna.pisti.manager;

import com.zygna.pisti.pojo.Card;
import com.zygna.pisti.pojo.Player;
import com.zygna.pisti.pojo.Table;
import com.zygna.pisti.service.PlayerService;

public interface TableManager {

	public void startGame();
	
	public void endGame();
	
	public void addPlayerToTable(PlayerService playerService) throws Exception;
	
	public void discardedCard(Player player,Card card);
	
	public Table getTable();

}

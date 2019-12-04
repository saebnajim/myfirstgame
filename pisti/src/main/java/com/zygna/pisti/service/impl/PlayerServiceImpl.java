package com.zygna.pisti.service.impl;

import com.zygna.pisti.pojo.Bot;
import com.zygna.pisti.pojo.Card;
import com.zygna.pisti.pojo.Player;
import com.zygna.pisti.pojo.Table;
import com.zygna.pisti.service.PlayerService;

public class PlayerServiceImpl implements PlayerService {

	private Player player;
	protected final Table table;
	private Bot bot;
	
	public PlayerServiceImpl(Player player,Table table,Bot bot){
		this.player=player;
		this.table=table;
		this.bot=bot;
		
		try {
			this.table.addPlayerToTable(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Player getPlayer() {
		return player;
	}

	@Override
	public Bot getBot() {
		return bot;
	}

	public void addScore(int totalScore) {
		int score = player.getScore();
		score += totalScore;
		player.setScore(score);
	}

	@Override
	public void addCollectedCards(int collectedCardsNo) {
		int collectedCardsNoTmp = player.getCollectedCardsNo();
		collectedCardsNoTmp += collectedCardsNo;
		player.setCollectedCardsNo(collectedCardsNoTmp);
	}

	@Override
	public boolean play(){
		Card card = bot.getBotService().getSuitableCard(player.getCardsOnHand(), table.getLastDiscardedCard());
		table.discardedCard(this, card); 
		return true;
	}
	
}

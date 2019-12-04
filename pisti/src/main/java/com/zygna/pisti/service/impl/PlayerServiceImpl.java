package com.zygna.pisti.service.impl;

import com.zygna.pisti.manager.TableManager;
import com.zygna.pisti.pojo.Bot;
import com.zygna.pisti.pojo.Card;
import com.zygna.pisti.pojo.Player;
import com.zygna.pisti.service.PlayerService;

public class PlayerServiceImpl implements PlayerService {

	private Player player;
	protected TableManager tableManager;
	private Bot bot;
	
	public PlayerServiceImpl(Player player,TableManager tableManager,Bot bot){
		this.player=player;
		this.tableManager=tableManager;
		this.bot=bot;
		
		try {
			this.tableManager.addPlayerToTable(this);
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
		Card card = bot.getBotService().getSuitableCard(player.getCardsOnHand(), tableManager.getTable());
		tableManager.discardedCard(player, card); 
		return true;
	}
	
}

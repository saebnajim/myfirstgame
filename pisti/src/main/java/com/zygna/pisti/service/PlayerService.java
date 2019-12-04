package com.zygna.pisti.service;

import com.zygna.pisti.pojo.Bot;
import com.zygna.pisti.pojo.Player;

public interface PlayerService {

	public Player getPlayer();

	public Bot getBot();

	public void addScore(int totalScore);

	public void addCollectedCards(int collectedCardsNo);

	public boolean play();

}

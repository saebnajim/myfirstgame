package com.zygna.pisti.pojo;

import java.util.ArrayList;
import java.util.List;


public class Player {

	private final long id;
	private List<Card> cardsOnHand;
	private int score;
	private final boolean isADealer;
	private int collectedCardsNo;
	private final boolean smartPlayer;
	
	public Player(long id,boolean isADealer,boolean smartPlayer){
		this.id=id;
		this.isADealer=isADealer;
		this.smartPlayer=smartPlayer;
		this.cardsOnHand = new ArrayList<Card>();
	}

	public List<Card> getCardsOnHand() {
		return cardsOnHand;
	}

	public void setCardsOnHand(List<Card> cardsOnHand) {
		this.cardsOnHand = cardsOnHand;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isADealer() {
		return isADealer;
	}

	public int getCollectedCardsNo() {
		return collectedCardsNo;
	}

	public void setCollectedCardsNo(int collectedCardsNo) {
		this.collectedCardsNo = collectedCardsNo;
	}

	public long getId() {
		return id;
	}

	public boolean isSmartPlayer() {
		return smartPlayer;
	}
	
}

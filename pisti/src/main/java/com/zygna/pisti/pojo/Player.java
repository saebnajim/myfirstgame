package com.zygna.pisti.pojo;

import java.util.ArrayList;
import java.util.List;


public class Player {

	private final Integer id;
	protected final Table table;
	private List<Card> cardsOnHand;
	private int score;
	private final boolean isADealer;
	private int collectedCardsNo;
	
	public Player(Integer id,Table table,boolean isADealer){
		this.id=id;
		this.table=table;
		this.isADealer=isADealer;
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

	public Integer getId() {
		return id;
	}

	public Table getTable() {
		return table;
	}
	
}

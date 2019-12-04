package com.zygna.pisti.pojo;

import java.util.ArrayList;
import java.util.List;


public class Player {

	private final Integer id;
	protected final Table table;
	private List<Card> cardsOnHand;
	private int score;
	private boolean isDealer;
	private int collectedCardsNo;
	
	public Player(Integer id,Table table,boolean isDealer){
		this.id=id;
		this.table=table;
		this.isDealer=isDealer;
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

	public boolean isDealer() {
		return isDealer;
	}

	public void setDealer(boolean isDealer) {
		this.isDealer = isDealer;
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

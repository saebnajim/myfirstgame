package com.zygna.pisti.pojo;

import java.util.Stack;

public class Deck {

	private Stack<Card> cards;
	
	public Deck(Stack<Card> cards){
		this.cards=cards;
	}

	public Stack<Card> getCards() {
		return cards;
	}

	public void setCards(Stack<Card> cards) {
		this.cards = cards;
	}
	
	
	
}

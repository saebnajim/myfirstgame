package com.zygna.pisti.pojo;

import com.zygna.pisti.enums.CardName;
import com.zygna.pisti.enums.CardType;

public class Card {

	private CardType cardType;
	private CardName cardName;
	private boolean facingUp;
	private int rank;
	
	public Card(CardType cardType,CardName cardName,int rank){
		this.cardType=cardType;
		this.cardName=cardName;
		this.rank=rank;
	}

	public CardType getCardType() {
		return cardType;
	}

	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}

	public CardName getCardName() {
		return cardName;
	}

	public void setCardName(CardName cardName) {
		this.cardName = cardName;
	}

	public boolean isFacingUp() {
		return facingUp;
	}

	public void setFacingUp(boolean facingUp) {
		this.facingUp = facingUp;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardName == null) ? 0 : cardName.hashCode());
		result = prime * result + ((cardType == null) ? 0 : cardType.hashCode());
		result = prime * result + rank;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (cardName != other.cardName)
			return false;
		if (cardType != other.cardType)
			return false;
		if (rank != other.rank)
			return false;
		return true;
	}

}

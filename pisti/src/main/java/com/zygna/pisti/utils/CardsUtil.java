package com.zygna.pisti.utils;

import java.util.Stack;

import com.zygna.pisti.enums.CardName;
import com.zygna.pisti.enums.CardType;
import com.zygna.pisti.pojo.Card;

public class CardsUtil {
	
	
	public static Stack<Card> getOrderedCards(){
		
		Stack<Card> cards = new Stack<>();
		
		cards.push(new Card(CardType.Spade, CardName.C_Ace,1));
		cards.push(new Card(CardType.Spade, CardName.C_2,0));
		cards.push(new Card(CardType.Spade, CardName.C_3,0));
		cards.push(new Card(CardType.Spade, CardName.C_4,0));
		cards.push(new Card(CardType.Spade, CardName.C_5,0));
		cards.push(new Card(CardType.Spade, CardName.C_6,0));
		cards.push(new Card(CardType.Spade, CardName.C_7,0));
		cards.push(new Card(CardType.Spade, CardName.C_8,0));
		cards.push(new Card(CardType.Spade, CardName.C_9,0));
		cards.push(new Card(CardType.Spade, CardName.C_10,0));
		cards.push(new Card(CardType.Spade, CardName.C_Jack,1));
		cards.push(new Card(CardType.Spade, CardName.C_Queen,0));
		cards.push(new Card(CardType.Spade, CardName.C_King,0));
		
		cards.push(new Card(CardType.Heart, CardName.C_Ace,1));
		cards.push(new Card(CardType.Heart, CardName.C_2,0));
		cards.push(new Card(CardType.Heart, CardName.C_3,0));
		cards.push(new Card(CardType.Heart, CardName.C_4,0));
		cards.push(new Card(CardType.Heart, CardName.C_5,0));
		cards.push(new Card(CardType.Heart, CardName.C_6,0));
		cards.push(new Card(CardType.Heart, CardName.C_7,0));
		cards.push(new Card(CardType.Heart, CardName.C_8,0));
		cards.push(new Card(CardType.Heart, CardName.C_9,0));
		cards.push(new Card(CardType.Heart, CardName.C_10,0));
		cards.push(new Card(CardType.Heart, CardName.C_Jack,1));
		cards.push(new Card(CardType.Heart, CardName.C_Queen,0));
		cards.push(new Card(CardType.Heart, CardName.C_King,0));
		
		cards.push(new Card(CardType.Diamond, CardName.C_Ace,1));
		cards.push(new Card(CardType.Diamond, CardName.C_2,0));
		cards.push(new Card(CardType.Diamond, CardName.C_3,0));
		cards.push(new Card(CardType.Diamond, CardName.C_4,0));
		cards.push(new Card(CardType.Diamond, CardName.C_5,0));
		cards.push(new Card(CardType.Diamond, CardName.C_6,0));
		cards.push(new Card(CardType.Diamond, CardName.C_7,0));
		cards.push(new Card(CardType.Diamond, CardName.C_8,0));
		cards.push(new Card(CardType.Diamond, CardName.C_9,0));
		cards.push(new Card(CardType.Diamond, CardName.C_10,3));
		cards.push(new Card(CardType.Diamond, CardName.C_Jack,1));
		cards.push(new Card(CardType.Diamond, CardName.C_Queen,0));
		cards.push(new Card(CardType.Diamond, CardName.C_King,0));
		
		cards.push(new Card(CardType.Club, CardName.C_Ace,1));
		cards.push(new Card(CardType.Club, CardName.C_2,2));
		cards.push(new Card(CardType.Club, CardName.C_3,0));
		cards.push(new Card(CardType.Club, CardName.C_4,0));
		cards.push(new Card(CardType.Club, CardName.C_5,0));
		cards.push(new Card(CardType.Club, CardName.C_6,0));
		cards.push(new Card(CardType.Club, CardName.C_7,0));
		cards.push(new Card(CardType.Club, CardName.C_8,0));
		cards.push(new Card(CardType.Club, CardName.C_9,0));
		cards.push(new Card(CardType.Club, CardName.C_10,0));
		cards.push(new Card(CardType.Club, CardName.C_Jack,1));
		cards.push(new Card(CardType.Club, CardName.C_Queen,0));
		cards.push(new Card(CardType.Club, CardName.C_King,0));
		
		return cards;
	}

}

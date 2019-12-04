package com.zygna.pisti.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Table {

	Logger logger = LoggerFactory.getLogger(Table.class);

	private Deck deck;
	private List<Player> players;
	private Stack<Card> pile;
	private Card lastDiscardedCard;
	private List<Card> firstFacingUpCards;
	private int cardsEndupPlayers = 0;
	private int gamesNo = 0;
	private Map<String,Integer> allPlayedCardsCounter;
	
	public Table() {
		this.pile = new Stack<>();
		this.players = new ArrayList<>();
		this.allPlayedCardsCounter=new ConcurrentHashMap<>();
	}
	
	public Table(Deck deck) {
		this.deck = deck;
		this.pile = new Stack<>();
		this.players = new ArrayList<Player>();
		this.allPlayedCardsCounter=new ConcurrentHashMap<>();
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public Stack<Card> getPile() {
		return pile;
	}

	public void setPile(Stack<Card> pile) {
		this.pile = pile;
	}

	public Card getLastDiscardedCard() {
		return lastDiscardedCard;
	}

	public void setLastDiscardedCard(Card lastDiscardedCard) {
		this.lastDiscardedCard = lastDiscardedCard;
	}

	public List<Card> getFirstFacingUpCards() {
		return firstFacingUpCards;
	}

	public void setFirstFacingUpCards(List<Card> firstFacingUpCards) {
		this.firstFacingUpCards = firstFacingUpCards;
	}

	public int getCardsEndupPlayers() {
		return cardsEndupPlayers;
	}

	public void setCardsEndupPlayers(int cardsEndupPlayers) {
		this.cardsEndupPlayers = cardsEndupPlayers;
	}

	public int getGamesNo() {
		return gamesNo;
	}

	public void setGamesNo(int gamesNo) {
		this.gamesNo = gamesNo;
	}

	public Map<String,Integer> getAllPlayedCardsCounter() {
		return allPlayedCardsCounter;
	}

	public void setAllPlayedCardsCounter(Map<String,Integer> allPlayedCardsCounter) {
		this.allPlayedCardsCounter = allPlayedCardsCounter;
	}

 
	

}

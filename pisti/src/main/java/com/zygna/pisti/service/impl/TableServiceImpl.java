package com.zygna.pisti.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zygna.pisti.enums.CardName;
import com.zygna.pisti.pojo.Card;
import com.zygna.pisti.pojo.Deck;
import com.zygna.pisti.pojo.Player;
import com.zygna.pisti.service.DealerService;
import com.zygna.pisti.service.PlayerService;

public class TableServiceImpl {
	
	Logger logger = LoggerFactory.getLogger(TableServiceImpl.class);

	private List<PlayerService> playerServices;
	private Deck deck;
	private Stack<Card> pile;
	private Card lastDiscardedCard;
	private List<Card> firstFacingUpCards;
	private DealerService dealerService;
	private int cardsEndupPlayers=0;

	public TableServiceImpl(Deck deck) {
		this.deck = deck;
		this.pile = new Stack<>();
		this.playerServices = new ArrayList<>();
	}

	public List<PlayerService> getPlayerServices() {
		return playerServices;
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
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

	public void startGame() {

		// Start as 2,1,4,3 location player to play

		for(int i=0;i<4;i++){
			PlayerService onRightPlayer = playerServices.get(0);
			PlayerService onTopPlayer = playerServices.get(1);
			PlayerService onLeftPlayer = playerServices.get(2);
			PlayerService dealerPlayer = playerServices.get(3);
			
			onRightPlayer.play();
			onTopPlayer.play();
			onLeftPlayer.play();
			dealerPlayer.play();
		}

	}
	
	public void endGame() {
		
		playerServices.forEach(ps ->{
			Player player = ps.getPlayer();
			String log = String.format("%s | %s | %s",player.getId(),ps.getBot().getBotName(),player.getScore());
			logger.info(log);
		});
		
	
	}
	
	public void addPlayerToTable(PlayerService playerService) throws Exception{
		
		if(playerServices.size()<4){
			playerServices.add(playerService);
			if(playerService instanceof DealerService){
				dealerService =(DealerService) playerService;
			}
		} else {
			throw new Exception("can't add new player, players size is 4 !");
		}
	}
	
	
	public void discardedCard(PlayerService playerService,Card card){
		
		pile.push(card);
		lastDiscardedCard = card;
		
		if(pile.size()==1){
			return;
		}
		
		//Playing a jack also collects the whole pile, no matter what card is the latest played card AND Add the collected score to the player's score
		if(CardName.C_Jack.equals(card.getCardName())){

			int pileSize = pile.size();
			int totalScore = getPileScore();

			// If the pile consists of just a single jack and you capture it with another jack, this counts as a double “pisti”, which is worth 20 points. 
			if(pileSize==2 && CardName.C_Jack.equals(lastDiscardedCard.getCardName())){
				totalScore += 20;
			}
			
			playerService.addScore(totalScore);
			playerService.addCollectedCards(pileSize);
			this.lastDiscardedCard=null;

		} else if(lastDiscardedCard.equals(card)){
			// If the rank of the played (discarded) card matches the rank of the last card on the pile, the player collects the whole pile.
			int pileSize = pile.size();
			int totalScore = getPileScore();
			
			//If the pile consists of just one card and the next player collects it by matching a card (not a jack), the collecting player scores a 10 point bonus for a “pisti”.
			if(pile.size()==2){
				totalScore += 10;
			}
			
			playerService.addScore(totalScore);
			playerService.addCollectedCards(pileSize);
			this.lastDiscardedCard=null;
		} 

		//If the played card is not equal to the top card of the pile, the played card is simply added to the top of the pile.
		
		if(playerService.getPlayer().getCardsOnHand().isEmpty()){
			cardsEndupPlayers++;
		}
		
		if(cardsEndupPlayers==4){
			if(deck.getCards().size()>0){
				cardsEndupPlayers=0;
				dealerService.dealCardsToPlayers();
			} else {
				
				PlayerService hasMaxPlayer = null;
				
				for(PlayerService ps:playerServices){
					Player p = ps.getPlayer();
					int collectedCardsNo = p.getCollectedCardsNo();
					if(hasMaxPlayer==null || hasMaxPlayer.getPlayer().getCollectedCardsNo()<collectedCardsNo){
						hasMaxPlayer = ps;
					}
				}

				hasMaxPlayer.addCollectedCards(3);
				
				endGame();
			}
		}
		
	}
	
	private int getPileScore(){
		
		int totalScore = 0;
		for(int i=0;i<pile.size();i++) {
			Card cardTmp = pile.pop();
			int score = cardTmp.getRank();
			totalScore += score;
		}

		return totalScore;
		
	}

}

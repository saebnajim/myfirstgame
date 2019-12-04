package com.zygna.pisti.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zygna.pisti.enums.CardName;
import com.zygna.pisti.manager.TableManager;
import com.zygna.pisti.pojo.Card;
import com.zygna.pisti.pojo.Player;
import com.zygna.pisti.pojo.Table;
import com.zygna.pisti.service.DealerService;
import com.zygna.pisti.service.PlayerService;

public class TableManagerImpl implements TableManager {
	
	Logger logger = LoggerFactory.getLogger(TableManagerImpl.class);

	private List<PlayerService> playerServices;

	private Table table;
	
	private DealerService dealerService;
	
	public TableManagerImpl(Table table) {
		this.table=table;
		this.playerServices = new ArrayList<PlayerService>();
	}

	public void setFirstFacingUpCards(List<Card> firstFacingUpCards) {
		table.setFirstFacingUpCards(firstFacingUpCards);
		table.setLastDiscardedCard(firstFacingUpCards.get(3));
	}

	public void startGame() {

		for(int i=0;i<4;i++){
			PlayerService rightPlayer = playerServices.get(0);
			PlayerService nextPlayer = playerServices.get(1);
			PlayerService leftPlayer = playerServices.get(2);
			PlayerService dealer = playerServices.get(3);
			
			rightPlayer.play();
			nextPlayer.play();
			leftPlayer.play();
			dealer.play();
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
			table.getPlayers().add(playerService.getPlayer());
			if(playerService instanceof DealerService){
				dealerService =(DealerService) playerService;
			}
		} else {
			throw new Exception("can't add new player, players size is 4 !");
		}
	}
	
	
	public void discardedCard(Player player,Card card){
		
		Stack<Card> pile = table.getPile();	
		Card lastDiscardedCard = table.getLastDiscardedCard();
		Integer cardsEndupPlayers = table.getCardsEndupPlayers();
		
		pile.push(card);
		card.setFacingUp(true);
		lastDiscardedCard = card;
		
		
		Map<String,Integer> allPlayedCardsCounter = table.getAllPlayedCardsCounter();
		String key = card.getCardName().toString();
		if(allPlayedCardsCounter.containsKey(key)){
			Integer count = allPlayedCardsCounter.get(key);
			count++;
			allPlayedCardsCounter.put(key, count);
		} else {
			allPlayedCardsCounter.put(key, 1);
		}
		
		if(pile.size()==1){
			return;
		}
		
		//Playing a jack also collects the whole pile, no matter what card is the latest played card AND Add the collected score to the player's score
		if(CardName.C_Jack.equals(card.getCardName())){

			int pileSize = pile.size();
			int totalScore = getPileScore(pile);

			// If the pile consists of just a single jack and you capture it with another jack, this counts as a double “pisti”, which is worth 20 points. 
			if(pileSize==2 && CardName.C_Jack.equals(lastDiscardedCard.getCardName())){
				totalScore += 20;
			}
			
			addScore(player,totalScore);
			addCollectedCards(player,pileSize);
			lastDiscardedCard=null;

		} else if(lastDiscardedCard.equals(card)){
			// If the rank of the played (discarded) card matches the rank of the last card on the pile, the player collects the whole pile.
			int pileSize = pile.size();
			int totalScore = getPileScore(pile);
			
			//If the pile consists of just one card and the next player collects it by matching a card (not a jack), the collecting player scores a 10 point bonus for a “pisti”.
			if(pile.size()==2){
				totalScore += 10;
			}
			
			addScore(player,totalScore);
			addCollectedCards(player,pileSize);
			lastDiscardedCard=null;
		} 

		//If the played card is not equal to the top card of the pile, the played card is simply added to the top of the pile.
		
		if(player.getCardsOnHand().isEmpty()){
			cardsEndupPlayers++;
		}
		
		 table.setPile(pile);	
		 table.setLastDiscardedCard(lastDiscardedCard);
		 table.setCardsEndupPlayers(cardsEndupPlayers);

		if(cardsEndupPlayers==4){
			if(table.getDeck().getCards().size()>0){
				cardsEndupPlayers=0;
				table.setCardsEndupPlayers(cardsEndupPlayers);
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
				
				table.setAllPlayedCardsCounter(new ConcurrentHashMap<>());
				
				int gamesNo = table.getGamesNo()-1;
				if(gamesNo==0) {
					endGame();
				} else {
					table.setGamesNo(gamesNo);
				}
			}
		}
		

	}
	
	public Table getTable() {
		return table;
	}

	private int getPileScore(Stack<Card> pile){
		
		int totalScore = 0;
		for(int i=0;i<pile.size();i++) {
			Card cardTmp = pile.pop();
			int score = cardTmp.getRank();
			totalScore += score;
		}

		return totalScore;
		
	}
	
	private void addScore(Player player, int totalScore) {
		int score = player.getScore();
		score += totalScore;
		player.setScore(score);
	}

	private void addCollectedCards(Player player,int collectedCardsNo) {
		int collectedCardsNoTmp = player.getCollectedCardsNo();
		collectedCardsNoTmp += collectedCardsNo;
		player.setCollectedCardsNo(collectedCardsNoTmp);
	}

}

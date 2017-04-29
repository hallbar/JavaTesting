package edu.osu.blackjack;

import java.util.*;

public  class Dealer implements DealerAction{


	
	private List<Card> dealerHand = new ArrayList<Card>();
	private List<Card> deck;

	public Dealer(){
		reset();
	}

	@Override
	public void acceptCard(Card c) {
		// deck.add(c);
		dealerHand.add(c);
		if(dealerHand.size() == 2) {
			dealerHand.get(1).setVisible(false);
		}
	}

	@Override
	public void shuffleDeck() {
		Collections.shuffle(deck);
	}

	@Override
	public void dealCard(CommonAction toPlayer) {
		Card c = this.dealCardFromDeck(true);
		toPlayer.acceptCard(c);
	}

	@Override
	public void compareHandAndSettle(PlayerAction p) {
		int dealerScore = handScore(dealerHand);
		int currentBet = p.makeBet();
		int playerScore = handScore(p.getHand());

		// System.out.println("Dealer " + dealerScore + " Player: " + playerScore);
		
		if(dealerScore<playerScore){
			p.acceptMoney(currentBet*2);
		}
		else if(isInsuranceAvailable() && dealerScore == 21){
			p.acceptMoney(currentBet*3);
		}
		
		// deck.addAll(p.getHand());
		p.nextHand();
	
		// deck.addAll(dealerHand);
		dealerHand.clear();
	}


	
	public void reset(){
		
		this.dealerHand = new ArrayList<Card>();
		this.deck = new ArrayList<Card>();
		this.setDeck(Card.newDeck());
	}
	
	public void setDeck(List<Card> c){
		this.deck = c;
	}
	

	public boolean isInsuranceAvailable() {
		for(Card c: dealerHand){
			if(c.face == Card.Face.ACE && c.isVisible()){
				return true;
			}
		}
		return false;
	}

	private int handScore(List<Card> currentHand) {
		int score = 0;
		for(Card c:currentHand){
			// System.out.print(c.toString() + ";");
			score += c.face.getValue();
			if(score > 21)
				return -1;
		}
		// System.out.println("");
		return score;
	}

	/*
	public boolean isAnyoneBroke() {
		for(PlayerState p : playerState){
			if(p.currentWallet != null && p.currentWallet < 0)
				return true;
		}
		return false;
	}*/
	
	public static void dumpDeck(List<Card> c){
		System.out.print("Deck:");
		for(Card z : c) System.out.print(c.toString() + ";");
		System.out.println("");
	}


	private Card dealCardFromDeck(boolean b) {
		Card c = deck.remove(0);
		c.setVisible(b);
		return c;
	}

	@Override
	public List<Card> getHand() {
		return this.dealerHand;
	}

	public int getDeckSize() {
		return this.deck.size();
	}

	public void clearHand() {
		this.dealerHand.clear();
	}

}



	

	



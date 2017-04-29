package edu.osu.blackjack;



public class SimpleBlackjack {
	public enum ActionType {HIT , DOUBLE, STAND};
	private int numPlayers;
	
	DealerAction dealer;
	PlayerAction[] players ;

	
	public SimpleBlackjack(DealerAction d, PlayerAction[] ip){
		this.dealer = d;
		this.players = ip;
	}
	
	public void playRound(){

		dealer.shuffleDeck();
		
		for(PlayerAction p: players ){
			p.makeBet();
		}
		
		/***** old code *****/
		// for(PlayerAction p: players ){
		// 	dealer.dealCard(dealer);
		// 	dealer.dealCard(p);
		// }

		/***** new code *****/
		//this combines the fixes included in report1.txt and report2.txt
		for(int i = 0; i < 2; i++) {
			for(PlayerAction p: players) {
				dealer.dealCard(p);
			}
			dealer.dealCard(dealer);
		}
	
		if(dealer.isInsuranceAvailable()){
			for(PlayerAction p: players ){
				p.makeInsuranceBet();
			}
		}

		for(PlayerAction p: players ){
			boolean turnOver = false;
			while(!turnOver){
				PlayerAction.ActionType a = p.getAction();	
				switch(a){
					case HIT:
						dealer.dealCard(p);		
						break;
					case DOUBLE:
						p.doubleDownBet();
						dealer.dealCard(p);
						turnOver = true;
						break;
					default: // stand
						turnOver = true;
						break;
				}
		
			}
		}

		// using rules where dealer stands on hard and soft 17
		// while(dealer.handScore(dealer.dealerHand) < 17) {
		// 	dealer.dealCard(dealer);
		// }

		for(PlayerAction p: players){
			dealer.compareHandAndSettle(p);
		}
	}
}

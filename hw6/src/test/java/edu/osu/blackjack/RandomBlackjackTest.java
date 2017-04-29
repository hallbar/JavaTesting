package edu.osu.blackjack;


import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;
import static org.mockito.Mockito.*;

public class RandomBlackjackTest {
	@Test
    public void testRandomRound() {
    	for(int i = 0; i < 100; i++) {
    		Random randomNumberOfDecks = new Random();
    		Random randomNumberOfPlayers = new Random();
    		Random randomWallet = new Random();
    		Random randomDD = new Random();
    		Random numPlayerActions = new Random();

    		Dealer dealer = new Dealer();
    		List<Card> deck = new ArrayList<Card>();
    		int numberOfDecks = randomNumberOfDecks.nextInt(3) + 1;
    		int numberOfPlayers = randomNumberOfPlayers.nextInt(4) + 1;
    		PlayerAction[] playerArray = new PlayerAction[numberOfPlayers];

            int p1Wallet = 0;
            int p2Wallet = 0;
            int p3Wallet = 0;
            int p4Wallet = 0;
            SubPlayer p1 = new SubPlayer();
            SubPlayer p2 = new SubPlayer();
            SubPlayer p3 = new SubPlayer();
            SubPlayer p4 = new SubPlayer();

    		for(int j = 0; j < numberOfDecks; j++) {
    			deck.addAll(Card.newDeck());
    		}
    		dealer.setDeck(deck);

            int originalSizeOfDealerDeck = dealer.getDeckSize();

            assertEquals(52 * numberOfDecks, originalSizeOfDealerDeck);

    		// SubPlayer p1 = new SubPlayer();
            p1Wallet = randomWallet.nextInt(901) + 100;
    		p1.currentWallet = p1Wallet;
    		playerArray[0] = p1;

    		if(numberOfPlayers > 1) {
    			// SubPlayer p2 = new SubPlayer();
                p2Wallet = randomWallet.nextInt(901) + 100;
    			p2.currentWallet = p2Wallet;
    			playerArray[1] = p2;
    		}

    		if(numberOfPlayers > 2) {
    			// SubPlayer p3 = new SubPlayer();
                p3Wallet = randomWallet.nextInt(901) + 100;
    			p3.currentWallet = p3Wallet;
    			playerArray[2] = p3;
    		}

    		if(numberOfPlayers > 3) {
    			// SubPlayer p4 = new SubPlayer();
                p4Wallet = randomWallet.nextInt(901) + 100;
    			p4.currentWallet = p4Wallet;
    			playerArray[3] = p4;
    		}

            // System.out.println("num players " + numberOfPlayers);

    		SimpleBlackjack game = new SimpleBlackjack(dealer, playerArray);

            game.playRound();

            // cards were dealt to the players and dealer, so the deck should
            // have fewer cards than at the start
            assertTrue(originalSizeOfDealerDeck > dealer.getDeckSize());

            // under current settings, there are no pushes between dealer and
            // player, so the wallet should change after each round
            assertNotEquals((int) p1.currentWallet, p1Wallet);
            if(numberOfPlayers > 1){
                assertNotEquals((int) p2.currentWallet, p2Wallet);
            }
            if(numberOfPlayers > 2) {
                assertNotEquals((int) p3.currentWallet, p3Wallet);
            }
            if(numberOfPlayers > 3){
                assertNotEquals((int) p4.currentWallet, p4Wallet);
            }


    	}
   	}
}
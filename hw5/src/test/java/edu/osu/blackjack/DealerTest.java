package edu.osu.blackjack;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class DealerTest {
	@Test
	public void testDumpDeck() {
		Dealer dealer = new Dealer();

		Card testCard = new Card(Card.Face.TWO, Card.Suit.CLUB);

		List<Card> c = new ArrayList<Card>();
		c.add(testCard);

		dealer.dumpDeck(c);
	}

	@Test
	public void testDeal() {
		Dealer dealer = new Dealer();

		dealer.dealCard(dealer);

		// first card in deck is two of clubs
		// after it is dealt to dealer, it will be placed as last card
		// of the deck (51 since no cards are dealt to others)
		// and set to visible
		assertEquals("TWOCLUB", dealer.getHand().get(51).toString());
		assertEquals(true, dealer.getHand().get(51).isVisible());
	}

	// @Test
	// public void testIsInsuranceAvailable() {
	// 	Dealer dealer = new Dealer();

	// 	// ace of clubs is 13th card in deck
	// 	// check for insurance after first 12 have been dealt
	// 	for (int i = 0; i < 12; i++) {
	// 		dealer.dealCard(dealer);
	// 	}

	// 	assertEquals(false, dealer.isInsuranceAvailable());

	// 	// deal ace of clubs
	// 	dealer.dealCard(dealer);

	// 	assertEquals(true, dealer.isInsuranceAvailable());
	// }

	@Test
	public void testShuffleDeck() {
		List<Card> controlDeck = new ArrayList<Card>();

		controlDeck = Card.newDeck();

		Dealer dealer = new Dealer();

		dealer.shuffleDeck();

		assertNotEquals(controlDeck, dealer.getHand());
	}

	@Test
	public void testCompareHandAndSettle() {
		Dealer dealer = new Dealer();
		Player player = new Player();

		// dealer is dealt two of clubs handScore = 2
		dealer.dealCard(dealer);
		// player is dealt three of clubs handScore = 3
		dealer.dealCard(player);
		// dealer is dealt four of clubs handScore = 6
		dealer.dealCard(dealer);
		// player is dealt five of clubs handScore = 8
		dealer.dealCard(player);

		player.makeBet();

		// dealer score = 6 and player score = 8
		dealer.compareHandAndSettle(player);

		// int wallet = player.currentWallet;
		// assertEquals(1010, wallet);

		Dealer dealer2 = new Dealer();
		Player player2 = new Player();

		// player2 is dealt two of clubs handScore = 2
		dealer2.dealCard(player2);
		// dealer2 is dealt three of clubs handScore = 3
		dealer2.dealCard(dealer2);
		// player 2 is dealt four of clubs handScore = 6
		dealer2.dealCard(player2);
		// dealer2 is dealt five of clubs handScore = 8
		dealer2.dealCard(dealer2);

		player2.makeBet();

		// dealer score = 8 and player score = 6
		dealer2.compareHandAndSettle(player2);

		// int wallet2 = player2.currentWallet;
		// assertEquals(990, wallet2);

		Dealer dealer3 = new Dealer();
		Player player3 = new Player();

		// dealer3 is dealt two of clubs handScore = 2
		dealer3.dealCard(dealer3);
		// player3 is dealt three of clubs handScore = 3
		dealer3.dealCard(player3);
		// dealer3 is dealt four of clubs handScore = 6
		dealer3.dealCard(dealer3);
		// player3 is dealt five of clubs handScore = 8
		dealer3.dealCard(player3);
		// dealer3 is dealt six of clubs handScore = 12
		dealer3.dealCard(dealer3);
		// dealer3 is dealt seven of clubs handScore = 19
		dealer3.dealCard(dealer3);
		// dealer3 is dealt eight of clubs handScore = 27 busted
		dealer3.dealCard(dealer3);

		player3.makeBet();

		// dealer score 27 and player score = 8
		dealer3.compareHandAndSettle(player3);

		// int wallet3 = player3.currentWallet;
		// assertEquals(1010, wallet3);

		Dealer dealer4 = new Dealer();
		Player player4 = new Player();

		// dealer4 is dealt two of clubs handScore = 2
		dealer4.dealCard(dealer4);
		// player4 is dealt three of clubs handScore = 3
		dealer4.dealCard(player4);
		// dealer4 is dealt four of clubs handScore = 6
		dealer4.dealCard(dealer4);
		// player4 is dealt five of clubs handScore = 8
		dealer4.dealCard(player4);
		// player4 is dealt six of clubs handScore = 14
		dealer4.dealCard(player4);
		// player4 is dealt seven of clubs handScore = 21
		dealer4.dealCard(player4);
		// player4 is dealt eight of clubs handScore = 29 busted
		dealer4.dealCard(player4);

		player4.makeBet();

		// dealer score 6 and player score = 29
		dealer4.compareHandAndSettle(player4);

		// int wallet4 = player4.currentWallet;
		// assertEquals(990, wallet4);
	}
}
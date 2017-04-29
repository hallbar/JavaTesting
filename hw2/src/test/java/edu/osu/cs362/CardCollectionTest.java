package edu.osu.cs362;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

public class CardCollectionTest {
	
	@Test
	public void testAddCard() {
		CardCollection hand = new CardCollection();

		Card card = new Card(Card.Face.TWO, Card.Suit.HEART);
		hand.add(card);
		
		List<Card> cards = hand.getCards();

		// check for properties of inserted card
		assertEquals(2, cards.get(0).face.getValue());
		assertEquals("TWO", cards.get(0).face.name());
		assertEquals("HEART", cards.get(0).suit.name());
	}

	@Test
	public void testAddCards() {
		CardCollection hand = new CardCollection();
		Card card1 = new Card(Card.Face.TWO, Card.Suit.CLUB);
		Card card2 = new Card(Card.Face.THREE, Card.Suit.CLUB);
		
		List<Card> cards = new ArrayList<Card>();
		cards.add(card1);
		cards.add(card2);
	
		hand.add(cards);

		List<Card> cardsReturned = hand.getCards();

		// check for properties of inserted cards
		assertEquals(2, cardsReturned.get(0).face.getValue());
		assertEquals("TWO", cardsReturned.get(0).face.name());
		assertEquals("CLUB", cardsReturned.get(0).suit.name());
		assertEquals(3, cardsReturned.get(1).face.getValue());
		assertEquals("THREE", cardsReturned.get(1).face.name());
		assertEquals("CLUB", cardsReturned.get(1).suit.name());
	}

	@Test
	public void testDiscardCard() {
		CardCollection hand = new CardCollection();
	
		hand.add(new Card(Card.Face.TWO, Card.Suit.CLUB));
		hand.add(new Card(Card.Face.THREE, Card.Suit.SPADE));
		hand.add(new Card(Card.Face.ACE, Card.Suit.HEART));

		Card discarded = hand.discardCard(1);

		// check properties of card that is discarded
		assertEquals(3, discarded.face.getValue());
		assertEquals("THREE", discarded.face.name());
		assertEquals("SPADE", discarded.suit.name());

		assertEquals(11, hand.getCards().get(1).face.getValue());
		assertEquals("ACE", hand.getCards().get(1).face.name());
		assertEquals("HEART", hand.getCards().get(1).suit.name());
	}

	@Test
	public void testCardCollection() {
		CardCollection hand = new CardCollection(new Card(Card.Face.ACE, Card.Suit.HEART));

		List<Card> cards = hand.getCards();

		assertEquals(11, cards.get(0).face.getValue());
	}

	@Test
	public void testSingleDeckDiscard() {

		ArrayList<Card> singleControl = Card.newDeck();
		CardCollection singleDeck = new CardCollection();
		singleDeck.add(Card.newDeck());

		// discard first card
		singleDeck.discardCard(0);

		// check that second card has moved to first position
		assertEquals(true, singleControl.get(1).equals(singleDeck.getCards().get(0)));

		CardCollection singleDeck2 = new CardCollection();
		singleDeck2.add(Card.newDeck());

		// discard 14th card
		singleDeck2.discardCard(13);

		// check card before discarded card and card replacing discarded card
		assertEquals(true, singleControl.get(12).equals(singleDeck2.getCards().get(12)));
		assertEquals(true, singleControl.get(14).equals(singleDeck2.getCards().get(13)));

		CardCollection singleDeck3 = new CardCollection();
		singleDeck3.add(Card.newDeck());

		// discard last card
		singleDeck3.discardCard(51);

		// check card before discarded card
		assertEquals(true, singleControl.get(50).equals(singleDeck3.getCards().get(50)));
	}

	@Test
	public void testDoubleDeckDiscard() {
		ArrayList<Card> singleControl= Card.newDeck();

		CardCollection doubleDeck1 = new CardCollection();
	
		doubleDeck1.add(Card.newDeck());
		doubleDeck1.add(Card.newDeck());

		// discard first card
		doubleDeck1.discardCard(0);

		// check that second card has moved to first position
		assertEquals(true, singleControl.get(1).equals(doubleDeck1.getCards().get(0)));
		
		CardCollection doubleDeck2 = new CardCollection();
	
		doubleDeck2.add(Card.newDeck());
		doubleDeck2.add(Card.newDeck());

		doubleDeck2.discardCard(23);

		// check card before discarded card and card replacing discarded card
		assertEquals(true, singleControl.get(22).equals(doubleDeck2.getCards().get(22)));
		assertEquals(true, singleControl.get(24).equals(doubleDeck2.getCards().get(23)));

		CardCollection doubleDeck3 = new CardCollection();
	
		doubleDeck3.add(Card.newDeck());
		doubleDeck3.add(Card.newDeck());

		doubleDeck3.discardCard(52);
		
		// check card before discarded card and card replacing discarded card
		assertEquals(singleControl.get(51), doubleDeck3.getCards().get(51));
		assertEquals(true, singleControl.get(51).equals(doubleDeck3.getCards().get(51)));
		assertEquals(true, singleControl.get(1).equals(doubleDeck3.getCards().get(52)));

		CardCollection doubleDeck4 = new CardCollection();
	
		doubleDeck4.add(Card.newDeck());
		doubleDeck4.add(Card.newDeck());

		doubleDeck4.discardCard(71);

		// check card before discarded card and card replacing discarded card
		assertEquals(true, singleControl.get(18).equals(doubleDeck4.getCards().get(70)));
		assertEquals(true, singleControl.get(20).equals(doubleDeck4.getCards().get(71)));
		

	}
}

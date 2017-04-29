package edu.osu.blackjack;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

public class CardTest {
	
	@Test
	public void testNewDeck(){
		ArrayList<Card> result = Card.newDeck();
		
		String[] faces = {"TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN", "JACK", "QUEEN", "KING", "ACE"};
		String[] suits = {"CLUB", "DIAMOND", "HEART", "SPADE"};

		// run through deck 10 times checking for correc t order of cards
		for(int run = 0; run < 10; run++) {
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 13; j++) {
					int cardNum = i * 13 + j;
					assertEquals(faces[j], result.get(cardNum).face.name());
					assertEquals(suits[i], result.get(cardNum).suit.name());
				}
			}
		}
	}
	
	@Test
	public void testCard() {
		Card result = new Card(Card.Face.TWO, Card.Suit.CLUB);
		assertEquals(2, result.face.getValue());
		assertEquals("TWO", result.face.name());
		assertEquals("CLUB", result.suit.name());

		Card result2 = new Card(Card.Face.ACE, Card.Suit.HEART);
		assertEquals(11, result2.face.getValue());
		assertEquals("ACE", result2.face.name());
		assertEquals("HEART", result2.suit.name());
	}

	@Test
	public void testVisible() {
		Card card = new Card(Card.Face.QUEEN, Card.Suit.SPADE);

		boolean visibility = card.isVisible();

		assertEquals(false, visibility);
		
		card.setVisible(true);
		
		visibility = card.isVisible();
		assertEquals(true, visibility);

		card.setVisible(false);
		visibility = card.isVisible();
		assertEquals(false, visibility);
	}

	@Test
	public void testToString() {
		Card card = new Card(Card.Face.TEN, Card.Suit.DIAMOND);
		String cardString = card.toString();
		
		assertEquals("TENDIAMOND", cardString);
	}
}

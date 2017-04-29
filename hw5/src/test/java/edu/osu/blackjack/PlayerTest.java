package edu.osu.blackjack;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class PlayerTest {
	@Test
	public void testPlayer() {
		Player player = new Player();

		int wallet = player.currentWallet;

		assertEquals(1000, wallet);
	}
	@Test
	public void testMoveMoneyToInsurance() {
		Player player = new Player();

		player.moveMoneyToInsurance(0);

		int insurance = player.currentInsurance;

		assertEquals(0, insurance);

		player.moveMoneyToInsurance(100);

		insurance = player.currentInsurance;
		int wallet = player.currentWallet;

		assertEquals(100, insurance);
		assertEquals(900, wallet);

		Player player2 = new Player();

		player2.moveMoneyToInsurance(1000);

		int insurance2 = player2.currentInsurance;
		int wallet2 = player2.currentWallet;

		assertEquals(1000, insurance2);
		assertEquals(0, wallet2);

		// Player player3 = new Player();

		// player3.moveMoneyToInsurance(1001);
	}

	@Test
	public void testMoveMoneyToBet() {
		Player player = new Player();

		player.moveMoneyToBet(0);

		int bet = player.currentBet;

		assertEquals(0, bet);

		player.moveMoneyToBet(100);

		bet = player.currentBet;
		int wallet = player.currentWallet;

		assertEquals(100, bet);
		assertEquals(900, wallet);

		Player player2 = new Player();

		player2.moveMoneyToBet(1000);

		int bet2 = player2.currentBet;
		int wallet2 = player2.currentWallet;

		assertEquals(1000, bet2);
		assertEquals(0, wallet2);

		// Player player3 = new Player();

		// player3.moveMoneyToBet(1001);
	}

	@Test
	public void testDealToPlayer() {
		Dealer dealer = new Dealer();
		Player player = new Player();

		dealer.dealCard(player);

		// first card in deck is two of clubs
		// after it is dealt to player, it will be placed in hand
		// and set to visible
		assertEquals("TWOCLUB", player.getHand().get(0).toString());
		assertEquals(true, player.getHand().get(0).isVisible());
	}

	@Test
	public void testBet() {
		Player player = new Player();

		int bet = player.makeBet();

		assertEquals(10, bet);

		player.moveMoneyToBet(100);

		bet = player.getCurrentBet();

		assertEquals(100, bet);

		bet = player.doubleDownBet();

		assertEquals(200, bet);
	}

	@Test
	public void testNextHand() {
		Player player = new Player();

		player.nextHand();

		assertEquals(null, player.currentBet);
		assertEquals(null, player.currentWallet);
		assertEquals(null, player.currentInsurance);
	}

	@Test
	public void testMakeInsuranceBet() {
		Player player = new Player();

		player.moveMoneyToInsurance(100);

		int insurance = player.makeInsuranceBet();

		assertEquals(100, insurance);
	}

	@Test
	public void testGetAction() {
		Player player = new Player();

		PlayerAction.ActionType action = player.getAction();

		assertEquals(PlayerAction.ActionType.STAND, action);
	}

	@Test
	public void testAcceptMoney() {
		Player player = new Player();

		player.acceptMoney(1000);

		int wallet = player.currentWallet;

		assertEquals(2000, wallet);
	}

	@Test
	public void testToString() {
		Player player = new Player();

		player.moveMoneyToBet(200);
		player.moveMoneyToInsurance(200);

		Dealer dealer = new Dealer();

		dealer.dealCard(player);

		String playerString = "Player: [TWOCLUB]w 600b 200i 200";

		assertEquals(playerString, player.toString());
	}
}

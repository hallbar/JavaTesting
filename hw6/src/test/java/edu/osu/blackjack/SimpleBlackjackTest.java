package edu.osu.blackjack;


import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Random;
import static org.mockito.Mockito.*;

public class SimpleBlackjackTest {
   /* @Test
    public void testBasicFunctionality(){
	// Does it run without errors for a simple case?
	Dealer dealer = new Dealer();
	Player[] players  = new Player[1];
	players[0] = new Player(){
		@Override
		public int makeBet(){
		    this.currentBet = 1 + ((int)Math.random()*5);
		    return currentBet;
		}
	    };
	SimpleBlackjack j = new SimpleBlackjack(dealer,players);
	j.playRound();

    }*/

    @Test
    public void testDoubleDown() {
    	DealerAction dt = mock(DealerAction.class);
		PlayerAction pa = mock(PlayerAction.class);

		//  This player is feeling charitiable....
		when(pa.getAction()).thenReturn(PlayerAction.ActionType.DOUBLE);

		SimpleBlackjack j = new SimpleBlackjack(dt,new PlayerAction[]{pa});
		j.playRound();

		// double down causes a special betting action
		verify(pa, times(1)).doubleDownBet();
		verify(dt, times(3)).dealCard(pa);
    }

    @Test
    public void testInitialDealDealerSinglePlayer() {
    	DealerAction dt = mock(DealerAction.class);
    	PlayerAction p1 = mock(PlayerAction.class);

    	// player 1 is dealt initial hand then stands
    	when(p1.getAction()).thenReturn(PlayerAction.ActionType.STAND);

    	SimpleBlackjack j1 = new SimpleBlackjack(dt, new PlayerAction[]{p1});
    	j1.playRound();

    	verify(dt, times(2)).dealCard(dt);
    }

    @Test
    public void testInitialDealDealerMultiplePlayers() {
    	DealerAction dt = mock(DealerAction.class);
    	PlayerAction p1 = mock(PlayerAction.class);
    	PlayerAction p2 = mock(PlayerAction.class);
    	PlayerAction p3 = mock(PlayerAction.class);

    	// player 1, 2 and 3 are dealt initial hand then stand
    	when(p1.getAction()).thenReturn(PlayerAction.ActionType.STAND);
    	when(p2.getAction()).thenReturn(PlayerAction.ActionType.STAND);
    	when(p3.getAction()).thenReturn(PlayerAction.ActionType.STAND);

    	SimpleBlackjack j3 = new SimpleBlackjack(dt, new PlayerAction[]{p1, p2, p3});
    	j3.playRound();

    	verify(dt, times(2)).dealCard(dt);
    }

    @Test
    public void testInitialDealMultiplePlayers() {
    	DealerAction dt = mock(DealerAction.class);
    	PlayerAction p1 = mock(PlayerAction.class);
    	PlayerAction p2 = mock(PlayerAction.class);
    	
    	// player 1 and 2 are dealt initial hand then stand
    	when(p1.getAction()).thenReturn(PlayerAction.ActionType.STAND);
    	when(p2.getAction()).thenReturn(PlayerAction.ActionType.STAND);
    	
    	SimpleBlackjack j2 = new SimpleBlackjack(dt, new PlayerAction[]{p1, p2});
    	j2.playRound();

    	verify(dt, times(2)).dealCard(p1);
    	verify(dt, times(2)).dealCard(p2);
    }

    @Test
    public void testInitialDealSinglePlayer() {
    	DealerAction dt = mock(DealerAction.class);
    	PlayerAction p1 = mock(PlayerAction.class);
    	
    	// player 1 is dealt initial hand then stands
    	when(p1.getAction()).thenReturn(PlayerAction.ActionType.STAND);

    	SimpleBlackjack j1 = new SimpleBlackjack(dt, new PlayerAction[]{p1});
    	j1.playRound();

    	verify(dt, times(2)).dealCard(p1);
    }
	
	@Test
	public void testMakeBet() {
		DealerAction dt = mock(DealerAction.class);
		PlayerAction p1 = mock(PlayerAction.class);
		PlayerAction p2 = mock(PlayerAction.class);

		when(p1.getAction()).thenReturn(PlayerAction.ActionType.HIT)
						    .thenReturn(PlayerAction.ActionType.STAND);
		when(p2.getAction()).thenReturn(PlayerAction.ActionType.STAND);
		when(p1.makeBet()).thenReturn(10);
		when(p2.makeBet()).thenReturn(10);

		SimpleBlackjack j = new SimpleBlackjack(dt, new PlayerAction[]{p1, p2});
		j.playRound();

		verify(p1, times(1)).makeBet();
		verify(p2, times(1)).makeBet();
	}

	@Test
	public void testInsurance() {
		DealerAction dTrue = mock(DealerAction.class);
		DealerAction dFalse = mock(DealerAction.class);
		PlayerAction p1 = mock(PlayerAction.class);
		PlayerAction p2 = mock(PlayerAction.class);
		PlayerAction p3 = mock(PlayerAction.class);
		PlayerAction p4 = mock(PlayerAction.class);

		when(dTrue.isInsuranceAvailable()).thenReturn(true);
		when(dFalse.isInsuranceAvailable()).thenReturn(false);
		when(p1.makeInsuranceBet()).thenReturn(10);
		when(p2.makeInsuranceBet()).thenReturn(10);
		// when(p3.makeInsuranceBet()).thenReturn(10);
		when(p1.getAction()).thenReturn(PlayerAction.ActionType.STAND);
    	when(p2.getAction()).thenReturn(PlayerAction.ActionType.STAND);
    	when(p3.getAction()).thenReturn(PlayerAction.ActionType.STAND);
    	when(p4.getAction()).thenReturn(PlayerAction.ActionType.STAND);

		SimpleBlackjack jTrue = new SimpleBlackjack(dTrue, new PlayerAction[]{p1, p2});
		jTrue.playRound();

		verify(dTrue, times(1)).isInsuranceAvailable();
		verify(p1, times(1)).makeInsuranceBet();
		verify(p1, times(1)).makeInsuranceBet();

		SimpleBlackjack jFalse = new SimpleBlackjack(dFalse, new PlayerAction[]{p3, p4});
		jFalse.playRound();

		verify(dFalse, times(1)).isInsuranceAvailable();
		verify(p3, times(0)).makeInsuranceBet();
		verify(p4, times(0)).makeInsuranceBet();
	}

	@Test
	public void testCompareHandAndSettle() {
		DealerAction dt = mock(DealerAction.class);
		PlayerAction p1 = mock(PlayerAction.class);
		PlayerAction p2 = mock(PlayerAction.class);

		when(p1.getAction()).thenReturn(PlayerAction.ActionType.HIT)
							.thenReturn(PlayerAction.ActionType.STAND);
		when(p2.getAction()).thenReturn(PlayerAction.ActionType.STAND);

		SimpleBlackjack j = new SimpleBlackjack(dt, new PlayerAction[]{p1, p2});
		j.playRound();

		verify(dt, times(1)).compareHandAndSettle(p1);
		verify(dt, times(1)).compareHandAndSettle(p2);
	}
	
	@Test
	public void testShuffle() {
		DealerAction dt = mock(DealerAction.class);
		PlayerAction p1 = mock(PlayerAction.class);

		when(p1.getAction()).thenReturn(PlayerAction.ActionType.STAND);

		SimpleBlackjack j = new SimpleBlackjack(dt, new PlayerAction[]{p1});
		j.playRound();

		verify(dt, times(1)).shuffleDeck();
	}

	@Test
	public void testNumberOfActions() {
		DealerAction dt = mock(DealerAction.class);
		PlayerAction p1 = mock(PlayerAction.class);
		PlayerAction p2 = mock(PlayerAction.class);
		PlayerAction p3 = mock(PlayerAction.class);
		PlayerAction p4 = mock(PlayerAction.class);

		when(p1.getAction()).thenReturn(PlayerAction.ActionType.HIT)
							.thenReturn(PlayerAction.ActionType.DOUBLE);
		when(p2.getAction()).thenReturn(PlayerAction.ActionType.HIT)
							.thenReturn(PlayerAction.ActionType.HIT)
							.thenReturn(PlayerAction.ActionType.HIT)
							.thenReturn(PlayerAction.ActionType.STAND);
		when(p3.getAction()).thenReturn(PlayerAction.ActionType.STAND);
		when(p4.getAction()).thenReturn(PlayerAction.ActionType.DOUBLE);

		SimpleBlackjack j = new SimpleBlackjack(dt, new PlayerAction[]{p1, p2, p3, p4});
		j.playRound();

		verify(p1, times(2)).getAction();
		verify(p2, times(4)).getAction();
		verify(p3, times(1)).getAction();
		verify(p4, times(1)).getAction();
	}

	// there is a bug with the order that the cards are dealt but I can not
	// get InOrder to work.  mvn says the symbol is not found and 
	// http://site.mockito.org/mockito/docs/current/org/mockito/InOrder.html
	// is of no help, so I can not prove that the bug exists via testing

	// @Test
	// public void testDealOrder() {
	// 	DealerAction dt = mock(DealerAction.class);
	// 	PlayerAction p1 = mock(PlayerAction.class);

	// 	when(p1.getAction()).thenReturn(PlayerAction.ActionType.STAND);

	// 	SimpleBlackjack j = new SimpleBlackjack(dt, new PlayerAction[]{p1});
	// 	j.playRound();

	// 	assertEquals("dt", order[0]);
	// 	assertEquals("p1", order[1]);

	// 	// InOrder inOrder = Mockito.inOrder(j);

	// 	// inOrder.verify(j).dealCard(dt);
	// 	// inOrder.verify(j).dealCard(p1);
	// }

	// @Test
	// public void testDealerActionsAvailable() {
	// 	DealerAction dt = mock(DealerAction.class);
	// 	PlayerAction p1 = mock(PlayerAction.class);
	// 	boolean found = false;

	// 	when(p1.getAction()).thenReturn(PlayerAction.ActionType.STAND);

	// 	SimpleBlackjack j = new SimpleBlackjack(dt, new PlayerAction[]{p1});
	// 	j.playRound();

	// 	for(int i = 0; i < j.players.length; i++) {
	// 		if(dt == j.players[i]) {
	// 			found = true;
	// 			break;
	// 		}
	// 	}
	// 	assertEquals(true, found);
	// }
}
package edu.osu.blackjack;


import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Random;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;

public class VerifiedTest {

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
    public void testDealerHandSize() {
    	Dealer dealer = new Dealer();
    	List<Card> dealerHand = new ArrayList<Card>();

    	dealerHand = dealer.getHand();

    	assertEquals(0, dealerHand.size());
    	
    	dealer.dealCard(dealer);
    	dealer.dealCard(dealer);
    }
    
}
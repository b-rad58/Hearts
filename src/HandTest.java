import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class HandTest {

	private Hand hand;
	private Card c1;
	private Card c2;
	private Card c3;
	private Card c4;
	private ArrayList<Card> cards;
	
	@Before
	public void executedBeforeEach() {
		c1 = new Card(Ranks.THREE, Suits.CLUBS);
		c2 = new Card(Ranks.QUEEN, Suits.SPADES);
		c3 = new Card(Ranks.NINE, Suits.CLUBS);
		c4 = new Card(Ranks.TWO, Suits.CLUBS);
		cards = new ArrayList<Card>(4);
		cards.add(c1);
		cards.add(c2);
		cards.add(c3);
		cards.add(c4);
		hand = new Hand(cards);
	}
	
	@Test
	public void testContainsSuits() {
		assertTrue("Assert hand contains CLUBS", hand.contains(Suits.CLUBS));
		assertTrue("Assert hand contains  SPADES", hand.contains(Suits.SPADES));
		assertFalse("Assert hand does not contain DIAMONDS", hand.contains(Suits.DIAMONDS));
		assertFalse("Assert hand does not contain HEARTS", hand.contains(Suits.HEARTS));		
	}

	@Test
	public void testOnlyContains() {
		assertFalse("Assert that hand does not only contain CLUBS", hand.containsOnly(Suits.CLUBS));
		hand.remove(1);
		assertTrue("Assert that hand only contains CLUBS", hand.containsOnly(Suits.CLUBS));
	}

}

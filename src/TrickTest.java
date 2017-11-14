import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


/**
 * The Class TrickTest performs unit testing for the Trick class.
 */
public class TrickTest {

	/** The trick. */
	private Trick trick;
	
	/** The first trick. */
	private Trick firstTrick;
	
	/** The hearts not broken. */
	private Trick heartsNotBroken;
	
	/** The hearts broken. */
	private Trick heartsBroken;
	
	/** The Queen spades. */
	private Card QueenSpades;
	
	/** The Eight hearts. */
	private Card EightHearts;
	
	/** The Four diamonds. */
	private Card FourDiamonds;
	
	/** The Two clubs. */
	private Card TwoClubs;
	
	/** The p 1. */
	private Player p1;
	
	/** The p 2. */
	private Player p2;
	
	/** The p 3. */
	private Player p3;
	
	/** The p 4. */
	private Player p4;
	
	/** The players. */
	private Player[] players;
	
	/**
	 * Executed before each.
	 */
	@Before
	public void executedBeforeEach() {
		QueenSpades = new Card(Ranks.QUEEN, Suits.SPADES);
		EightHearts = new Card(Ranks.EIGHT, Suits.HEARTS);
		FourDiamonds = new Card(Ranks.FOUR, Suits.DIAMONDS);
		TwoClubs = new Card(Ranks.TWO, Suits.CLUBS);
		
		p1 = new Player("p1");
		p2 = new Player("p2");
		p3 = new Player("p3");
		p4 = new Player("p4");
		players = new Player[] {p1, p2, p3, p4};
		
		p1.pickUpCard(QueenSpades);
		p2.pickUpCard(EightHearts);
		p3.pickUpCard(FourDiamonds);
		p4.pickUpCard(TwoClubs);
		
	}


	/**
	 * Test calculate points.
	 */
	@Test
	public void testCalculatePoints() {
		trick = new Trick(players, 0, true, true);
		assertEquals("One point for hearts", 1, Trick.calculatePoints(EightHearts));
		assertEquals("Thirteen points for the queen of Spades", 13, Trick.calculatePoints(QueenSpades));
		assertEquals("No points for all cards which aren't hearts or the queen of spades", 0, Trick.calculatePoints(FourDiamonds));
	}

	/**
	 * Test check highest.
	 */
	@Test
	public void testCheckHighest() {
		trick = new Trick(players, 0, true, true);
		if (trick.isHigherValue(QueenSpades))
			trick.setHighestCard(QueenSpades, 0);
		if (trick.isHigherValue(EightHearts))
			trick.setHighestCard(EightHearts, 1);
		if (trick.isHigherValue(FourDiamonds))
			trick.setHighestCard(FourDiamonds, 2);
		if (trick.isHigherValue(TwoClubs))
			trick.setHighestCard(TwoClubs, 3);
		assertEquals("Test checkHighest sets correct highest card", QueenSpades, trick.getHighestCard());
		
		// another one, all same suit
		trick = new Trick(players, 0, true, true); 
		if (trick.isHigherValue(TwoClubs))
			trick.setHighestCard(TwoClubs, 0);
		if (trick.isHigherValue(new Card(Ranks.FOUR, Suits.CLUBS)))
			trick.setHighestCard((new Card(Ranks.FOUR, Suits.CLUBS)), 1);
		if (trick.isHigherValue(new Card(Ranks.THREE, Suits.CLUBS)))
			trick.setHighestCard((new Card(Ranks.THREE, Suits.CLUBS)), 2);
		if (trick.isHigherValue(new Card(Ranks.EIGHT, Suits.HEARTS)))
			trick.setHighestCard(new Card(Ranks.EIGHT, Suits.HEARTS), 3);
		System.out.println(trick.getHighestCard());
		assertEquals("Test checkHighest sets correct highest card", new Card(Ranks.FOUR, Suits.CLUBS), trick.getHighestCard());
	}

	/**
	 * Test valid card first trick.
	 */
	@Test
	public void testValidCardFirstTrick() {
		p1.pickUpCard(new Card(Ranks.NINE, Suits.SPADES));
		p2.pickUpCard(new Card(Ranks.JACK, Suits.DIAMONDS));
		
		firstTrick = new Trick(players, 0, false, false);
		assertFalse("First card of first trick must be Two of Clubs", firstTrick.isValid(QueenSpades, p4)); // First card must be 2 of Clubs
		assertTrue("Accept Two of Clubs as first card of first trick", firstTrick.isValid(TwoClubs, p4));   
		firstTrick.setHighestCard(TwoClubs, 3);
		
		assertTrue("No Clubs in hand on first trick, may play another suit", firstTrick.isValid(FourDiamonds ,p3)); // Not first card, if no clubs play another suit
		
		assertFalse("No Clubs in hand but no points on first trick", firstTrick.isValid(QueenSpades, p1)); // Not first card, no clubs but can't play points card;
		
		p1.playCard(new Card(Ranks.NINE, Suits.SPADES));
		assertTrue("If dealt all points card on first trick, may play points card", firstTrick.isValid(QueenSpades, p1)); //Dealt only points cards
		
		p3.pickUpCard(new Card(Ranks.QUEEN, Suits.CLUBS));	
		assertFalse("Must play Clubs if have Clubs in hand", firstTrick.isValid(FourDiamonds, p3));
		
		
		
	}
	
	/**
	 * Test valid card hearts not broken.
	 */
	@Test
	public void testValidCardHeartsNotBroken() {
		heartsNotBroken = new Trick(players, 0, true, false);
		assertFalse("Can't lead with Hearts if Hearts !broken and have other cards", heartsNotBroken.isValid(new Card(Ranks.THREE, Suits.HEARTS), p1));
		assertTrue("Hearts !broken but only have Hearts to play", heartsNotBroken.isValid(EightHearts, p2));
		
		assertTrue(heartsNotBroken.isHigherValue(FourDiamonds));
		
		p1.pickUpCard(new Card(Ranks.SIX, Suits.DIAMONDS));
		assertTrue("No trump suit so can play Hearts", heartsNotBroken.isValid(EightHearts,  p2));		
	}
	
	/**
	 * Test valid card hearts broken.
	 */
	@Test
	public void testValidCardHeartsBroken() {
		heartsBroken = new Trick(players, 0, true, true);
		p2.pickUpCard(new Card(Ranks.SIX, Suits.DIAMONDS));
		assertTrue("Verify can lead with Hearts", heartsBroken.isValid(EightHearts, p2));
		assertTrue(heartsBroken.isHigherValue(EightHearts));		
		heartsBroken.setHighestCard(EightHearts, 0);
		heartsBroken.setTrump(Suits.HEARTS);
		p1.pickUpCard(new Card(Ranks.JACK, Suits.HEARTS));
		assertFalse("Must play suit led, if in hand", heartsBroken.isValid(QueenSpades, p1));
	}


}

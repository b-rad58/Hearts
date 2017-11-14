/**
 * The enumerated type Suits represents the four suits of a playing cards deck
 */
public enum Suits implements Comparable<Suits> {
	CLUBS('C'), DIAMONDS('D'), SPADES('S'), HEARTS('H');
	
	/** The suit. */
	private final char suit;
	
	/**
	 * Instantiates a new suit.
	 *
	 * @param suit the suit
	 */
	private Suits(char suit) {
		this.suit = suit;
	}
	
	/**
	 * Gets the suit.
	 *
	 * @return the suit
	 */
	public char getSuit() {
		return suit;
	}
}

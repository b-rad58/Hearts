import java.util.Objects;

/**
 * The Class Card represents a playing card from a standard 52 card
 * deck.
 */
public class Card implements Comparable<Card> {
	
	/** The suit. */
	private Suits suit;
	
	/** The rank. */
	private Ranks rank;


	/**
	 * Instantiates a new card.
	 *
	 * @param rank the rank
	 * @param suit the suit
	 */
	public Card(Ranks rank, Suits suit) {
		this.suit  = suit;
		this.rank = rank; 
	}
		
	/**
	 * Gets the suit.
	 *
	 * @return the suit
	 */
	public Suits getSuit() {
		return suit;
	}

	/**
	 * Gets the rank.
	 *
	 * @return the rank
	 */
	public Ranks getRank() {
		return rank;
	}
	
	/** 
	 * Returns a String representing a Card. Uses Unicode values for the 
	 * Card
	 * @return String
	 * @see java.lang.Object#toString()
	 */
	public String toString() {            //      2               3               4               5               6               7               8               9               10              J               Q               K               A
		String[][] unicode = new String[][] { {"\uD83C\uDCA2", "\uD83C\uDCA3", "\uD83C\uDCA4", "\uD83C\uDCA5", "\uD83C\uDCA6", "\uD83C\uDCA7", "\uD83C\uDCA8", "\uD83C\uDCA9", "\uD83C\uDCAA", "\uD83C\uDCAB", "\uD83C\uDCAD", "\uD83C\uDCAE", "\uD83C\uDCA1"},   // Spades
											  {"\uD83C\uDCB2", "\uD83C\uDCB3", "\uD83C\uDCB4", "\uD83C\uDCB5", "\uD83C\uDCB6", "\uD83C\uDCB7", "\uD83C\uDCB8", "\uD83C\uDCB9", "\uD83C\uDCBA", "\uD83C\uDCBB", "\uD83C\uDCBD", "\uD83C\uDCBE", "\uD83C\uDCB1"},   // Hearts
											  {"\uD83C\uDCC2", "\uD83C\uDCC3", "\uD83C\uDCC4", "\uD83C\uDCC5", "\uD83C\uDCC6", "\uD83C\uDCC7", "\uD83C\uDCC8", "\uD83C\uDCC9", "\uD83C\uDCCA", "\uD83C\uDCCB", "\uD83C\uDCCD", "\uD83C\uDCCE", "\uD83C\uDCC1"},   // Diamonds
											  {"\uD83C\uDCD2", "\uD83C\uDCD3", "\uD83C\uDCD4", "\uD83C\uDCD5", "\uD83C\uDCD6", "\uD83C\uDCD7", "\uD83C\uDCD8", "\uD83C\uDCD9", "\uD83C\uDCDA", "\uD83C\uDCDB", "\uD83C\uDCDD", "\uD83C\uDCDE", "\uD83C\uDCD1"} }; // cLUBS
		int col;
		if (rank.getRank() == 1)
			col = 12;
		else
			col = rank.getRank() - 2;
		
		if (suit.equals(Suits.SPADES))
			return unicode[0][col];
		else if (suit.equals(Suits.HEARTS))
			return unicode[1][col];
		else if (suit.equals(Suits.DIAMONDS))
			return unicode[2][col];
		else // Clubs
			return unicode[3][col];			
	}
	
	/**
	 * Checks whether a given object is equal to this object
	 * @return boolean 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		if (this == o) 
			return true;
		if (o == null) 
			return false;
		if (this.getClass() != o.getClass())
			return false;
		Card that = (Card) o;
		return Objects.equals(this.suit, that.suit) && Objects.equals(this.rank, that.rank);
	}

	/** 
	 * Compares a Card to this Card
	 * @return int less than (<0), equal (0), greater than (1)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Card that) {
		if (this.suit.compareTo(that.suit) > 0)
			return 1;
		if (this.suit.compareTo(that.suit) < 0)
			return -1;
		if (this.rank.compareTo(that.rank) > 0)
			return 1;
		if (this.rank.compareTo(that.rank) < 0)
			return -1;
		
		return 0;
	}
	
}

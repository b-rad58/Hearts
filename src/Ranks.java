/**
 * The enumerated type Ranks represents the ranks of
 * playing cards. 
 * 
 * @author b-rad
 */
public enum Ranks implements Comparable<Ranks>{
	TWO (2), 
	THREE(3), 
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9), 
	TEN(10), 
	JACK(11),
	QUEEN(12),
	KING(13),
	ACE(14);
	
	 /** The rank */
	private final int rank;
	
	/**
	 * Instantiates a new rank.
	 *
	 * @param rank the rank
	 */
	private Ranks(int rank) {
		this.rank = rank;
	}
	
	/**
	 * Gets the rank
	 *
	 * @return the rank
	 */
	public int getRank() {
		return rank;
	}
		
}
	
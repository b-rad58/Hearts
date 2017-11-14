/**
 * The Class Player is a representation of a person playing a Card
 * game. A player has a name, a hand (containing their cards) 
 */
public class Player {
	
	/** The name. */
	private String name;
	
	/** The hand. */
	private Hand hand;
	
	/** The score. */
	private int score;
	
	/**
	 * Instantiates a new player with a name, an existing
	 * hand of cards and a score of zero.
	 *
	 * @param name the name
	 * @param hand the hand
	 */
	public Player(String name, Hand hand) {
		this.name = name;
		this.hand = hand;
		this.score = 0;
	}
	
	/**
	 * Instantiates a new player with a name, an empty hand
	 * 
	 *
	 * @param name the name
	 */
	public Player(String name) {
		this.name = name;
		hand = new Hand();
		score = 0;
	}
	
	/**
	 * Gets the name of the player.
	 *
	 * @return the name of the player
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name of the player
	 *
	 * @param name the new name of the player
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the hand of the player.
	 *
	 * @return the hand of the player
	 */
	public Hand getHand() {
		return hand;
	}
	
	/**
	 * Gets the card at the index position of the player's hand.
	 *
	 * @return the card at the index position of the player's hand.
	 * @see Hand#get(int)
	 */
	public Card getCard(int index) {
		return hand.get(index);
	}
	
	/**
	 * Plays the card at the given index of the player's hand,
	 * removing it from the player's hand.
	 *
	 * @param index the index of card in the player's hand
	 * @return the card at the given index
	 * @see java.util.List#remove(int)
	 */
	public Card playCard(int index) { 
		return hand.remove(index);
	}
	
	/**
	 * Plays the card matching a given card (if the
	 * given card is in the player's hand)
	 *
	 * @param c the card to be played
	 * @return true, if the card is in the players hand and therefore
	 * able to be played
	 * @see java.util.List#remove(Object)
	 */
	public boolean playCard(Card c) {
		return hand.remove(c);		
	}
	
		
	/**
	 * Pick up card and add it to the players hand
	 *
	 * @param c the card to pick up
	 */
	public void pickUpCard(Card c) {
		hand.addCard(c);
	}
	
	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Sets the score.
	 *
	 * @param newScore the new score
	 */
	public void setScore(int newScore) {
		score = newScore;
	}
	
	/**
	 * Adds the score.
	 *
	 * @param addScore the score to be added to the players
	 * current score
	 */
	public void addScore(int addScore) {
		score += addScore;
	}
	
	public String toString() {
		return name + System.lineSeparator() + hand + System.lineSeparator() + "Score: " + score;
	}
	
	/**
	 * Check if the player's hand contains a given card.
	 *
	 * @param card the card to see if in the hand
	 * @return true, if card is in hand
	 * @see java.util.List#contains(Object)
	 */
	public boolean contains(Card card) {
		return hand.contains(card);
	}
	
	/**
	 * Check if the player's hand contains a given suit.
	 *
	 * @param suit the suit to see if in the hand
	 * @return true, if successful
	 */
	public boolean handContains(Suits s) {
		return hand.contains(s);
	}
	
	/**
	 * Check if the player's hand contains only a given suit.
	 *
	 * @param suit the suit to see if only in the hand
	 * @return true, if 'suit' is the only suit in the hand
	 */
	public boolean handContainsOnly(Suits s) {
		return hand.containsOnly(s);		
	}
}

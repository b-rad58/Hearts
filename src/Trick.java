import java.util.AbstractMap.SimpleEntry;
import java.util.Scanner;

/**
 * The Class Trick.
 */
public class Trick {
	
	/** The leading suit of the trick. */
	private Suits trump;
	
	/** The highest card played in the trick, along with the 
	 *  index position of the player who played it. */
	private SimpleEntry<Card, Integer> highestCard = null;
	
	/** Scanner for players to choose the card to play. */
	private final Scanner in = new Scanner(System.in);
	
	/** The players playing the trick. */
	private Player[] players;
	
	/** The position of the player starting the trick. */
	private int startPos;
	
	/** The amount of points played in the trick. */
	private int points;
	
	/** Whether points are allowed in this trick. */
	private boolean pointsTrick;
	
	/** Whether or not hearts have been broken. */
	private boolean heartsBroken;
	
	/** Cards played this trick. */
	private Card[] cardsPlayed;
	
	
	/**
	 * Instantiates a new trick with the player playing, the position
	 * of the player starting play and whether points are allowed in the
	 * trick and if hearts have been broken or not.
	 *
	 * @param players the players playing
	 * @param startPos the position of the player starting the trick
	 * @param pointsTrick whether points are allowed this trick
	 * @param heartsBroken whether hearts have been broken yet
	 */
	public Trick(Player[] players, int startPos, boolean pointsTrick, boolean heartsBroken) { //takes pairs <'card played', 'player that played the card;>
		this.players = players;
		this.startPos = startPos;
		this.pointsTrick = pointsTrick;
		this.heartsBroken = heartsBroken;
		cardsPlayed = new Card[4];
	}	
		
	/**
	 * Goes through the actions of each player playing a card for the trick. 
	 * Makes sure that each card is valid to be played. Keeps track of points 
	 * and who played the highest card.
	 */
	public void playTrick() {
		Card currentCard;
		for (int foo = 0; foo<players.length; foo++) {                                // Go through each player (starting with the startPos)
			int i = nextPlayer(foo);                                                  // Get the next player
			Player currentPlayer = players[i];
			while(true) {                                                             // Player continues to choose a card until they choose a valid card
				System.out.println(currentPlayer.getName() + " play a card");
				System.out.println(currentPlayer.getHand());
				currentCard = currentPlayer.getCard(in.nextInt());
				if (isValid(currentCard, currentPlayer)) {
					currentPlayer.playCard(currentCard);                              // Remove chosen card from player's hand (if valid)
					break;
				}
				else {
					System.out.println("Invalid card");
					continue;
				}				
			}
			if (highestCard == null) {                                                // Maintain record of the highest card played (and who played it)
				trump = currentCard.getSuit();	
				highestCard = new SimpleEntry<Card, Integer>(currentCard, i);
			}
			else if (isHigherValue(currentCard)) 
					highestCard = new SimpleEntry<Card, Integer>(currentCard, i);
			points += calculatePoints(currentCard);                                   // Keep track of how many points have been played during the trick
			cardsPlayed[i] = currentCard;
			System.out.println(this);
		}
	}
	
		
		
	/**
	 * Gets the trump (the leading suit)
	 *
	 * @return the trump (leading suit)
	 */
	public Suits getTrump() {
		return trump;
	}
	
	/**
	 * Sets the trump.
	 *
	 * @param trump the new trump
	 */
	public void setTrump(Suits trump) {
		this.trump = trump;
	}	
	
	/**
	 * Gets the highest card.
	 *
	 * @return the highest card
	 */
	public Card getHighestCard() {
		return highestCard.getKey();
	}
	
	/**
	 * Sets the highest card.
	 *
	 * @param highest the new highest card
	 */
	public void setHighestCard(Card card, int player) {
		highestCard = new SimpleEntry<Card, Integer>(card, player);
		trump = card.getSuit();
	}
	/**
	 * Gets the index of the winning player.
	 *
	 * @return the index of the winning player
	 */
	public int getWinner() {
		return highestCard.getValue();
	}
	
	/**
	 * Calculate the amount of points a given card is worth
	 *
	 * @param card the card whose points value is to be calculated
	 * @return the int amount of points the card is worth
	 */
	public static int calculatePoints(Card card) { // static?
		if (card.equals(new Card(Ranks.QUEEN, Suits.SPADES)))
			return 13;
		else if (card.getSuit().equals(Suits.HEARTS))
			return 1;
		return 0;
	}
		
	/**
	 * Gets the points.
	 *
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}
	
	/**
	 * Checks if a chosen card is valid (conforms to the rules of the game)
	 *
	 * @param card the card attempting to be played
	 * @param player the player attempting to play the card
	 * @return true, if the card is a valid play
	 */
	public boolean isValid(Card card, Player player) {
		if (!pointsTrick) {                                                                          // First trick of a round
			if (highestCard == null) {                                                               // First card must be Two of Clubs
				if (!card.equals(new Card(Ranks.TWO, Suits.CLUBS))) {
					System.out.println("First card played must be Two of Clubs");
					return false;
				}
			}
			else if (!card.getSuit().equals(Suits.CLUBS)) {                                          // Must play a Club (if player has one)
				if (player.handContains(Suits.CLUBS)) {
					System.out.println("Must play Clubs");
					return false;
				}
				else if (calculatePoints(card) > 0) {
					Card queenOfSpades = new Card(Ranks.QUEEN, Suits.SPADES);       				 // If no clubs, player may play a points cards iff
					if (player.playCard(queenOfSpades)) { 						                     // their hand contains only (all) the points cards 
						if (!player.handContainsOnly(Suits.HEARTS)) {
							player.pickUpCard(queenOfSpades);
							System.out.println("No points on the first trick");
							return false;
						}
						else 
							player.pickUpCard(queenOfSpades);
					}
					else {
						System.out.println("No points on the first trick");
						return false;
					}
				}
			}
		}
		else if (!heartsBroken) {                                                                   // Points allowed on trick but hearts not broken
			if (highestCard == null) {
				if (card.getSuit().equals(Suits.HEARTS)) {                                          // Can't lead with Hearts (unless hand contains only Hearts)
					if (!player.handContainsOnly(Suits.HEARTS)) {
						System.out.println("Hearts not yet broken");
						return false;
					}
					else {
						heartsBroken = true;                                                        // If a Heart is played, Hearts are now broken
						System.out.println("Hearts broken");
					}	
				}
			}
			else if (!card.getSuit().equals(trump)){                                                // Points trick but hearts are not yet broken
				if (player.handContains(trump)) {                                                   // Must played the suit lead (trump) if player has it in their hand
					System.out.println("Must play " + trump);
					return false;
				}
				else if (card.getSuit().equals(Suits.HEARTS)) {
					heartsBroken = true;
					System.out.println("Hearts broken");
				}			
			}
		}
		else {                                                                                      // Points trick and Hearts are broken
			if (!(highestCard == null) && !card.getSuit().equals(trump)) {                          //  Must played the suit lead (trump) if player has it in their hand
				if (player.handContains(trump)) {
					System.out.println("Must play suit led");
					return false;
				}
			}
			
		}
		return true;                                                                                // Passed all tests, card is a valid play
	}
	
	/**
	 * Checks if is higher value.
	 *
	 * @param newCard the new card
	 * @return true, if card is a higher value
	 */
	public boolean isHigherValue(Card newCard) {
		if (highestCard == null)
			return true;
		if (newCard.getSuit().equals(trump)) {
			if (newCard.getRank().compareTo(highestCard.getKey().getRank()) > 0) 
				return true;
		}
		return false;
	}
	
	/**
	 * Find which player is to play next
	 *
	 * @param x the for-loop integer
	 * @return the int index position of the next player to play 
	 */
	public int nextPlayer(int x) {
		return (x + startPos) % players.length;
	}
	
	public boolean isHeartsBroken() {
		return heartsBroken;
	}
		
	
	// To implement
	public String toString() {
		String str = "";
		if (!(cardsPlayed[2] == null))
			str += "   " + cardsPlayed[2];
		str += System.lineSeparator();
		if (!(cardsPlayed[1] == null))
			str += cardsPlayed[1];
		else 
			str += "   ";
		if (!(cardsPlayed[3] == null))
			str += "   " + cardsPlayed[3];
		str += System.lineSeparator();
		if (!(cardsPlayed[0] == null))
			str += "   " + cardsPlayed[0];
		return str;
		
	}

}

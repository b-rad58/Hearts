import java.util.*;

/**
 * The Class Deck represents a standard playing card deck of 
 * 52 cards.
 */
public class Deck {
	
	/** The deck. */
	private Stack<Card> deck;	
	
	/**
	 * Instantiates a new deck with the standard 52 cards
	 */
	public Deck() {
		this.deck = new Stack<Card>();
		for (Suits s: Suits.values()) {
			for (Ranks r: Ranks.values()) {
				Card card = new Card(r,s);
				deck.push(card);
			}
		}
	}
	
	/**
	 * Gets the deck.
	 *
	 * @return the deck
	 */
	public Stack<Card> getDeck() {
		return deck;
	}
	
	/**
	 * Prints each card in the deck in rows of 13
	 */
	public void printDeck() {
		Stack<Card> tempDeck = new Stack<Card>();
		tempDeck.addAll(deck);
		while(tempDeck.size() > 13) {
			for (int i = 0; i < 13; i++)
				System.out.print(tempDeck.pop());
			System.out.println();
		}
		while (tempDeck.size() > 0)
			System.out.print(tempDeck.pop());
		System.out.println();
		
	}
	
	/**
	 * Shuffles the deck
	 */
	public void shuffle() {
		Collections.shuffle(deck);
	}
	
    /**
     * Cut deck, returning a card
     *
     * @param min the minimum numbers of cards to be cut off the top of the deck
     * @param max the maximum number of cards to be cut off the top of the deck
     * @return the card returned from the cut
     */
    public Card cutDeck(int min, int max) {
    	if (min < 0)
    		throw new IllegalArgumentException("min value must be greater than 0");
    	if (max >= deck.size()-1)
    		throw new IllegalArgumentException("max value must be less than the amount of cards in the deck");
    	if (min > max)
    		throw new IllegalArgumentException("min must be less than or equal to max");
    	Random rand = new Random();
    	int n = rand.nextInt(max) + 1;
    	Stack<Card> tempStack = new Stack<Card>();
    	tempStack.addAll(deck);
    	for (int i = 0; i < n; i++)
    		tempStack.pop();
    	return tempStack.pop();
    }
	
	/**
	 * Deals the card on top of the deck, removing
	 * it from the deck
	 *
	 * @return the card from the top of the deck
	 */
	public Card dealCard() {
		if (deck.size() > 0) {
			Card c = deck.pop();
			return c;
		}
		else throw new IllegalStateException("No cards left in deck");		
	}
	
	
	
	/**
	 * Deals cards, one at a time, to each player
	 *
	 * @param players the players receiving the cards
	 * @param handSize the amount of cards being dealt to each player
	 */
	public void dealCards(Player[] players, int handSize) {
		if (players.length*handSize <= deck.size()) {
			for (int i = 0; i < handSize; i++) {
				for (Player p: players)
					p.pickUpCard(dealCard());
			}
		}
		else throw new IllegalStateException("Not enough cards");
	}
	
		
	/**
	 * Checks if is empty.
	 *
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		return deck.isEmpty();
	}
	
	/**
	 * Size.
	 *
	 * @return the int
	 */
	public int size() {
		return deck.size();
	}
	
	
}

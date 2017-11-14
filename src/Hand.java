import java.util.*;

/**
 * The Class Hand represents a collection of cards held 
 * by a player.
 */
public class Hand {
	
	/** The hand. */
	private ArrayList<Card> hand;
	
	/**
	 * Instantiates a new hand with an existing collection of cards.
	 *
	 * @param cards the cards
	 */
	public Hand(ArrayList<Card> cards) {
		hand = new ArrayList<Card>();
		hand.addAll(cards);
	}
	
	/**
	 * Instantiates a new empty hand.
	 */
	public Hand() {
		hand = new ArrayList<Card>();
	}
	
	/**
	 * Adds a card to the hand.
	 *
	 * @param c the card to added
	 * @see java.util.List#add(Object)
	 */
	public void addCard(Card c) {
		hand.add(c);
		Collections.sort(hand);;
	}
	
	/**
	 * Removes the a card from the hand
	 *
	 * @param c the card to be removed
	 * @return true, if successful
	 * @see java.util.List#remove(Object)
	 */
	public boolean remove(Card c) { 
		return hand.remove(c);
		
	}
	
	/**
	 * Removes the card at index position 'index'
	 *
	 * @param index the index position of the card to be removed.
	 * @return the card that is removed
	 * @see java.util.List#remove(int)
	 */
	public Card remove(int index) {
		return hand.remove(index); 
	}
	
	/**
	 * Discard all cards in the hand
	 * @see java.util.List#clear()
	 */
	public void discardAll() {
		hand.clear();
	}
	
	/**
	 * Gets the card at index position 'index' 
	 *
	 * @param index the index position of the card
	 * @return the card at index position 'index'
	 * @see java.util.List#get(int)
	 */
	public Card get(int index) {
		return hand.get(index);
	}
	
	/**
	 * Sort the hand.
	 * @see java.util.Collections#sort(List)
	 */
	public void sort() {
		Collections.sort(hand);
	}
	
	/**
	 * Check if the hand contains a given card.
	 *
	 * @param card the card to see if in the hand
	 * @return true, if card is in hand
	 * @see java.util.List#contains(Object)
	 */
	public boolean contains(Card card) {
		return hand.contains(card);
	}
	
	/**
	 * Check if the hand contains a given suit.
	 *
	 * @param suit the suit to see if in the hand
	 * @return true, if successful
	 */
	public boolean contains(Suits suit) {
		for (Card c: hand) {
			if (c.getSuit().equals(suit))
				return true;
		}
		return false;
	}
	
	/**
	 * Check if the hand contains only a given suit.
	 *
	 * @param suit the suit to see if only in the hand
	 * @return true, if 'suit' is the only suit in the hand
	 */
	public boolean containsOnly(Suits suit) {
		for (Card c: hand) {
			if (!c.getSuit().equals(suit))
				return false;
		}
		return true;
	}
	
	/**
	 * Size of the hand
	 *
	 * @return An int representing the amount of cards in the hand
	 * @see java.uitl.List#size()
	 */
	public int size() {
		return hand.size();
	}
	
	/** 
	 * Returns a String representation of a hand.
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String str = "";
		for(Card c : hand)
			str += c;
		return str;
	}
}

import java.util.*;

public class Hand {
	private ArrayList<Card> hand;
	
	public Hand(ArrayList<Card> cards) {
		hand = cards;
	}
	
	public Hand() {
		hand = new ArrayList<Card>();
	}
	
	public void addCard(Card c) {
		hand.add(c); // some sort of error check?
	}
	
	public void remove(Card c) { //void or return Card?
		if (hand.contains(c)) {
			hand.remove(c);  
		}
		else {}//error
		
	}
	
	public Card remove(int index) {
		return hand.remove(index); //error in range
	}
	
	public void discardAll() {
		hand.clear();
	}
	
	public Card get(int index) {
		return this.hand.get(index);
	}
	
	public void sortByRank() {
		 /**
	     * Sorts the cards in the hand so that cards of the same 
	     * suit are grouped together, and within a suit the cards 
	     * are sorted by value.
	     */
	   
	   
	}
	public boolean contains(Card c) {
		return hand.contains(c);
	}
	public void sortBySuit() {
		 /**
	     * Sorts the cards in the hand so that cards are sorted into
	     * order of increasing value.  Cards with the same value 
	     * are sorted by suit. Note that aces are considered
	     * to have the lowest value.
	     */
		// pick a sorting method and an order of suits. Overwrite in Hearts for C-D-S-H
	}
	
	public int getCardCount() {
		return hand.size();
	}
	
	public String toString() {
		String str = "";
		for(Card c : this.hand) {
			str = str + ", [" + c + "]";
			// add comma at end, after loop strip last char
		}
		return str;
	}
	//get/remove card by positions
}

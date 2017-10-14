import java.util.*;

// why would a deck of cards not be a stack, boiii?  more difficult to cut deck.. still worth it, deal card = pop()
//no duplicates
public class Deck {
	private ArrayList<Card> deck;
	
	
	public Deck() {
		this.deck = new ArrayList<Card>(52);
		for (Suits s: Suits.values()) {
			for (Ranks r: Ranks.values()) {
				Card card = new Card(r,s);
				deck.add(card);
			}
		}
		
		/*Card card = new Card
		this.cards[i] = card;
		*/
	}
	public ArrayList<Card> getDeck() {
		return this.deck;
	}
	
	public void printDeck() {
		for(Card c : this.deck) {
			System.out.println(c);
		}
	}
	
	public void shuffle() {
		Collections.shuffle(this.deck);
	}
	
   // public '' cutDeck()
	/*public void shuffle()

	public int cardsLeft()

	public Card dealCard()
	*/
	
	
	public void dealCards(int players, int handSize) {//error check
		if (players*handSize <= 52) {
			Card[][] hands = new Card[players][handSize];
			for(int i=0; i<handSize; i++) {
				for(int j=0; j<players; j++) {
					hands[j][i] = dealCard();
				}
			}
		}
		else throw new IllegalStateException("Not enough cards");
	}
	
	public Card dealCard() {
		//deal one card
		//@throws IllegalStateException if no more cards are left.
		if (this.deck.size() >0) {
			Card c = deck.get(0);
			deck.remove(0);
			return c;
		}
		else throw new IllegalStateException("No cards left");
		
	}
	
	public boolean isEmpty() {
		return deck.isEmpty();
	}
	
	public static void main(String[] args) {
		Deck test = new Deck();
		test.shuffle();
		//test.printDeck();
		System.out.println(test.dealCard());
	}
}
//https://stackoverflow.com/questions/15942050/deck-of-cards-java

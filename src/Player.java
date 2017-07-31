import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;

public class Player {
	private String name;
	private Hand hand;
	private ArrayList<Card> cardsTaken;
	private int score;
	
	public Player(String name, Hand hand) {
		this.name = name;
		this.hand = hand;
		this.cardsTaken = new ArrayList<Card>();
		this.score = 0;
	}
	
	public Player(String name) {
		this.name = name;
		hand = new Hand();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Hand getHand() {
		return this.hand;
	}
	
	public Card playCard(int index) { 
		Card c = this.hand.get(index);
		this.hand.remove(index);
		return c;
	}
	
	public void playCard(Card c) { //error check 
		this.hand.remove(c);		
	}
	
	public ArrayList<Card> takeTrick(Trick trick) {
		this.cardsTaken.addAll(trick.getTrickCards());
		return trick.getTrickCards();
		
	}
	
	public void pickUpCard(Card c) {
		hand.addCard(c);
	}
}

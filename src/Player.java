import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;

public class Player {
	private String name;
	private Hand hand;
	//private ArrayList<Card> cardsTaken; dont need it
	private int score; //getter/setter score
	
	public Player(String name, Hand hand) {
		this.name = name;
		this.hand = hand;
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
	
	public void takeTrick(Trick trick) {
		score += trick.getPoints();		
	}
	
	public void pickUpCard(Card c) {
		hand.addCard(c);
	}
}

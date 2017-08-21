//create a play trick method

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Scanner;

//anything better than simple entry? Java does not have tuples, work around good enough for now
public class Trick {
	private Suits trump;
	private SimpleEntry<Card, Player> highestCard = null;
	private ArrayList<Card> trickCards; //currently not added, do i need this?
	private Scanner in;
	private Player trickWinner;
	private Player[] players;
	
	//logically does it make sense for the constructor to contain this much logic?
	public Trick(Player[] players, int startPos) { //takes pairs <'card played', 'player that played the card;>
		this.players = players;
		trickCards = new ArrayList<Card>();
		in = new Scanner(System.in);
		
		Player currentPlayer; //
		int i;
		for (int foo=0; foo<players.length; foo++) {
			i = (foo + startPos) % players.length;
			currentPlayer = players[i];
			System.out.println(currentPlayer.getName() + "play a card: ");
			System.out.println(currentPlayer.getHand());
			Card currentCard = currentPlayer.playCard(in.nextInt());
			if (highestCard == null) {
				this.trump = currentCard.getCardSuit();			//add player position so we know where to start next trick
				this.highestCard = new SimpleEntry<Card, Player>(currentCard, currentPlayer);
				
			}
			else { //check equals method for enumerated types
				if (currentCard.getCardSuit().equals(trump)) {
					if(currentCard.getCardValue().getRank() > highestCard.getKey().getCardValue().getRank()) {
						highestCard = new SimpleEntry<Card, Player>(currentCard, currentPlayer);
					}
					
				}
			}
		
		}
		this.trickWinner = highestCard.getValue();
		System.out.println(highestCard);
		//end of card playing
		
		
	
		
	} // created a trick and the winner but Constructor cannot return the winner, maybe 
	  // not need t
	//this.getWinner()\\\\\\\\\
	
	public Player getWinner() {
		return trickWinner;
	}
	
	public Trick(int numPlayers, Suits trump) {
		this.trump = trump;
	}
	
	public Suits getTrump() {
		return trump;
	}
	
	public void setTrump(Suits trump) {
		this.trump = trump;
	}
	
	public SimpleEntry<Card, Player> getHighestCard() {
		return highestCard;
	}
	
	public ArrayList<Card> getTrickCards() {
		return trickCards;
	}
	
	public int twoOfClubs() {
		Card twoClubs = new Card(Ranks.TWO, Suits.CLUBS);
		for (int i=0; i<4; i++) {
			if (this.players[i].getHand().contains(twoClubs))
				return i;
		}
		return -1; //error
	}

}

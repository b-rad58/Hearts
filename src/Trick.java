//create a play trick method

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Scanner;

//anything better than simple entry? Java does not have tuples, work around good enough for now
public class Trick {
	private Suits trump;
	private SimpleEntry<Card, Player> highestCard = null; // static class in here
	private ArrayList<Card> trickCards; //currently not added, do i need this?
	private final Scanner in = new Scanner(System.in);
	private int winnerPos;
	private Player[] players;
	private int startPos;
	private int points;
	
	
	//Trick takes card
	
	//public Trick(players) //Stack? Queue?  
	
	//logically does it make sense for the constructor to contain this much logic? NO IT DOES NOT
	public Trick(Player[] players, int startPos) { //takes pairs <'card played', 'player that played the card;>
		this.players = players;
		this.startPos = startPos;
		this.winnerPos = startPos;
		trickCards = new ArrayList<Card>();
		
		
	}	
		
	public void play() {
		Player currentPlayer;
		int i;		
		for (int foo=0; foo<players.length; foo++) { // Goes through turns
			i = (foo + startPos) % players.length; //works from starting pos and goes through all players, in order
			currentPlayer = players[i];
			winnerPos = i; //reference for next Trick
			System.out.println(currentPlayer.getName() + "play a card: ");
			System.out.println(currentPlayer.getHand());
			Card currentCard = currentPlayer.playCard(in.nextInt()); //play card by int position hand
			if (currentCard.getCardSuit().equals(Suits.HEARTS)) //add points for hearts
				points++;
			else if (currentCard.getCardSuit().equals(Suits.SPADES) && currentCard.getCardValue().equals(Ranks.QUEEN)) //check for the bish
				points += 13;
			if (highestCard == null) {
				this.trump = currentCard.getCardSuit();			//add player position so we know where to start next trick
				this.highestCard = new SimpleEntry<Card, Player>(currentCard, currentPlayer);
			}
			else { //check equals method for enumerated types
				
				if (currentCard.getCardSuit().equals(trump)) {
					if(currentCard.getCardValue().getRank() > highestCard.getKey().getCardValue().getRank()) {
						highestCard = new SimpleEntry<Card, Player>(currentCard, currentPlayer);
						winnerPos = i;
					}					
				}
			}		
		}
		System.out.println(highestCard);
		//end of card playing
		
		
	
		
	} // created a trick and the winner but Constructor cannot return the winner, maybe 
	  // not need t
	//this.getWinner()\\\\\\\\\
	
		
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
	
	public Player getTrickWinner() {
		return highestCard.getValue();
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
	
	public int getPoints() {
		return points;
	}
	
	public int getWinnerPos() {
		return winnerPos;
	}

}

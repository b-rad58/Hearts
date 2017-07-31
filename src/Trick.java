import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Scanner;

//anything better than simple entry? Java does not have tuples, work around good enough for now
public class Trick {
	private Suits trump;
	private SimpleEntry<Card, Player> highestCard;
	private ArrayList<Card> trickCards;
	private Scanner in;
	//doesnt work logically. Cards are played one at a time, allowing players to see the cards previously played in that trick (as well as the trump, duh)
	
	
	public Trick(Player[] players, int startPos) { //takes pairs <'card played', 'player that played the card;>
		trickCards = new ArrayList<Card>();
		in = new Scanner(System.in);
		
		Player currentPlayer; //
		int i;
		for (int foo=0; foo<players.length; foo++) {
			i = (foo + startPos) % players.length;
			currentPlayer = (Player) players[i];
			System.out.println(currentPlayer.getName() + "play a card: ");
			System.out.println(currentPlayer.getHand());
			Card currentCard = currentPlayer.playCard(in.nextInt());
			this.trump = currentCard.getCardSuit();
			this.highestCard = new SimpleEntry(currentCard, currentPlayer);
			
		
		}
		
		this.trump = cardsPlayed[0].getKey().getCardSuit();
		highestCard = new SimpleEntry<Card, Player>(cardsPlayed[0].getKey(),cardsPlayed[0].getValue());
		for(SimpleEntry<Card, Player> card : cardsPlayed) {
			trickCards.add(card.getKey());
			if (card.getKey().getCardSuit().equals(trump)) {//check equals method is valid for comparing suits
				if (card.getKey().getCardValue().getRank() > highestCard.getKey().getCardValue().getRank()) { //define 'greater than' for values
					highestCard = card;
				}
			}
		}//this code is super ugly. Abstract some shit away || just 1break up the lines to make it more readable
		
	} // created a trick and the winner but Constructor cannot return the winner, maybe 
	  // not need t

	
	
	
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

}

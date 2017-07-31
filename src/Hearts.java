import java.util.Scanner;
import java.util.AbstractMap.SimpleEntry;

public class Hearts {
	private Player[] players;
	private int highestScore;
	private static Deck deck;
	
	public int twoOfClubs() {
		Card twoClubs = new Card(Ranks.TWO, Suits.CLUBS);
		for (int i=0; i<4; i++) {
			if (this.players[i].getHand().contains(twoClubs))
				return i;
		}
		return -1; //error
	}
	
	public Hearts(Player[] players) {
		this.players = players;
		this.highestScore = 0;
		deck = new Deck();
	}
	
	public Deck getDeck() {
		return deck;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Player[] players = new Player[4]; // create and name the 4 players
		for(int i=0; i<4; i++) {
			System.out.println("Enter name of Player " + (i+1));
			players[i] = new Player(in.nextLine());
		}
		
		Hearts hearts = new Hearts(players);
		
		deck.shuffle();
		while(!deck.isEmpty()) { // deal out all cards (13 each)
			for (Player p : players) {
				p.pickUpCard(deck.dealCard());
			}
		}
		for (Player p : players) {
			System.out.println(p.getHand());
		}
		
		//Hearts.start(); figure out how you want to organize this later on+
		int startPos = hearts.twoOfClubs();
		int i;
		Player currentPlayer;
		for (int foo=0; foo<4; foo++) {
			i = (foo + startPos) % 4;
			currentPlayer = players[i];
			System.out.println(currentPlayer.getName() + "play a card: ");
			System.out.println(currentPlayer.getHand());
			currentPlayer.playCard(in.nextInt());		
		
			
		}
		in.close();
	}
}

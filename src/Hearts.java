import java.util.Scanner;

/**
 * The Class Hearts.
 */
public class Hearts {
	
	/** The players playing the game. */
	private Player[] players;
	
	/** The current highest score. */
	private int highestScore;
	
	/** The deck of cards to be used. */
	private static Deck deck;
	
	
	/**
	 * Instantiates a new game of Hearts with the players who are
	 * playing and a deck of shuffled cards.
	 *
	 * @param players the players playing the game
	 */
	public Hearts(Player[] players) {
		this.players = players;
		highestScore = 0;
		deck = new Deck();
		deck.shuffle();
	}
	
	/**
	 * Gets the deck.
	 *
	 * @return the deck
	 */
	public Deck getDeck() {
		return deck;
	}
	
	/**
	 * Adds up the scores of each player after a round.
	 *
	 * @param scores the round scores of the players
	 */
	public void addScores(int[] scores) {
		if (!(scores.length == players.length))
			throw new IllegalArgumentException("Scores array must same size as players");
		for (int i = 0; i < players.length; i++)
			players[i].addScore(scores[i]);
		highestScore();
	}
	
	/**
	 * Gets the winner of the game
	 *
	 * @return the winner of the game
	 */
	public Player getWinner() {
		if (!(highestScore >= 100))
			throw new IllegalStateException("Game not yet over");
		Player lowestScore = players[0];
		for (int i = 1; i < players.length; i++) {
			if (players[i].getScore() < lowestScore.getScore())
				lowestScore = players[i];
		}
		return lowestScore;
	}
	
	/**
	 * Plays through the game. Following each round, the deck is
	 * shuffled and 13 cards are dealt to each player. If, at the end
	 * of a round, a player's score is greater or equal to 100, then
	 * the game is over. At this point, the player with the lowest
	 * score is the winner.
	 */
	public void playHearts() {
		while (highestScore < 100) {
			deck.dealCards(players, 13);
			Round round = new Round(players);
			round.playRound();
			addScores(round.getScores());
			printScores();
			deck = new Deck();
			deck.shuffle();
		}
		Player winner = getWinner();
		System.out.println("Congratulations " + winner.getName() + " , you are the winner!");
	}
	
	/**
	 * Highest score.
	 */
	public void highestScore() {
		for (Player p : players) {
			if (p.getScore() > highestScore)
				highestScore = p.getScore();
		}
	}
	
	/**
	 * Prints the scores of each player.
	 */
	public void printScores() {
		// Print the list objects in tabular format.
	    System.out.println("---------------------------");
	    System.out.printf("%15s %6s", "PLAYER", "SCORE");
	    System.out.println();
	    System.out.println("---------------------------");
	    for(Player p: players){
	        System.out.format("%15s %6s", 
	                p.getName(), p.getScore());
	        System.out.println();
	    }
	    System.out.println("---------------------------");
	}
	
	/**
	 * The main method. Takes as input the name of each player
	 * and than plays the game.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Player[] players = new Player[4]; // create and name the 4 players
		for(int i=0; i<4; i++) {
			System.out.println("Enter name of Player " + (i+1));
			players[i] = new Player(in.nextLine());
		}
		
		Hearts hearts = new Hearts(players);
		hearts.playHearts();
		in.close();
	}
}

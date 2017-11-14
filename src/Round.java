/**
 * The Class Round plays a round of the card game Hearts. A round consists
 * of 13 tricks. Scores are added up at the end of the round.
 */
public class Round {
	
	/** The players playing the game. */
	private Player[] players;
	
	/** The round scores of each player. */
	private int[] roundScores;
	
	/** The amount of tricks left in the round. */
	private int tricksLeft;
	
	/** Whether or not hearts have been broken yet this round. */
	private boolean heartsBroken;
	
	/** Whether or not points are allowed in tricks (i.e not
	 *  the first trick. */
	private boolean pointsTrick;
	
	
	/**
	 * Instantiates a new round with a list of players. Round scores
	 * are initialized to zero, there are currently thirteen tricks remaining, 
	 * hearts are not broken and points cards are not allowed to be played.
	 *
	 * @param players the players to play the round
	 */
	public Round(Player[] players) {
		tricksLeft = 13;
		roundScores = new int[players.length];
		this.players = players;
		heartsBroken = false;
		pointsTrick = false;
	}
	
	/**
	 * Find the index position of the player holding the Two of Clubs.
	 *
	 * @return the int position of the player with the Two of Clubs
	 */
	public int twoOfClubsPosition() {
		Card twoOfClubs = new Card(Ranks.TWO, Suits.CLUBS);
		for (int i = 0; i < players.length; i++) {
			if (players[i].getHand().contains(twoOfClubs)) {
				return i;
			}
		}
		throw new IllegalStateException("No player has the Two of Clubs");
	}
	
	/**
	 * Play round of Hearts. Goes through the thirteen tricks, The first trick
	 * is led with the player who holds the Two of Clubs. Subsequent tricks are led
	 * by the winner of the previous trick. The points for each player are maintained.
	 * In the roundScores array. 
	 */
	public void playRound() { //goes through 13 tricks, counting points
		Trick trick;
		int prevTrickWinner = this.twoOfClubsPosition(); //need a value here for if/else to work
		while(tricksLeft > 0) {
			trick = new Trick(players, prevTrickWinner, pointsTrick, heartsBroken);		
			trick.playTrick();
			if (tricksLeft == 13)
				pointsTrick = true;
			if (!heartsBroken) {
				if (trick.isHeartsBroken())
					heartsBroken = true;
			}
			prevTrickWinner = trick.getWinner();
			roundScores[prevTrickWinner] += trick.getPoints();	
			tricksLeft--;	
			System.out.println(players[trick.getWinner()].getName() + " takes the trick");
		}
		calculateScores();
	}
	
	/**
	 * Determines whether a player 'shot the moon' (took all 13 points
	 * cards). A player who manages to shoot the moon receives a round score
	 * of zero, while all other players receive 26.
	 *
	 * @return true, if a player 'shot the moon'
	 */
	public boolean shotTheMoon() {
		for (int i = 0; i < roundScores.length; i++) {
			if (roundScores[i] == 26) 
				return true;
		}
		return false;
	}
	
	/**
	 * Gets the round scores of the players.
	 *
	 * @return the round scores of the players
	 */
	public int[] getScores() {
		return roundScores;
	}
	
	/**
	 * Calculates the total scores of each player for the round.
	 */
	public void calculateScores() {
		if (shotTheMoon()) {
			for (int i = 0; i <roundScores.length; i++) {
				if (roundScores[i] == 0)
					roundScores[i] = 26;
				else
					roundScores[i] = 0;
			}
			
		}
	}
	
	

}

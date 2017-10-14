
public class Round {
	private Player[] players;
	private int[]    roundScores;
	private int      roundsLeft;
	private int      twoOfClubsPos;
	
	
	public Round(Player[] players, int twoOfClubsPos) {
		roundsLeft = 13;
		roundScores = new int[players.length];
		this.players = players;
		this.twoOfClubsPos = twoOfClubsPos;
	}
		
	public void playRound() { //goes through 13 tricks, counting points
		Trick trick;
		int prevTrickWinner = -1; //need a value here for if/else to work
		while(roundsLeft > 0) {
			if (roundsLeft == 13) {
				trick = new Trick(players, twoOfClubsPos); //first trick
				prevTrickWinner = trick.getWinnerPos();
			}
			else
				trick = new Trick(players, prevTrickWinner);
			
			prevTrickWinner = trick.getWinnerPos();
			roundScores[prevTrickWinner] += trick.getPoints();	
			roundsLeft--;
		}
		
		//add up points

		
	}
	//getters so Hearts.java can access scores
	//shoot the moon, method?
}

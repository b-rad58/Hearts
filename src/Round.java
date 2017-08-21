
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
		
	public void playRound() {
		Trick firstTrick = new Trick(players, twoOfClubsPos);
		
	}
	
}

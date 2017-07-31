
public enum Suits {
	HEARTS('H'), DIAMONDS('D'), CLUBS('C'), SPADES('S');
	
	private final char suit;
	
	private Suits(char suit) {
		this.suit = suit;
	}
	
	public char getSuit() {
		return suit;
	}
}

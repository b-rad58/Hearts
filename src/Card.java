import java.util.Objects;

//unicode values for cards https://en.wikipedia.org/wiki/Playing_cards_in_Unicode

// put suit/rank enums within the class
public class Card { //implements comparable
	private Suits suit;
	private Ranks rank;


	public Card(Ranks rank, Suits suit) {
		this.suit  = suit;
		this.rank = rank; 
	}
		
	public Suits getCardSuit() {
		return suit;
	}

	public Ranks getCardValue() {
		return rank;
	}
	
	public String toString() {
		return suit.getSuit() + ":" + rank.getRank();
	}
	
	public boolean equals(Object o) {
		if (this == o) //self check
			return true;
		if (o == null) //null check
			return false;
		if (this.getClass() != o.getClass()) //type check + cast 
			return false;
		Card card = (Card) o;
		//field comparison
		return Objects.equals(this.suit, card.suit) && Objects.equals(this.rank, card.rank);
	}


		/*public String getCard() {
			return suit + ":" + value;
		}*/
	
}

//Dennis Sosa (109198226)
//Card Class for Deck & Player's Hand

package final_project_gui;
public class Card{
	private char suit;
	private int rank;

	public Card(){
		rank = 2;
		suit = 'S';
	}

	public Card(char ch, int i){
		rank = i;
		suit = Character.toUpperCase(ch);
	}

	public int getRank(){
		return rank;
	}

	public char getSuit(){
		return suit;
	}

	public void displayCard(){
		String suitString;
		switch(suit){
			case 'S':
				suitString = "Spades";
				break;
			case 'H':
				suitString = "Hearts";
				break;
			case 'D':
				suitString = "Diamonds";
				break;
			case 'C':
				suitString = "Clubs";
				break;
			default:
				suitString = "Invalid Suit";
		}
		if(rank >= 2 && rank < 11){
			System.out.println(rank + " of " + suitString);
		}

		else{
			switch(rank){
				case 11:
					System.out.println("JACK of " + suitString);
					break;
				case 12:
					System.out.println("QUEEN of " + suitString);
					break;
				case 13:
					System.out.println("KING of " + suitString);
					break;
				case 14:
					System.out.println("ACE of " + suitString);
					break;
			}
		}
		return;
	}
	
	public String getFileName(){
		String fileName;
		switch(suit){
			case 'S':
				fileName = "Playing_card_spade_";
				break;
			case 'H':
				fileName = "Playing_card_heart_";
				break;
			case 'D':
				fileName = "Playing_card_diamond_";
				break;
			case 'C':
				fileName = "Playing_card_club_";
				break;
			default:
				fileName = "green_card.jpg";
				return fileName;
		}
		if(rank >= 2 && rank < 11){
			fileName+=rank;
		}

		else{
			switch(rank){
				case 11:
					fileName += "J";
					break;
				case 12:
					fileName += "Q";
					break;
				case 13:
					fileName += "K";
					break;
				case 14:
					fileName += "A";
					break;
			}
		}
		return fileName+=".jpg";
	}
}

//Dennis Sosa(109198226)
//Deck Class for the game with methods to pass to each of the player's hands.

package final_project_gui;
import java.util.ArrayList;

public class CardDeck extends Card{
	private ArrayList<Card> cardDeck;

	//13 Cards in each suit
	//52 new cards
	public CardDeck(){
		cardDeck = new ArrayList<Card>();
		for(int i=2; i<15; i++){
			cardDeck.add(new Card('S',i));
			cardDeck.add(new Card('H',i));
			cardDeck.add(new Card('D',i));
			cardDeck.add(new Card('C',i));
		}
	}
	
	public void display(){
		for(int i=0; i<cardDeck.size(); i++){
			(cardDeck.get(i)).displayCard();
		}
	}
	
	/*
	public void displayHand(ArrayList<Card> a){
		for(int i=0; i<a.size(); i++){
			(a.get(i)).displayCard();
		}	
	}
	*/
	
	public void draw(){	
		cardDeck.remove(cardDeck.size() - 1);
	}
	
	public ArrayList<Card> getCardDeck(){
		return cardDeck;
	}
	
	public int myDeckSize(){
		return cardDeck.size();
	}
	public void shuffleDeck(){
		ArrayList<Card> temp = new ArrayList<Card>();
		while(!cardDeck.isEmpty()){
			int ind = (int)(Math.random()*cardDeck.size());
			temp.add(cardDeck.get(ind));
			cardDeck.remove(ind);
		}
		cardDeck = temp;
	}

}
//Dennis Sosa (109198226)
//Player's Hand Card Class

package final_project_gui;
import java.util.ArrayList;

public class CardHand extends CardDeck{
	private ArrayList<Card> Hand;
	
	public CardHand(ArrayList<Card> cardDeck){
		Hand = new ArrayList<Card>();
		ArrayList<Card> temp = new ArrayList<Card>();
		temp = cardDeck;
		
		for(int i=0; i<13; i++){
			draw();
			Hand.add(temp.remove(temp.size()-1));
		}
	}
	
        public ArrayList<Card> getPlayer(){
            return Hand;
        }
        
	public void displayPlayer(){
		for(int i=0; i<Hand.size(); i++){
			(Hand.get(i)).displayCard();
		}
		//displayHand(Hand);
	}
	
	public int handSize(){
		return Hand.size();
	}
	
	public void play(int i){
		Hand.get(i).displayCard();
		//Hand.remove(i);
	}
	
	public int checkRank(int i){
		return Hand.get(i).getRank();
	}
	
	public char checkSuit(int i){
		return Hand.get(i).getSuit();
	}
	
	public void drawPlay(int i){
		Hand.remove(i);
	}
	
	public int ind(int i,char x){
		for(int j=0;j<Hand.size();j++){
			if(Hand.get(j).getRank()==i && Hand.get(j).getSuit()==x)
				return j;
		}
		return 0;
	}
	
	public String cardImage(int i){
            return (Hand.get(i)).getFileName();	
	}
	
}

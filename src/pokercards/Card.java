package pokercards;

/**
* A single card of a deck
* @author sangeetha
*/

public class Card {
	public int id;
	public char suit;
	public int rank;
	
	/**
	* Constructor
	* @param id card id
	* @param cRank number corresponding to rank of the card 
	* @param suit character representing suit name
	*/
	public Card(int id, String cRank, char suit){
		this.id = id;
		this.suit=suit;
		switch(cRank){
			case "J" : this.rank=11;break;
			case "Q" : this.rank=12;break;
			case "K" : this.rank=13;break;
			case "A" : this.rank=14;break;
			default : this.rank = Integer.parseInt(cRank);break;
		}
	}
	
	/**
	* get card info
	* @return card info: String corresponding to info of the card 
	*/
	public String getCard(){
		String rank;
		switch(this.rank){
			case 11 : rank="J";break;
			case 12 : rank="Q";break;
			case 13 : rank="K";break;
			case 14 : rank="A";break;
			default : rank = Integer.toString(this.rank);break;
		}
		return rank+Character.toString(this.suit);		
	}
}

package pokerlistings;

import java.util.ArrayList;
import pokercards.*;

/**
* Class for Poker Listing - Three of a Kind 
* @author Sangeetha
**/
public class ThreeOfAKind implements Listing{
	private String listingType = "Three of a Kind"; 
	private int listingRank = 7;
	private int kickerRank = 0;
	private ArrayList<Card> kickerCards = new ArrayList<Card>();
	
	public ThreeOfAKind(PokerHand pokerHand) {
		calculateKickerCards(pokerHand);
	}
	
	/**
	* calculate kicker cards and rank 
	*/	
	private void calculateKickerCards(PokerHand pokerHand){
		// Get the three cards rank
		Card card = pokerHand.getCardWithRank(pokerHand.getRankFromNumberOfTimesPresent(3));
		kickerCards.add(card);
		kickerRank = card.rank;		
	}
	
	/**
	* Get listing type  
	* @return String : Listing Type 
	*/
	public String getListingType(){
		return this.listingType;
	}
	
	/**
	* Get listing rank  
	* @return int : Listing Rank 
	*/	
	public int getListingRank(){
		return this.listingRank;
	}
	
	/**
	* Get Kicker Points  
	* @return int : Kicker Points 
	*/	
	public int getKickerRank(){
		return this.kickerRank;
	}
	
	/**
	* Get arraylist of Kicker cards  
	* @return ArrayList : list of kicker cards to determine tie breaker 
	*/
	public ArrayList<Card> getKickerCards(){		
		return this.kickerCards;
	}	
}

package pokerlistings;

import java.util.ArrayList;
import pokercards.*;

/**
* Class for Poker Listing - Full House 
* @author Sangeetha
**/
public class FullHouse implements Listing{
	private String listingType = "Full House"; 
	private int listingRank = 4;
	private int kickerRank = 0;
	private ArrayList<Card> kickerCards = new ArrayList<Card>();
	
	public FullHouse(PokerHand pokerHand) {
		calculateKickerCards(pokerHand);
	}
	
	/**
	* calculate kicker cards and rank 
	*/	
	private void calculateKickerCards(PokerHand pokerHand){
		// Get the card that is present twice
		Card card = pokerHand.getCardWithRank(pokerHand.getRankFromNumberOfTimesPresent(2));
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

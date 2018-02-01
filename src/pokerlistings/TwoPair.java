package pokerlistings;

import java.util.ArrayList;
import pokercards.*;

/**
* Class for Poker Listing - Two Pair 
* @author Sangeetha
**/
public class TwoPair implements Listing{
	private String listingType = "Two Pair"; 
	private int listingRank = 8;
	private int kickerRank = 0;
	private ArrayList<Card> kickerCards = new ArrayList<Card>();
	
	public TwoPair(PokerHand pokerHand) {
		calculateKickerCards(pokerHand);
	}
	
	/**
	* calculate kicker cards and rank 
	*/	
	private void calculateKickerCards(PokerHand pokerHand){
		// Get the one card other than than the two pair
		Card card = pokerHand.getCardWithRank(pokerHand.getRankFromNumberOfTimesPresent(1));
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
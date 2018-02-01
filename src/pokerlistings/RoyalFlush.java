package pokerlistings;

import java.util.*;
import pokercards.*;


/**
* Class for Poker Listing - Royal Flush
* @author sangeetha
**/
public class RoyalFlush implements Listing{

	private String listingType = "Royal Flush"; 
	private int listingRank = 1;
	private int kickerRank = 0;
	private ArrayList<Card> kickerCards = new ArrayList<Card>();
	
	public RoyalFlush(PokerHand pokerHand){
		calculateKickerCards(pokerHand);
	}
	
	/**
	* calculate kicker cards and rank 
	*/	
	private void calculateKickerCards(PokerHand pokerHand){	
		//No Kicker as its highest listing, anyone with another royal flush will be tied
		kickerRank = 0;		
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
	* Get Kicker Rank  
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

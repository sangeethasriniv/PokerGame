package pokerlistings;

import java.util.ArrayList;
import pokercards.*;

/**
* Class for Poker Listing - Straight 
* @author Sangeetha
**/
public class Straight implements Listing{
	private String listingType = "Straight"; 
	private int listingRank = 6;
	private int kickerRank = 0;
	private ArrayList<Card> kickerCards = new ArrayList<Card>();
	
	public Straight(PokerHand pokerHand) {
		calculateKickerCards(pokerHand);
	}
	
	/**
	* calculate kicker cards and rank 
	*/	
	private void calculateKickerCards(PokerHand pokerHand){
		//Get the card with highest rank
		Card card = pokerHand.getMaxRankCardInHand();
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

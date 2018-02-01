package pokerlistings;

import java.util.ArrayList;
import pokercards.*;

/**
* Class for Poker Listing - One Pair 
* @author Sangeetha
**/
public class OnePair implements Listing{
	private String listingType = "One Pair"; 
	private int listingRank = 9;
	private int kickerRank = 0;
	private ArrayList<Card> kickerCards = new ArrayList<Card>();
	
	public OnePair(PokerHand pokerHand) {
		calculateKickerCards(pokerHand);
	}
	
	/**
	* calculate kicker cards and rank 
	*/	
	private void calculateKickerCards(PokerHand pokerHand){
		// Get other cards other than one pair
		ArrayList<Integer> arrCards = pokerHand.getRanksPresentOnce();
		for(int rank : arrCards){
			Card card = pokerHand.getCardWithRank(rank);
			kickerCards.add(card);
			kickerRank+=rank;
		}
		if(pokerHand.isCountOfSameRanksPresent(3)){
			int rank = pokerHand.getRankFromNumberOfTimesPresent(3);
			Card card = pokerHand.getCardWithRank(rank);
			kickerCards.add(card);
			kickerRank+=rank;
		}
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
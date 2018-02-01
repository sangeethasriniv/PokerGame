package pokerprocessor;

import pokercards.PokerHand;
import pokerlistings.*;

/**
* To Orchestrate the hand to different type of poker processors 
* and find the hand type, ranking and kicker cards for a poker hand
* @author sangeetha
**/
public class PokerHandCalculator {
	
	private PokerHand pokerHand;
	
	/**
	* Constructor
	* @param hand: a poker hand object
	*/
	public PokerHandCalculator(PokerHand hand){
		this.pokerHand = hand;
	}
	
	/**
	* Constructor
	*/
	public PokerHandCalculator(){}
	
	/**
	* Calculate nature of a poker hand
	* @throws Exception when unable to find listing
	*/
	public void calculate() throws Exception{
		Listing chosenListing = null;
		//If all cards are in same suit, delegate to same suit processor
		if(pokerHand.getSuitCount()==1){
			chosenListing = new SameSuitCards().process(pokerHand);
		} // If more than one suit is present (mixed suits) 
		else if (pokerHand.getSuitCount()>1){
			//check if no duplicate ranks in mixed suits
			if(pokerHand.getRankCount()==5){
				chosenListing = new MixedSuitsUniqueRankCards().process(pokerHand);
			}
			//if duplicate ranks in mixed suits
			else{
				chosenListing = new MixedSuitsDuplicateRankCards().process(pokerHand);				
			}
		}
		//get the listing chosen by processing hand and assign the listing, rank and kicker cards to hand
		if(chosenListing!=null){
			this.pokerHand.setListingRank(chosenListing.getListingRank());
			this.pokerHand.setListingType(chosenListing.getListingType());
			this.pokerHand.setKickerCards(chosenListing.getKickerCards());
			this.pokerHand.setKickerRank(chosenListing.getKickerRank());
		}
		else {
			throw new Exception("unable to find poker listing");
		}		
	}
}
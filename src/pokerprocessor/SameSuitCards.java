package pokerprocessor;

import pokerlistings.*;
import pokercards.PokerHand;

/**
* To find the right listing and instatiate the appropriate listing object 
* when cards in hand are in same suit
* @author sangeetha
**/
public class SameSuitCards extends PokerHandCalculator {
	
	/**
	* Constructor
	*/
	public SameSuitCards(){}
	
	/**
	* process nature of poker hand
	* @param pokerHand pokerHand object
	* @return Listing object having the identified listing info
	*/
	public Listing process(PokerHand pokerHand){
		
		if(pokerHand.isAllRoyalsPresent())
			return new RoyalFlush(pokerHand);
		if(pokerHand.isConsectiveCardsPresent())
			return new StraightFlush(pokerHand);
		
		return new Flush(pokerHand);
	}	
}

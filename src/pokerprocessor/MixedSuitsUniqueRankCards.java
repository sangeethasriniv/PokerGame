package pokerprocessor;

import pokerlistings.*;
import pokercards.*;

/**
* To find the right listing and instatiate the appropriate listing object 
* when cards in hand are in different suit and every card is of unique rank
* @author sangeetha
**/
public class MixedSuitsUniqueRankCards extends PokerHandCalculator {
	/**
	* Constructor
	*/
	public MixedSuitsUniqueRankCards(){}
	
	/**
	* process nature of poker hand
	* @param pokerHand pokerHand object
	* @return Listing object having the identified listing info
	*/
	public Listing process(PokerHand pokerHand){
		
		if(pokerHand.isConsectiveCardsPresent())
			return new Straight(pokerHand);
		else 
			return new HighCard(pokerHand);
	}	
}

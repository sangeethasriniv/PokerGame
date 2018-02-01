package pokerprocessor;

import pokerlistings.*;
import pokercards.*;

/**
* To find the right listing and instatiate the appropriate listing object 
* when cards in hand are in different suit and there cards of duplicate rank
* @author sangeetha
**/
public class MixedSuitsDuplicateRankCards extends PokerHandCalculator {
	/**
	* Constructor
	*/
	public MixedSuitsDuplicateRankCards(){}
	
	/**
	* process nature of poker hand
	* @param pokerHand pokerHand object
	* @return Listing object having the identified listing info
	*/
	public Listing process(PokerHand pokerHand){
		
		if(pokerHand.isCountOfSameRanksPresent(4))
			return new FourOfAKind(pokerHand);
		
		if(pokerHand.isCountOfSameRanksPresent(3)){
			if(pokerHand.isCountOfSameRanksPresent(2))
				return new FullHouse(pokerHand);
			if(pokerHand.isRankPresent(12) && pokerHand.isRankPresent(13))
				return new ThreeOfAKind(pokerHand);
			else
				return new OnePair(pokerHand);
		}
		
		if(pokerHand.isCountOfSameRanksPresent(2)){
			if(pokerHand.getCountOfRankCouples()==2)
				return new TwoPair(pokerHand);
			if(pokerHand.getCountOfRankCouples()==1)
				return new OnePair(pokerHand);			
		}	
		return null;
	}
}
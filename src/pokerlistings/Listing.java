package pokerlistings;
import java.util.*;
import pokercards.Card;
/**
* Interface for a type of Poker Hand
*/
public interface Listing {
	public String getListingType();
	public int getListingRank();
	public int getKickerRank();
	public ArrayList<Card> getKickerCards();	
}

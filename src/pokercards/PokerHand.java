package pokercards;
import java.util.*;
import org.json.*;

/**
* A single hand in poker
* @author Sangeetha
**/
public class PokerHand {
	
	public ArrayList<Card> cards = new ArrayList<Card>();
	
	private int listingRank = 0;
	private String listingType = "";
	private ArrayList<Card> kickerCards = new ArrayList<Card>();
	private int kickerRank = 0;
	
	private ArrayList<Integer> allRanks = new ArrayList<Integer>();
	private boolean consecutiveRanks=false;
	private boolean allRoyals=false;
	
	private int suitCount = 0;
	private int rankCount =0;
	private HashMap<Character,Integer> suitInfo = new HashMap<Character, Integer>();
	private HashMap<Integer,Integer> rankInfo = new HashMap<Integer, Integer>();
	
	/**
	* Constructor
	* @param jsonArrHand json array as set of cards in hand
	*/
	public PokerHand(JSONArray jsonArrHand){
		this.getCardInfoFromJson(jsonArrHand);
		this.consecutiveRanks=this.isConsecutiveRanks();
		this.allRoyals=this.isAllRoyal();
		System.out.println("consecutiveRanks:"+this.consecutiveRanks);
		System.out.println("allRoyals:"+this.allRoyals);	
	}

	/**
	* Process json and populate arraylist of cards , suitInfo and rankInfo
	* @param jsonArrHand: json array having cards info
	*/
	private void getCardInfoFromJson(JSONArray jsonArrHand){
		try{
			Card c;
			String rank;
			char suit;
			System.out.println("Cards in PokerHand");
			for(int i=0;i<jsonArrHand.length();i++){
				String oneCard = jsonArrHand.getString(i).trim().toUpperCase();
				if(oneCard.matches("10[D,C,H,S]")){
					System.out.println(oneCard);
					rank = "10";
					suit = oneCard.charAt(oneCard.length()-1);
					c = new Card(i, rank, suit);
				}
				else if(oneCard.matches("^([A,J,K,Q]|[1-9])[D,C,H,S]")){
					System.out.println(oneCard);
					rank = Character.toString(oneCard.charAt(0));
					if(rank == "1") {throw new Exception("invalid card:"+oneCard );}
					suit = oneCard.charAt(1);
					c = new Card(i, rank, suit);
				}
				else{
					throw new Exception("invalid card:"+oneCard );
				}
				
				//TODO: Check for duplicates before adding the card, if duplicate throw exception
				this.cards.add(c);
				this.allRanks.add(c.rank);					
				populateSuiteInfo(new Character(suit));
				populateRankInfo(new Integer(c.rank));
			}
			System.out.println(suitInfo);
			System.out.println(rankInfo);
			System.out.println(this.allRanks);
			
			this.suitCount = this.suitInfo.size();
			this.rankCount = this.rankInfo.size();
			System.out.println("suitcount:"+this.suitCount);
			System.out.println("rankCount:"+this.rankCount);
		}
		catch (Exception e){
			System.out.println("Error in Input JSON Array" + e.getMessage());
		}
	}
	
	/**
	* populate HashMap with suit letter and count of cards in each suit
	* @param suit character representing the suit
	*/
	private void populateSuiteInfo(Character suit){
		if(this.suitInfo.containsKey(suit)){
			this.suitInfo.put(suit, (Integer)suitInfo.get(suit)+1);
		}
		else{
			this.suitInfo.put(suit, 1);
		}	
	}
	
	/**
	* populate HashMap with rank number and count of cards in each rank
	* @param rank int representing rank 
	*/
	private void populateRankInfo(Integer rank){
		if(this.rankInfo.containsKey(rank)){
			this.rankInfo.put(rank, (Integer)rankInfo.get(rank)+1);
		}
		else{
			this.rankInfo.put(rank, 1);
		}			
	}
	
	/**
	* Check if ranks are consecutive    
	* @return return true if rannks in the suit are consecutive
	*/
	private boolean isConsecutiveRanks(){
		Collections.sort(this.allRanks);
		int rankCount = this.allRanks.size()-1;
		Integer LastCardRank = this.allRanks.get(rankCount);
		if(this.allRanks.get(0)==2){
			//if ace is not before 2
			if(!(LastCardRank==14))
				return false;
			else
				rankCount--; 
		}
		Integer previousRank = this.allRanks.get(0);
		for(int i=1;i<rankCount;i++){
			Integer currentRank = this.allRanks.get(i);
			if(currentRank!=previousRank+1){
				return false;
			}
			previousRank = currentRank; 
		}
		return true;
	}

	/**
	* Check if ranks are all royal    
	* @return return true if ranks are in order 10,J,Q,K,A
	*/
	private boolean isAllRoyal(){
		int sumRanks=0;
		for(int i=0;i<this.allRanks.size();i++){
			sumRanks = this.allRanks.get(i) + sumRanks;
		}
		//Add up ranks to determine all royals
		if(sumRanks == (10+11+12+13+14))
			return true;
		else 
			return false;
	}

	/**
	* Check if same number of a rank is present in hand as the given number 
	* @param number count of the rank to be checked    
	* @return return true if number of ranks exists
	*/
	public boolean isCountOfSameRanksPresent(int number){
		if(this.rankInfo.containsValue(number)){
			return true;
		}
		return false;
	}

	/**
	* Get the rank based on number of times a rank is duplicated
	* @param numberOfTimesPresent return the rank that is present this many number of times
	* @return numberOfTimesPresent rank that is present this many number of times   
	* @return return rank number
	*/
	public int getRankFromNumberOfTimesPresent(int numberOfTimesPresent){
		Set<Map.Entry<Integer,Integer>> set = rankInfo.entrySet();
		Iterator<Map.Entry<Integer,Integer>> it = set.iterator();
		
		while(it.hasNext()) {
			Map.Entry<Integer,Integer> entry = (Map.Entry<Integer, Integer>)it.next();
            if (entry.getValue().equals(numberOfTimesPresent)) {
                return(entry.getKey());
            }
        }
		return 0;
	}

	/**
	* Check if a rank is present in hand     
	* @param number of the rank to be checked
	* @return return true if rank exists
	*/
	public boolean isRankPresent(int number){
		if(this.rankInfo.containsKey(number)){
			return true;
		}
		return false;
	}

	/**
	* get the card with maximimum rank in hand    
	* @return Card object having the max rank
	*/
	public Card getMaxRankCardInHand(){
		Collections.sort(this.allRanks);
		int maxRank = this.allRanks.get(this.allRanks.size()-1);
		for (Card card : this.cards){
			if(card.rank == maxRank){
				return card;
			}
		}
		return null;
	}

	/**
	* get the card with given rank in hand
	* @param rank of the card to be returned    
	* @return Card object having the given rank
	*/
	public Card getCardWithRank(int rank){
		for (Card card : this.cards){
			if(card.rank == rank){
				return card;
			}
		}
		return null;
	}
	
	/**
	* get number of rank couples present     
	* @return count of rank couples
	*/
	public int getCountOfRankCouples(){
		
		int count = 0;
		Set<?> set = rankInfo.keySet();
		Iterator<?> it = set.iterator();
		
		while(it.hasNext()) {
			if (rankInfo.get(it.next())==2){
                count++;
            }
        }
		System.out.println("count of rank couples:" + count);
		return count;
	}

	/**
	* get arraylist of ranks that are present once     
	* @return arraylist of ranks
	*/
	public ArrayList<Integer> getRanksPresentOnce(){
		ArrayList<Integer> arr = new ArrayList<Integer>(); 
		Set<Map.Entry<Integer,Integer>> set = rankInfo.entrySet();
		Iterator<Map.Entry<Integer,Integer>> it = set.iterator();
		
		while(it.hasNext()) {
			Map.Entry<Integer,Integer> entry = (Map.Entry<Integer, Integer>)it.next();
            if (entry.getValue().equals(1)) {
                arr.add(entry.getKey());
            }
        }
		return arr;
	}

	/**
	* Display kicker cards    
	* @return String Buffer denoting all kicker cards
	*/
	public StringBuffer displayKickerCards(){
		StringBuffer kcards = new StringBuffer();
		if(this.kickerCards.size() == 0) 
			kcards.append("");
		else{
			for (Card card : this.kickerCards){
				kcards.append(card.getCard() + ",");
			}
			kcards.deleteCharAt(kcards.length()-1);
		}
		System.out.println("display kicker cards");
		System.out.println(kcards);
		return kcards;
	}


	/**
	* getter for suitCount   
	* @return return no of suits in this hand
	*/
	public int getSuitCount(){
		return this.suitCount;
	}

	/**
	* getter for rankCount   
	* @return return no of ranks in this hand
	*/
	public int getRankCount(){
		return this.rankCount;
	}


	/**
	* setter for Listing Type 
	* @param listingType to be set for hand  
	*/
	public void setListingType(String listingType){
		this.listingType = listingType;
	}
	
	/**
	* setter for Listing Rank
	* @param listingRank to be set for hand   
	*/
	public void setListingRank(int listingRank){
		this.listingRank = listingRank;
	}
	
	/**
	* setter for kicker Rank
	* @param kickerRank to be set for hand    
	*/
	public void setKickerRank(int kickerRank){
		this.listingRank = kickerRank;
	}

	/**
	* setter for Kicker cards
	* @param kickerCards for the hand   
	*/
	public void setKickerCards(ArrayList<Card> kickerCards){
		this.kickerCards = kickerCards;
	}

	/**
	* getter for Listing Type   
	* @return return this objects Listing Type
	*/
	public String getListingType(){
		return this.listingType;
	}
	
	/**
	* getter for Listing Rank   
	* @return return this objects Listing rank
	*/
	public int getListingRank(){
		return this.listingRank;
	}

	/**
	* getter for Kicker Rank   
	* @return return this objects Kicker rank
	*/
	public int getKickerRank(){
		return this.kickerRank;
	}

	/**
	* getter for Kicker cards   
	* @return return this objects kicker cards
	*/
	public ArrayList<Card> getKickerCards(){
		return this.kickerCards;
	}
	
	/**
	* getter for All ranks    
	* @return return All ranks of the hand as arraylist
	*/
	public ArrayList<Integer> getAllRanks(){
		return this.allRanks;
	}

	/**
	*  if consecutive ranks    
	* @return return true if consecutive cards
	*/
	public boolean isConsectiveCardsPresent(){
		return this.consecutiveRanks;
	}

	/**
	*  if all royal ranks    
	* @return return true if all royal cards
	*/
	public boolean isAllRoyalsPresent(){
		return this.allRoyals;
	}

}
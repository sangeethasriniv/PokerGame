package play;
import pokerprocessor.*;
import pokercards.*;
import java.util.*;
import org.json.*;


/**
* Main Program to pass in the poker hand and get the listing
* @author sangeetha
*/
public class PlayPoker {

	public static void main(String[] args){
		System.out.println("*****Enter Cards in Hand : eg: 2H, 5C, 9D, AS, JC *****");
		JSONArray jsonArrHand = new JSONArray(Arrays.asList(getInput()));
		PokerHand pHand = new PokerHand(jsonArrHand);
		System.out.println("*****Processing Input ******");
		PokerHandCalculator pHandCalculator = new PokerHandCalculator(pHand);
		try{
			pHandCalculator.calculate();
			System.out.println("*****************************************");
			System.out.println("Hand Listing : "+ pHand.getListingType());
			System.out.println("Kicker Card(s) : "+ pHand.displayKickerCards());
			System.out.println("*****************************************");

		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
	public static String[] getInput(){
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine(); 
		/*String cards = "";
		while(! input.equals("stop")) {
		     cards += input + ",";  
		     input = scanner.nextLine();
		}*/
		String[] result = input.split(","); 
		scanner.close();
		return result;
	}
}

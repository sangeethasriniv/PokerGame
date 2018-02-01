package pokertests;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import java.lang.reflect.*;
import pokerprocessor.*;
import pokercards.*;

import java.util.*;
import org.json.*;

/**
* Tests for identifying Listing Type of given Hand
* @author sangeetha
*/
public class TestHandListing {
	
	/**
	* Method to be executed before each test
	* @param testMethod object of the test method being executed
	*/
	@BeforeMethod
	public void beforeTestMethod(Method testMethod){
	   System.out.println("Testmethod: " + testMethod.getName());       
	}

	/**
	* Test to check a Royal Flush
	* @param testContext context object of the test
	*/
	@Test(alwaysRun = true)
	public void testRoyalFlush(ITestContext testContext) {
		String[] hand = {"10H","JH","QH","KH","AH"};
		testContext.setAttribute("expectedListingType", "Royal Flush");
		testContext.setAttribute("expectedKickerCards", "");
		this.getListing(testContext, hand);				
	}
	/**
	* Test to check a Straight Flush
	* @param testContext context object of the test
	*/
	@Test(alwaysRun = true)
	public void testStraightFlush(ITestContext testContext) {
		String[] hand = {"5H","6H","7H","8H","9H"}; 
		testContext.setAttribute("expectedListingType", "Straight Flush");
		testContext.setAttribute("expectedKickerCards", "9H");
		this.getListing(testContext, hand);				
	}
	
	/**
	* Test to check a hand that is not Straight though cards are consecutive
	* @param testContext context object of the test
	*/
	@Test(alwaysRun = true)
	public void testStraightbutNotStraightFlush(ITestContext testContext) {
		String[] hand = {"QH","KH","AH","2H","3H"}; 
		testContext.setAttribute("expectedListingType", "Flush");
		testContext.setAttribute("expectedKickerCards", "AH");
		this.getListing(testContext, hand);				
	}

	/**
	* Test to check a Flush
	* @param testContext context object of the test
	*/	
	@Test(alwaysRun = true)
	public void testFlush(ITestContext testContext) {
		String[] hand = {"AH","2H","9H","4H","5H"}; 
		testContext.setAttribute("expectedListingType", "Flush");
		testContext.setAttribute("expectedKickerCards", "AH");
		this.getListing(testContext, hand);				
	}
	
	/**
	* Test to check a Straight
	* @param testContext context object of the test
	*/
	@Test(alwaysRun = true)
	public void testStraight(ITestContext testContext) {
		String[] hand = {"10D","9S","8C","7H","6D"}; 
		testContext.setAttribute("expectedListingType", "Straight");
		testContext.setAttribute("expectedKickerCards", "10D");
		this.getListing(testContext, hand);				
	}

	/**
	* Test to check a High Card
	* @param testContext context object of the test
	*/
	@Test(alwaysRun = true)
	public void testHighCard(ITestContext testContext) {
		String[] hand = {"8D","3H","2C","4H","KS"}; 
		testContext.setAttribute("expectedListingType", "High Card");
		testContext.setAttribute("expectedKickerCards", "KS");
		this.getListing(testContext, hand);				
	}
	
	/**
	* Test to check a Four of a Kind
	* @param testContext context object of the test
	*/
	@Test(alwaysRun = true)
	public void testFourOfAKind(ITestContext testContext) {
		String[] hand = {"AD","AH","AC","AS","10H"}; 
		testContext.setAttribute("expectedListingType", "Four of a Kind");
		testContext.setAttribute("expectedKickerCards", "10H");
		this.getListing(testContext, hand);				
	}
	
	/**
	* Test to check a Three of a Kind
	* @param testContext context object of the test
	*/
	@Test(alwaysRun = true)
	public void testThreeOfAKind(ITestContext testContext) {
		String[] hand = {"2H","2D","2C","QS","KH"}; 
		testContext.setAttribute("expectedListingType", "Three of a Kind");
		testContext.setAttribute("expectedKickerCards", "2H");
		this.getListing(testContext, hand);				
	}

	/**
	* Test to check a hand with three same rank and two different rank cards which are not king and queen
	* @param testContext context object of the test
	*/
	@Test(alwaysRun = true)
	public void testNotQuiteAThreeOfAKind(ITestContext testContext) {
		String[] hand = {"2H","2D","2C","5S","KH"}; 
		testContext.setAttribute("expectedListingType", "One Pair");
		testContext.setAttribute("expectedKickerCards", "5S,KH,2H");
		this.getListing(testContext, hand);				
	}

	/**
	* Test to check a Full House
	* @param testContext context object of the test
	*/
	@Test(alwaysRun = true)	public void testFullHouse(ITestContext testContext) {
		String[] hand = {"2H","2D","2C","QS","QH"}; 
		testContext.setAttribute("expectedListingType", "Full House");
		testContext.setAttribute("expectedKickerCards", "QS");
		this.getListing(testContext, hand);				
	}

	/**
	* Test to check a One Pair
	* @param testContext context object of the test
	*/	
	@Test(alwaysRun = true)
	public void testOnePair(ITestContext testContext) {
		String[] hand = {"7H","7D","5C","8S","QH"}; 
		testContext.setAttribute("expectedListingType", "One Pair");
		testContext.setAttribute("expectedKickerCards", "5C,8S,QH");
		this.getListing(testContext, hand);				
	}

	/**
	* Test to check a Two Pair
	* @param testContext context object of the test
	*/	
	@Test(alwaysRun = true)
	public void testTwoPair(ITestContext testContext) {
		String[] hand = {"2H","2D","5C","5S","QH"}; 
		testContext.setAttribute("expectedListingType", "Two Pair");
		testContext.setAttribute("expectedKickerCards", "QH");
		this.getListing(testContext, hand);				
	}

	/**
	* Method to set listing type and kicker cards from given hand
	* @param testContext context object of the test
	* @param hand Array of cards in a hand
	*/
	public void getListing(ITestContext testContext, String[] hand){
		JSONArray jsonArrHand = new JSONArray(Arrays.asList(hand));		
		PokerHand pokerHand = new PokerHand(jsonArrHand);
		
		PokerHandCalculator pHandCalculator = new PokerHandCalculator(pokerHand);
		try{
			pHandCalculator.calculate();
			testContext.setAttribute("actualListingType", pokerHand.getListingType().toString());
			testContext.setAttribute("actualKickerCards", pokerHand.displayKickerCards().toString());
			
			System.out.println("*****************************************");
			System.out.println("Hand Listing : "+ testContext.getAttribute("actualListingType"));
			System.out.println("Kicker Card(s) : "+ testContext.getAttribute("actualKickerCards"));
			System.out.println("*****************************************");
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		String expectedListingType = testContext.getAttribute("expectedListingType").toString();
		testContext.getAttribute("expectedKickerCards").toString();
		String expectedKickerCards = testContext.getAttribute("expectedKickerCards").toString();

		String actualListingType = testContext.getAttribute("actualListingType").toString();
		String actualKickerCards = testContext.getAttribute("actualKickerCards").toString();

		Assert.assertEquals(expectedListingType, actualListingType);
		Assert.assertEquals(expectedKickerCards, actualKickerCards);
		System.out.println("Test Complete");
	}
	
 }
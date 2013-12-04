package excelTests;


import org.openqa.selenium.By;
//import org.openqa.selenium.By.ById;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import excelTests.LoggedOutPage;

public class LoggedOutTest extends BaseTestClass{
	
	private static final String AMAZON_HOME_PAGE = "http://www.amazon.com";
	private static final By AMAZON_LOGO = new By.ById("nav-logo");
	private static final By AMAZON_CART_COUNT = new By.ById("nav-cart-count");
	private static final By AMAZON_CART = new By.ById("nav-cart");
	private static final By TODAYS_DEALS_LINK = new By.ByLinkText("Today's Deals");
	private static final String TODAYS_DEALS_INNERHTML = "Today's Deals.";// contains non standard characters
	private static final By TODAYS_DEALS_TEXT = new By.ByXPath("//div[@class='gbh1-bold']");
	private static final By ADD_TO_CART_BUTTON = new By.ById("dealActionButton");
	
	private LoggedOutPage boChrome;
	private LoggedOutPage boFirefox;
	
	@BeforeMethod
	public void setup(){

		boChrome = new LoggedOutPage(chromeThing);
		boFirefox = new LoggedOutPage(firefoxThing);

		boChrome.goToURL(AMAZON_HOME_PAGE);
		boFirefox.goToURL(AMAZON_HOME_PAGE);
	}
	
	@Test
	public void renderedPage(){
		// Initial page navigation takes you to a properly rendered amazon.com page.

		Assert.assertTrue(boChrome.checkForDisplayedWithBy(AMAZON_LOGO));
		Assert.assertTrue(boFirefox.checkForDisplayedWithBy(AMAZON_LOGO));
	}
	
	@Test
	public void cartZero(){
		// Shopping cart is displayed with 0 items when I have not added an item to my shopping cart.
		
		Assert.assertEquals("0", boChrome.getInnerHTML(AMAZON_CART_COUNT));
		Assert.assertEquals("0", boFirefox.getInnerHTML(AMAZON_CART_COUNT));
	}
	
	@Test
	public void todaysDeals(){
		// When I click on Today's Deals I am taken to the daily deals. (from the home page right?)

		boChrome.visibilityPageClickerWithBy(TODAYS_DEALS_LINK);
		boFirefox.visibilityPageClickerWithBy(TODAYS_DEALS_LINK);
		
		Assert.assertEquals(TODAYS_DEALS_INNERHTML, boChrome.getInnerHTML(TODAYS_DEALS_TEXT));
		Assert.assertEquals(TODAYS_DEALS_INNERHTML, boFirefox.getInnerHTML(TODAYS_DEALS_TEXT));
	}
	
	@Test
	public void todaysDealsAddToCart(){
		// When I click on Today's Deals there is a button to add items to my cart for some items.

		boChrome.visibilityPageClickerWithBy(TODAYS_DEALS_LINK);
		boFirefox.visibilityPageClickerWithBy(TODAYS_DEALS_LINK);
		
		// check for add to cart image (Amazon has invalid html and has multiple instances of the same id on a page)
		Assert.assertTrue(boChrome.countNumberOfOccurances(ADD_TO_CART_BUTTON) > 0);
		Assert.assertTrue(boFirefox.countNumberOfOccurances(ADD_TO_CART_BUTTON) > 0);
	}
	
	@Test
	public void loginPrompt(){
		// When I click on add to cart I am prompted to create an account or log in.
		
//		boChrome.visibilityPageClickerWithBy(TODAYS_DEALS_LINK);
//		boFirefox.visibilityPageClickerWithBy(TODAYS_DEALS_LINK);
//		
//		boChrome.visibilityPageClickerWithBy(ADD_TO_CART_BUTTON);
//		boFirefox.visibilityPageClickerWithBy(ADD_TO_CART_BUTTON);
		

		
		
	}
	
	
	
	
}

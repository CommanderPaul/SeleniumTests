package amazonTests;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import amazonTests.LoggedOutPage;
import pageObjects.LoginPage;
import amazonTests.PageObject;
import pageObjects.ParallelPrepHelper;
import utility.DriverGenerator;

public class ParallelPrepTest extends BaseTestClass {
	
	private static final String AMAZON_HOME_PAGE = "http://www.amazon.com";
	private static final By TODAYS_DEALS_LINK = new By.ByLinkText("Today's Deals");
	private static final By GIFT_CARDS_LINK = new By.ByLinkText("Gift Cards");
	private static final By HUNDRED_TO_200_LINK = new By.ByLinkText("$100 to $200");
	private static final By FIRST_LIGHTNING_DEAL = new By.ByXPath("(//*[@id='dealActionButton']/img[@title='Add to cart'])[1]");
	private static final By SIGN_IN_FORM_BY_ID = new By.ById("ap_signin_form");
	
	private static final By EMAIL_OPTION = new By.ByXPath("(//*[@id='bestRefinement']/a)[1]/div/div/span");
	private static final By MAIL_OPTION = new By.ByXPath("(//*[@id='bestRefinement']/a)[2]/div/div/span");
	private static final By PRINT_OPTION = new By.ByXPath("(//*[@id='bestRefinement']/a)[3]/div/div/span");
	
	private static final By AMAZON_LOGO = new By.ById("nav-logo");
	private static final By AMAZON_CART_COUNT = new By.ById("nav-cart-count");
	private static final By AMAZON_CART = new By.ById("nav-cart");

	private static final String TODAYS_DEALS_INNERHTML = "Today's Deals.";// contains non standard characters
	private static final By TODAYS_DEALS_TEXT = new By.ByXPath("//div[@class='gbh1-bold']");
	private static final By ADD_TO_CART_BUTTON = new By.ById("dealActionButton");
	
	
	
	
	
	// dataProvider key word does a lot of thing in testng.
	// dataProvider runs the test for each item in the multidimensional array
	@Test( dataProvider= DATA_PROVIDER)
	public void sampleTest(DriverGenerator dg){
		
		WebDriver wd = dg.generate();
		ParallelPrepHelper pph = new ParallelPrepHelper(wd);

		// When I click on add to cart I am prompted to create an account or log in.
		// use a lightning deal for desired behavior
		
		// go to todays deals
		pph.goToURL(AMAZON_HOME_PAGE);
		pph.visibilityPageClickerWithBy(TODAYS_DEALS_LINK);
		
		// click on first add to cart (lightning dea onlyl)
		pph.visibilityPageClickerWithBy(FIRST_LIGHTNING_DEAL);
		
		// check for Sign In
		Assert.assertTrue(pph.checkForDisplayedWithBy(SIGN_IN_FORM_BY_ID));

		wd.quit();
	}
	
	@Test( dataProvider= DATA_PROVIDER)
	public void giftClickTest(DriverGenerator dg){
		
		WebDriver wd = dg.generate();
		ParallelPrepHelper pph = new ParallelPrepHelper(wd);

		// When I click Gift cards and then click the 
		// $100-$200 link I can select as delivery type "Email", "Mail" and "Print at Home"
		
		// go to home
		pph.goToURL(AMAZON_HOME_PAGE);
		// click on gift cards link
		pph.visibilityPageClickerWithBy(GIFT_CARDS_LINK);
		// click on price 100 to 200 link
		pph.visibilityPageClickerWithBy(HUNDRED_TO_200_LINK);
		// email, mail, and printathome delivery types are present
		
		Assert.assertEquals("E-mail", pph.getInnerHTML(EMAIL_OPTION));

		wd.quit();
	}
	
	@Test( dataProvider= DATA_PROVIDER)
	public void emptyTest(DriverGenerator dg){
		
		WebDriver wd = dg.generate();
		ParallelPrepHelper pph = new ParallelPrepHelper(wd);

		

		wd.quit();
	}
	
	@Test( dataProvider= DATA_PROVIDER)
	public void newUserAttemptTest(DriverGenerator dg){
		
		WebDriver wd = dg.generate();

		LoginPage loginPage = new LoginPage(wd); // make page object
		loginPage.goToURL(loginPage.MAIN_URL);	//	go to amazon
		loginPage.visibilityPageClicker(loginPage.ACCOUNT_XPATH);	// click on account
		loginPage.populateInput(loginPage.LOGIN_XPATH, loginPage.generateRandomEmailAddress());	// assumes email address does not exist already
		loginPage.visibilityPageClicker(loginPage.NEW_CUSTOMER_RADIO_BUTTON); // select new customer radio button
		loginPage.visibilityPageClicker(loginPage.SIGN_IN_BUTTON);	// click on sign in button
		loginPage.populateInput(loginPage.USER_NAME_FIELD, loginPage.generateRandomString());
		loginPage.populateInput(loginPage.EMAIL_CONFIRMATION_XPATH, loginPage.emailAddress);

		wd.quit();
	}
	
	@Test( dataProvider= DATA_PROVIDER)
	public void findShoesTest(DriverGenerator dg){
		
		WebDriver wd = dg.generate();

		PageObject pageObject = new PageObject(wd);
		pageObject.goToURL(PageObject.MAIN_URL);
		pageObject.populateAutoSuggestInput(pageObject.SEARCH_FEILD_XPATH, "shoes", "html/body/header/div/div[2]/div[2]/div/form/div[1]/div/div/div/div/div[2]");
		
		By TEMP_BY = new By.ByXPath(pageObject.SEARCH_FEILD_XPATH);//TODO clean this up
		
		pageObject.hitEnter(TEMP_BY);

		wd.quit();
	}
	
	@Test( dataProvider= DATA_PROVIDER)
	public void renderedPageTest(DriverGenerator dg){
		
		WebDriver wd = dg.generate();
		LoggedOutPage pph = new LoggedOutPage(wd);
		// Initial page navigation takes you to a properly rendered amazon.com page.
		pph.goToURL(AMAZON_HOME_PAGE);
		Assert.assertTrue(pph.checkForDisplayedWithBy(AMAZON_LOGO));

		wd.quit();
	}
	
	@Test( dataProvider= DATA_PROVIDER)
	public void cartZeroTest(DriverGenerator dg){
		
		WebDriver wd = dg.generate();
		LoggedOutPage pph = new LoggedOutPage(wd);
		// Shopping cart is displayed with 0 items when I have not added an item to my shopping cart.
		pph.goToURL(AMAZON_HOME_PAGE);
		Assert.assertEquals("0", pph.getInnerHTML(AMAZON_CART_COUNT));

		wd.quit();
	}
	
	@Test( dataProvider= DATA_PROVIDER)
	public void todaysDealsTest(DriverGenerator dg){
		
		WebDriver wd = dg.generate();
		LoggedOutPage pph = new LoggedOutPage(wd);
		// When I click on Today's Deals I am taken to the daily deals. (from the home page right?)
		pph.goToURL(AMAZON_HOME_PAGE);
		pph.visibilityPageClickerWithBy(TODAYS_DEALS_LINK);
		Assert.assertEquals(TODAYS_DEALS_INNERHTML, pph.getInnerHTML(TODAYS_DEALS_TEXT));
		
		wd.quit();
	}
	
	@Test( dataProvider= DATA_PROVIDER)
	public void todaysDealsAddToCartTest(DriverGenerator dg){
		
		WebDriver wd = dg.generate();
		LoggedOutPage pph = new LoggedOutPage(wd);
		// When I click on Today's Deals there is a button to add items to my cart for some items.
		pph.goToURL(AMAZON_HOME_PAGE);
		pph.visibilityPageClickerWithBy(TODAYS_DEALS_LINK);
		Assert.assertTrue(pph.countNumberOfOccurances(ADD_TO_CART_BUTTON) > 0);
		
		wd.quit();
	}
	
	@Test( dataProvider= DATA_PROVIDER)
	public void bugJonTest(DriverGenerator dg){
		
		WebDriver wd = dg.generate();
		LoggedOutPage pph = new LoggedOutPage(wd);
		// When I click on Today's Deals there is a button to add items to my cart for some items.
		for (int x = 0; x < 2; x++){
		pph.goToURL("http://www.omfgdogs.com/");
		}
		
		//wd.quit();
	}
}

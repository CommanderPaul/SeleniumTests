package amazonTests;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import amazonTests.LoggedOutPage;
import pageObjects.LoginPage;
import amazonTests.PageObject;
import pageObjects.ParallelPrepHelper;
import utility.DriverGenerator;

/**
 * dataProvider key word does a lot of thing in testng. 
 * dataProvider runs the test for each item in the multidimensional array
 * @author pwroe
 *
 */
public class ParallelPrepTest extends BaseTestClass {
	//TODO sort, organize, and move constants
	//TODO abstract strings from Bys
	private static final By TODAYS_DEALS_LINK = new By.ByLinkText("Today's Deals");
	private static final By CONTACT_US_LINK = new By.ByXPath("//*[@id='displayLink']/a");
	
	private static final By HOME_PAGE_CLICK_TO_GET_TO_SIGN_IN_FORM = new By.ById("nav-your-account");
	private static final By SIGN_IN_FORM_SUBMIT_BUTTON = new By.ById("signInSubmit-input");
	
	private static final String VALID_EMAIL_ADDRESS = "Larry@gmail.com";
	
	private static final String INVALID_PASSWORD = "password";
	
	public static final By ERROR = By.id("message_error");
	
	public static final By EMAIL_FIELD = By.id("ap_email");
	public static final By PASSWORD_FIELD = By.id("ap_password");
	
	private static final int NUMBER_OF_HELP_TOPICS = 11;
	private static final By GIFT_CARDS_LINK = new By.ByLinkText("Gift Cards");
	private static final By HUNDRED_TO_200_LINK = new By.ByXPath("html/body/div[3]/div/div[4]/div/div[2]/div/ul[9]/li[4]/a");//TODO fix brittleness
	private static final By HELP_LINK = new By.ByLinkText("Help");
	
	private static final By HELP_TOPICS = new By.ByXPath("//*[@id='other-areas-content']//li");
	
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

	
	/**
	 * Test Number 1, Priority, Blocker.
	 * Initial page navigation takes you to a properly rendered amazon.com page.
	 * runs ok
	 * @param dg
	 */
	//@Test(dataProvider = DATA_PROVIDER)
	public void renderedPageTest(DriverGenerator dg) {
		// boiler plate
		WebDriver wd = dg.generate();
		LoggedOutPage pph = new LoggedOutPage(wd);
		// setup
		boolean madeItToPage = pph.goToURL(AMAZON_HOME_PAGE);
		// assertion(s)
		if (madeItToPage) {
			try {
				Assert.assertTrue(pph.isDisplayed(AMAZON_LOGO));
			} finally {
				wd.quit();
			}
		} else {
			failSetupAndCloseBrowser(wd);
		}
	}
	
	/**
	 * Test Number 2, Priority, Blocker.
	 * Shopping cart is displayed with 0 items
	 * when I have not added an item to my shopping cart.
	 * runs ok
	 * @param dg
	 */
	//@Test(dataProvider = DATA_PROVIDER)
	public void cartZeroTest(DriverGenerator dg) {
		// boiler plate
		WebDriver wd = dg.generate();
		LoggedOutPage pph = new LoggedOutPage(wd);
		// setup
		boolean madeItToPage = pph.goToURL(AMAZON_HOME_PAGE);
		// assertions
		if (madeItToPage) {
			try {
				Assert.assertEquals("0", pph.getInnerHTML(AMAZON_CART_COUNT));
			} finally {
				wd.quit();
			}
		} else {
			failSetupAndCloseBrowser(wd);
		}
	}
	
	/**
	 * Test Number 3, Priority, Blocker.
	 * When I click on Today's Deals I am taken to the daily deals. (from the home page)
	 * runs ok
	 * @param dg
	 */
	//@Test( dataProvider= DATA_PROVIDER)
	public void todaysDealsTest(DriverGenerator dg){
		// boiler plate
		WebDriver wd = dg.generate();
		LoggedOutPage pph = new LoggedOutPage(wd);
		// setup
		boolean madeItToPage = pph.goToURL(AMAZON_HOME_PAGE);
		boolean foundAndClickedOnElement = pph.clickOnVisibleElement(TODAYS_DEALS_LINK);
		// assertions
		if (madeItToPage && foundAndClickedOnElement){
			try{
				Assert.assertEquals(TODAYS_DEALS_INNERHTML, pph.getInnerHTML(TODAYS_DEALS_TEXT));				
			}finally{
				wd.quit();
			}
		}else{
			failSetupAndCloseBrowser(wd);
		}
	}

	/**
	 * Test Number 4, Priority, Blocker.
	 * When I click on Today's Deals there is
	 * a button to add items to my cart for some items.
	 * runs ok
	 * @param dg
	 */
	//@Test(dataProvider = DATA_PROVIDER)
	public void todaysDealsAddToCartTest(DriverGenerator dg) {
		// boiler plate
		WebDriver wd = dg.generate();
		LoggedOutPage pph = new LoggedOutPage(wd);
		// setup;
		boolean madeItToPage = pph.goToURL(AMAZON_HOME_PAGE);
		boolean foundAndClickedOnElement = pph.clickOnVisibleElement(TODAYS_DEALS_LINK);
		// assertions
		if (madeItToPage && foundAndClickedOnElement){
			try{
				Assert.assertTrue(pph.countNumberOfOccurances(ADD_TO_CART_BUTTON) > 0);				
			}finally{
				wd.quit();				
			}
		}else{
			failSetupAndCloseBrowser(wd);
		}
	}
	
	/**
	 * Test Number 5, Priority, Blocker.
	 * When I click on add to cart I am prompted to create an account or log in.
	 * using a lightning deal for desired behavior check
	 * clicking on first add to cart lightning deal
	 * 
	 * test fails because amazon does not prompt you to create an account after
	 * adding to cart
	 * 
	 * @param dg
	 */
	//@Test( dataProvider= DATA_PROVIDER)
	public void addToCartHasCreateAccount(DriverGenerator dg){
		// boiler plate
		WebDriver wd = dg.generate();
		ParallelPrepHelper pph = new ParallelPrepHelper(wd);
		// setup
		boolean madeItToPage = pph.goToURL(AMAZON_HOME_PAGE);
		boolean foundAndClickedOnElementOne = pph.clickOnVisibleElement(TODAYS_DEALS_LINK);
		boolean foundAndClickedOnElementTwo = pph.clickOnVisibleElement(FIRST_LIGHTNING_DEAL);
		// assertions
		if (madeItToPage && foundAndClickedOnElementOne && foundAndClickedOnElementTwo){
			try {
				Assert.assertTrue(pph.isDisplayed(SIGN_IN_FORM_BY_ID));
			} finally {
				wd.quit();
			}
		}else{
			failSetupAndCloseBrowser(wd);
		}
	}
	
	/**
	 * Test Number 6, Priority, Blocker When I click Gift cards and then click
	 * the $100-$200 link I can select as delivery type "Email", "Mail" and
	 * "Print at Home"
	 * runs ok
	 * @param dg
	 */
	//@Test(dataProvider = DATA_PROVIDER)
	public void giftClickTest(DriverGenerator dg) {
		// boiler plate
		WebDriver wd = dg.generate();
		ParallelPrepHelper pph = new ParallelPrepHelper(wd);
		// setup
		boolean madeItToPage = pph.goToURL(AMAZON_HOME_PAGE);
		boolean giftCardLink = pph.clickOnVisibleElement(GIFT_CARDS_LINK);
		boolean priceRangeLink = pph.clickOnVisibleElement(HUNDRED_TO_200_LINK);
		// assertions
		if (madeItToPage && giftCardLink && priceRangeLink) {
			try {
				Assert.assertEquals("E-mail", pph.getInnerHTML(EMAIL_OPTION));
				Assert.assertEquals("Mail", pph.getInnerHTML(MAIL_OPTION));
				Assert.assertEquals("Print at Home",
						pph.getInnerHTML(PRINT_OPTION));
			} finally {
				wd.quit();
			}
		} else {
			failSetupAndCloseBrowser(wd);
		}
	}
	
	/**
	 * Test Number 7, Priority, Blocker. 
	 * When I click on Help (from the home page) I am presented with 11 Topic links to click on.
	 * runs ok
	 * @param dg
	 */
	// @Test(dataProvider = DATA_PROVIDER)
	public void checkForHelpTopics(DriverGenerator dg) {
		// boiler plate
		WebDriver wd = dg.generate();
		ParallelPrepHelper pph = new ParallelPrepHelper(wd);
		// setup
		boolean madeItToPage = pph.goToURL(AMAZON_HOME_PAGE);
		boolean helpLink = pph.clickOnVisibleElement(HELP_LINK);
		int numberOfHelpTopicsFound = pph.countNumberOfOccurances(HELP_TOPICS);
		// assertions
		if (madeItToPage && helpLink && (numberOfHelpTopicsFound > 0)) {
			try {
				Assert.assertEquals(NUMBER_OF_HELP_TOPICS,
						numberOfHelpTopicsFound);
			} finally {
				wd.quit();//TODO wrap with a check for null / or replace with method that checks for null
			}
		} else {
			failSetupAndCloseBrowser(wd);//TODO check that driver is not null before quitting
		}
	}
	
	/**
	 * Test Number 8, Priority, Blocker.
	 * When I click on Help and then contact us I am prompted for login.
	 * runs ok
	 * @param dg
	 */
	//@Test(dataProvider = DATA_PROVIDER)
	public void helpContactPromptsForLogin(DriverGenerator dg){
		// boiler plate
		WebDriver wd = dg.generate();
		ParallelPrepHelper pph = new ParallelPrepHelper(wd);
		// setup
		boolean madeItToPage = pph.goToURL(AMAZON_HOME_PAGE);
		boolean helpLink = pph.clickOnVisibleElement(HELP_LINK);
		boolean contactUsLink = pph.clickOnVisibleElement(CONTACT_US_LINK);
		//TODO checking to see if 'I am on the sign in page' can be abstracted out.
		// assertions
		if (madeItToPage && helpLink && contactUsLink){
			try {
				Assert.assertTrue(pph.isDisplayed(SIGN_IN_FORM_BY_ID));
			} finally {
				wd.quit();
			}
		} else {
			failSetupAndCloseBrowser(wd);
		}
	}
	
	/**
	 * Test Number 9, Priority, Blocker
	 * When I attempt to log in and only enter my email 
	 * address and click submit I am presented with an error 
	 * message prompting me to enter my password.
	 * runs ok
	 * @param dg
	 */
	//@Test(dataProvider = DATA_PROVIDER)
	public void loginWithEmailAndNotPassword(DriverGenerator dg) {
		// boiler plate
		WebDriver wd = dg.generate();
		ParallelPrepHelper pph = new ParallelPrepHelper(wd);
		// setup
		boolean madeItToPage = pph.goToURL(AMAZON_HOME_PAGE);
		boolean clickOnSignIn = pph.clickOnVisibleElement(HOME_PAGE_CLICK_TO_GET_TO_SIGN_IN_FORM);
		boolean enterEmailAddress = pph.populateInput(EMAIL_FIELD, VALID_EMAIL_ADDRESS);
		boolean clickOnSignInTwo = pph.clickOnVisibleElement(SIGN_IN_FORM_SUBMIT_BUTTON);
		// assertions
		if (madeItToPage && clickOnSignIn && enterEmailAddress && clickOnSignInTwo) {
			try {
				Assert.assertTrue(pph.isDisplayed(ERROR));
			} finally {
				wd.quit();
			}
		} else {
			failSetupAndCloseBrowser(wd);
		}
	}
	
	/**
	 * Test Number 10, Priority, Blocker.
	 * When I attempt to log in using the wrong password I am presented with an error 
	 * message telling me that there was a problem with my email/password combination 
	 * and to try again.
	 * Ammendium:  Check for captcha instead
	 * @param dg
	 */
	@Test(dataProvider = DATA_PROVIDER)
	public void wrongPasswordAndValidAccountEmail(DriverGenerator dg){
		// boiler plate
		WebDriver wd = dg.generate();
		ParallelPrepHelper pph = new ParallelPrepHelper(wd);
		// setup
		boolean madeItToPage = pph.goToURL(AMAZON_HOME_PAGE);
		boolean clickOnSignIn = pph.clickOnVisibleElement(HOME_PAGE_CLICK_TO_GET_TO_SIGN_IN_FORM);
		boolean enterEmailAddress = pph.populateInput(EMAIL_FIELD, VALID_EMAIL_ADDRESS);
		boolean enterPassword = pph.populateInput(PASSWORD_FIELD, INVALID_PASSWORD);
		boolean clickOnSignInTwo = pph.clickOnVisibleElement(SIGN_IN_FORM_SUBMIT_BUTTON);
		// assertions
		if (madeItToPage && clickOnSignIn && enterEmailAddress && enterPassword && clickOnSignInTwo) {
			try {
				Assert.assertTrue(pph.isDisplayed(ERROR));
			} finally {
				wd.quit();
			}
		} else {
			failSetupAndCloseBrowser(wd);
		}
		
		
		
		
	}
	
	
	
	//@Test( dataProvider= DATA_PROVIDER)
	public void newUserAttemptTest(DriverGenerator dg){
		
		WebDriver wd = dg.generate();

		LoginPage loginPage = new LoginPage(wd); // make page object
		loginPage.goToURL(LoginPage.MAIN_URL);	//	go to amazon
		loginPage.clickOnVisibleElement(LoginPage.ACCOUNT);	// click on account
		loginPage.populateInput(LoginPage.LOGIN, loginPage.generateRandomEmailAddress());	// assumes email address does not exist already
		loginPage.clickOnVisibleElement(LoginPage.NEW_CUSTOMER_RADIO_BUTTON); // select new customer radio button
		loginPage.clickOnVisibleElement(LoginPage.SIGN_IN_BUTTON);	// click on sign in button
		loginPage.populateInput(LoginPage.USER_NAME_FIELD, loginPage.generateRandomString());
		loginPage.populateInput(LoginPage.EMAIL_CONFIRMATION, loginPage.emailAddress);

		wd.quit();
	}
	
	//@Test( dataProvider= DATA_PROVIDER)
	public void findShoesTest(DriverGenerator dg){
		
		WebDriver wd = dg.generate();

		PageObject pageObject = new PageObject(wd);
		pageObject.goToURL(PageObject.MAIN_URL);
		pageObject.populateAutoSuggestInput(PageObject.SEARCH_FEILD, "shoes", PageObject.SEARCH_FIELD_SUGGESTION);
		
		By TEMP_BY = new By.ByXPath(PageObject.SEARCH_FEILD_XPATH);//TODO clean this up
		
		pageObject.hitEnter(TEMP_BY);

		wd.quit();
	}
	
	
	

	

	
	
	

	
//	@Test( dataProvider= DATA_PROVIDER)
//	public void bugJonTest(DriverGenerator dg){
//		
//		WebDriver wd = dg.generate();
//		LoggedOutPage pph = new LoggedOutPage(wd);
//		
//		for (int x = 0; x < 2; x++){
//		pph.goToURL("http://www.omfgdogs.com/");
//		}
//		
//		//wd.quit();
//	}
}

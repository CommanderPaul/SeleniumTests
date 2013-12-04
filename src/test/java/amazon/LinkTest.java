package amazon;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import excelTests.BaseTestClass;
import pageObjects.LoginPage;
import pageObjects.PageObject;

public class LinkTest extends BaseTestClass{

	
	
	@Test
	public void testScreenShotLoginError(){
		
		LoginPage pageObject;
		
		pageObject = new LoginPage(new ChromeDriver());
		screenShotLoginError(pageObject);
		
		pageObject = new LoginPage(new FirefoxDriver());
		screenShotLoginError(pageObject);
	}
	
	private void screenShotLoginError(LoginPage pageObject){

		pageObject.goToURL(pageObject.MAIN_URL);// go to amazon
		pageObject.visibilityPageClicker(pageObject.ACCOUNT_XPATH);	// click on account
		
		pageObject.populateInput(pageObject.LOGIN_XPATH, pageObject.generateRandomString());//generateRandomString
		pageObject.visibilityPageClicker(pageObject.SIGN_IN_BUTTON);	// click on sign in button
		
		
		// take screenshot if error message is present

		if (pageObject.checkForPresence(pageObject.ERROR_XPATH)){
			System.out.println("error");
			
			pageObject.takeScreenshot();
		}
		
	}
	
	
	@Test
	public void testCartLink() {
		
		PageObject pageObject;
		pageObject = new PageObject(new ChromeDriver());
		actualCartLinkTest(pageObject);
		pageObject = new PageObject(new FirefoxDriver());
		actualCartLinkTest(pageObject);
	}
	
	private void actualCartLinkTest(PageObject pageObject){
		pageObject.goToURL(PageObject.MAIN_URL);
		String linkString = pageObject.getAttributeValue(pageObject.CART_LINK, "href");
		pageObject.visibilityPageClicker(pageObject.CART_LINK);// follow link
		//assertTrue("linkString should match current url",linkString.equals(pageObject.getCurrentURL()));
	}

	@Test
	public void testFindShoes(){
		// test has css issues
		PageObject pageObject = new PageObject(new FirefoxDriver());
		pageObject.goToURL(PageObject.MAIN_URL);
		pageObject.populateAutoSuggestInput(pageObject.SEARCH_FEILD_XPATH, "shoes", "html/body/header/div/div[2]/div[2]/div/form/div[1]/div/div/div/div/div[2]");
		pageObject.hitEnter(pageObject.SEARCH_FEILD_XPATH);
	}
	
	
	@Test
	public void testNewUserAttempt(){

		LoginPage loginPage = new LoginPage(new FirefoxDriver()); // make page object
		loginPage.goToURL(loginPage.MAIN_URL);	//	go to amazon
		loginPage.visibilityPageClicker(loginPage.ACCOUNT_XPATH);	// click on account
		loginPage.populateInput(loginPage.LOGIN_XPATH, loginPage.generateRandomEmailAddress());	// assumes email address does not exist already
		loginPage.visibilityPageClicker(loginPage.NEW_CUSTOMER_RADIO_BUTTON); // select new customer radio button
		loginPage.visibilityPageClicker(loginPage.SIGN_IN_BUTTON);	// click on sign in button
		loginPage.populateInput(loginPage.USER_NAME_FIELD, loginPage.generateRandomString());
		loginPage.populateInput(loginPage.EMAIL_CONFIRMATION_XPATH, loginPage.emailAddress);
		
		
		
	}

}

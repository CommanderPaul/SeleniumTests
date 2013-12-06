package oldTests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import utility.TakeScreenshot;
import amazonTests.PageObject;

public class LinkTest extends amazonTests.BaseTestClass{

	
	
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
			
			//pageObject.takeScreenshot();
			TakeScreenshot.captureImage(pageObject.getWebDriver());
			
		}
		
	}
	
	
	//@Test
	public void testCartLink() {
		
		PageObject pageObject;
		pageObject = new PageObject(new ChromeDriver());
		actualCartLinkTest(pageObject);
		pageObject.closeDriver();
		pageObject = new PageObject(new FirefoxDriver());
		actualCartLinkTest(pageObject);
		pageObject.closeDriver();
	}
	
	private void actualCartLinkTest(PageObject pageObject){
		pageObject.goToURL(PageObject.MAIN_URL);
		String linkString = pageObject.getAttributeValue(pageObject.CART_LINK, "href");
		pageObject.visibilityPageClicker(pageObject.CART_LINK);// follow link
		//assertTrue("linkString should match current url",linkString.equals(pageObject.getCurrentURL()));
	}




}

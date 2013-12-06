package oldTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import utility.DriverGenerator;
import utility.TakeScreenshot;
import amazonTests.LoggedOutPage;
import amazonTests.PageObject;

public class LinkTest extends amazonTests.BaseTestClass{

	
	
	//@Test
	public void testScreenShotLoginError(){
		
		LoginPage pageObject;
		
		pageObject = new LoginPage(new ChromeDriver());
		screenShotLoginError(pageObject);
		
		pageObject = new LoginPage(new FirefoxDriver());
		screenShotLoginError(pageObject);
	}
	
	private void screenShotLoginError(LoginPage pageObject){

		pageObject.goToURL(pageObject.MAIN_URL);// go to amazon
		pageObject.clickOnVisibleElement(pageObject.ACCOUNT);	// click on account
		
		pageObject.populateInput(pageObject.LOGIN, pageObject.generateRandomString());//generateRandomString
		pageObject.clickOnVisibleElement(pageObject.SIGN_IN_BUTTON);	// click on sign in button
		
		
		// take screenshot if error message is present

		if (pageObject.isDisplayed(pageObject.ERROR)){
			//System.out.println("error");
			//TODO log failure or Assert
			//pageObject.takeScreenshot();
			TakeScreenshot.captureImage(pageObject.getWebDriver());
			
		}
		
	}
	
	
	
	
	
	//@Test( dataProvider= DATA_PROVIDER)
	public void renderedPageTest(DriverGenerator dg){
		
		WebDriver wd = dg.generate();
		PageObject pph = new PageObject(wd);//TODO move to it's own child object
		
		pph.goToURL(PageObject.MAIN_URL);

		String linkString = pph.getAttributeValue(pph.CART_LINK_BY, "href");
		
		pph.clickOnVisibleElement(pph.CART_LINK_BY);// follow link
		Assert.assertTrue(linkString.equals(pph.getCurrentURL()),"linkString should match current url");

		wd.quit();
	}
	
}

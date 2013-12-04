package parallelPrep;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import excelTests.LoggedOutPage;
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
	
	
	
	
}

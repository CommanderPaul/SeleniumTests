package amazonTests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.LoginPage;

public class PageObject {
	// urls
	public static final String MAIN_URL = "http://www.amazon.com";
	// By
	public static final String CART_LINK = "//a[@id='nav-cart']";
	public static final By CART_LINK_BY = By.id("nav-cart");
	public static final String ACCOUNT_XPATH = "//a[@id='nav-your-account']";
	public static final By ACCOUNT = By.id("nav-your-account");
	
	public static final String SEARCH_FEILD_XPATH = "//input[@id='twotabsearchtextbox']";
	public static final By SEARCH_FEILD = By.id("twotabsearchtextbox");
	public static final By SEARCH_FIELD_SUGGESTION = By.xpath("html/body/header/div/div[2]/div[2]/div/form/div[1]/div/div/div/div/div[2]");
	
	public static final String SIGN_IN_BUTTON = "//input[@id='signInSubmit-input']";
	public static final By LOGIN = By.id("ap_email");
	public static final String ERROR_XPATH = "//div[@id='message_error']";
	public static final By ERROR = By.id("message_error");
	
	// objects
	private WebDriver driver;
	private WebDriverWait wait;
	
	
	public PageObject(WebDriver driver){
		// constructor gets it's own WebDriver
		
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 20);
	}
	
	public WebDriver getWebDriver(){
		return driver;
	}
	
	public int countNumberOfOccurances(By by){
		return driver.findElements(by).size();
	}
	
	public boolean isDisplayed(By by){
		boolean returnValue = false;
		try {
			returnValue = wait.until(ExpectedConditions.visibilityOfElementLocated(by )).isDisplayed();
		} catch (Exception e) {
			// take screen shot and log error
			// print message for now
			//TODO also need to close browser!!!
			System.out.println(" syso message " + e.getMessage());
		}
		return returnValue;
		
	}
	
	public void populateInput(By by, String input){
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by)).sendKeys(input);
		} catch (Exception e) {
			// take screen shot and log error
			// print message for now
			//TODO also need to close browser!!!
			System.out.println(" syso message " + e.getMessage());
		}
	}
	
	public void populateAutoSuggestInput(By by, String input, By by2){
		//TODO this function seems to have problems

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by)).sendKeys(input);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by2));// suggestion
		} catch (Exception e) {
			// take screen shot and log error
			// print message for now
			//TODO also need to close browser!!!
			System.out.println(" syso message " + e.getMessage());
		}
	}
	
	public boolean goToURL(String url) {
		boolean returnValue = false;

		try {
			driver.get(url);//TODO use wait object
			driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);

			if (getCurrentURL().contains(url)) {// confirm url
				returnValue = true;
			}

		} catch (Exception e) {
			// take screen shot and log error
			// print message for now
			System.out.println(" syso message goToURL method" + e.getMessage());
		}

		return returnValue;
	}
		
	public boolean clickOnVisibleElement(By by) {
		boolean returnValue = false;
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by))
					.click();
			returnValue = true;
		} catch (Exception e) {
			//TODO take screen shot and log error
			// print message for now
			//webDriver.close(); // is this a good place to force close?
			System.out.println(" Error message for clickOnVisibleElement method.  Couldn't find " 
			+ by.toString() + " " + e.getMessage());
		}
		return returnValue;
	}
	
	public String getInnerHTML(By by) {
		String returnString = "Text not found, check your XPATH";// don't return null, breaks other things

		try {
			returnString = wait.until(
					ExpectedConditions.visibilityOfElementLocated(by))
					.getText();
		} catch (Exception e) {
			// take screen shot and log error
			// print message for now
			System.out.println(" syso message getInnerHTML method " + e.getMessage());
		}
		return returnString;
	}
	
	public void closeDriver(){
		driver.close();
	}

	public String getAttributeValue(By by, String attribute) {
		String returnString = null;
		try {
			returnString = wait.until(
					ExpectedConditions.visibilityOfElementLocated(by))
					.getAttribute(attribute);
		} catch (Exception e) {
			// take screen shot and log error
			// print message for now
			System.out.println(" syso message " + e.getMessage());
		}
		return returnString;
	}

	public String getCurrentURL(){//TODO add try catch
		return driver.getCurrentUrl();
	}

	public void hitEnter(By by){
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by)).sendKeys(Keys.RETURN);
		} catch (Exception e) {
			// take screen shot and log error
			// print message for now
			System.out.println(" syso message " + e.getMessage());
		}
	}
	
	
	public int getWindowHeight(){
		return driver.manage().window().getSize().getHeight();
	}
	public int getWindowWidth(){
		return driver.manage().window().getSize().getWidth();
	}
	
	public void setWindowHeight(int height){
		Dimension dim = new Dimension(getWindowWidth(), height);
		driver.manage().window().setSize(dim);
	}
	public void setWindowWidth(int width){
		Dimension dim = new Dimension(width, getWindowHeight());
		driver.manage().window().setSize(dim);
	}
	
}

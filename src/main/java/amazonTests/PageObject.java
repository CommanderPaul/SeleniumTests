package amazonTests;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObject {
	// urls
	public static final String MAIN_URL = "http://www.amazon.com";
	// xpaths
	public static final String CART_LINK = "//a[@id='nav-cart']";
	public static final String ACCOUNT_XPATH = "//a[@id='nav-your-account']";
	public static final String SEARCH_FEILD_XPATH = "//input[@id='twotabsearchtextbox']";
	public static final String SIGN_IN_BUTTON = "//input[@id='signInSubmit-input']";
	public static final String LOGIN_XPATH = "//input[@id='ap_email']";
	public static final String ERROR_XPATH = "//div[@id='message_error']";
	
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
	
	public boolean checkForPresence(String xpath){
		return driver.findElement(By.xpath(xpath)).isDisplayed();
	}
	
	public boolean checkForDisplayedWithBy(By by){
		return wait.until(ExpectedConditions.visibilityOfElementLocated(by )).isDisplayed();
	}
	
	
	public void populateInput(String xpath, String input){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(input);
	}
	
	public void populateAutoSuggestInput(String xpath, String input, String xpath2){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(input);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath2)));// suggestion
		
	}
	
	public void goToURL(String url) {
		try {
			driver.get(url);
		} catch (Exception e) {
			// take screen shot and log error
			// print message for now
			System.out.println(" syso message " + e.getMessage());
		}
	}
	
	public void visibilityPageClicker(String xpath) {
		try {
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(By
							.xpath(xpath))).click();
		} catch (Exception e) {
			// take screen shot and log error
			// print message for now
			System.out.println(" syso message " + e.getMessage());
		}
	}
	
	public void visibilityPageClickerWithBy(By by) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by))
					.click();
		} catch (Exception e) {
			// take screen shot and log error
			// print message for now
			System.out.println(" syso message " + e.getMessage());
		}
	}
	
	public String getInnerHTML(By by){
		return wait.until(
				ExpectedConditions.visibilityOfElementLocated(by))
				.getText();
	}
	
	public void closeDriver(){
		driver.close();
	}
	
	public String getAttributeValue(String xpath, String attribute){
		return driver.findElement(By.xpath(xpath)).getAttribute(attribute);

	}
	public String getCurrentURL(){
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

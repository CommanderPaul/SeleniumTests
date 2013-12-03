package amazon;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
		this.wait = new WebDriverWait(driver, 2000);
	}
	
	
	public boolean checkForPresence(String xpath){
		return driver.findElement(By.xpath(xpath)).isDisplayed();
	}
	
	public void takeScreenshot() {

		/*
		 * Next line makes the browser wait for 7 seconds before declaring it
		 * cant find an element. Good for slow loading websites
		 */
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		// driver.get("http://www.amazon.com");// should already be at desired
		// page

		// System.out.println("Taking Screen Shot");
		File screenshot = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils
					.copyFile(screenshot, new File("c:\\test\\screenshot.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.quit();
	}
	
	
	public void populateInput(String xpath, String input){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(input);
	}
	
	public void populateAutoSuggestInput(String xpath, String input, String xpath2){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(input);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath2)));// suggestion
		
	}
	
	public void goToURL(String url){
		driver.get(url);
	}
	
	public void visibilityPageClicker(String xpath){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	}
	
	public String getAttributeValue(String xpath, String attribute){
		return driver.findElement(By.xpath(xpath)).getAttribute(attribute);

	}
	public String getCurrentURL(){
		return driver.getCurrentUrl();
	}
	
	public void hitEnter(String xpath){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.RETURN);;
	}
	
}

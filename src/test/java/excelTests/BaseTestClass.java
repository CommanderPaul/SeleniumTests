package excelTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;



/**
 * Parent Test Class 
 * @author pwroe
 *
 */
public class BaseTestClass {

	public static final String PATH_TO_CHROMEDRIVER_EXECUTABLE = "C:\\Windows\\chromedriver.exe";
	public static final String PATH_TO_CHROME_WEBBROWSER = "C:\\'Program Files (x86)'\\Google\\Chrome\\Application\\chrome.exe";
	
	public WebDriver chromeThing;
	public WebDriver firefoxThing;
	
	@BeforeMethod
	public void setupStuff(){
		System.setProperty(PATH_TO_CHROMEDRIVER_EXECUTABLE, PATH_TO_CHROME_WEBBROWSER);
		
		chromeThing = new ChromeDriver();
		firefoxThing = new FirefoxDriver();
		
	}
	
	@AfterMethod
	public void closeDriver(){
		chromeThing.quit();
		firefoxThing.quit();
	}


}

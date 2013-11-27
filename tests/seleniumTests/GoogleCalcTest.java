package seleniumTests;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GoogleCalcTest {

	private static final String XPATH_TO_BUTTON_ONE = "//div[@id='cwbt33']";
	private static final String XPATH_TO_BUTTON_PI = "//div[@id='cwbt20']";
	private static final String PATH_TO_GOOGLE_CALCULATOR = "https://google.com/#q=calculator";
	private static final String INITIAL_STATE_OF_RESULT_WINDOW = "0";
	private static final String XPATH_TO_RESULT_DIV = "//span[@id='cwos']";

	@Test
	public void testOnePlusOne() {
		// make a new web driver
		WebDriver driver = new FirefoxDriver();
		// go to website
		driver.get(PATH_TO_GOOGLE_CALCULATOR);
		// come up with a test
		// test 1+1=2 , validate as we go

		// put a timer here to make sure the page loads

		// find result window
		WebElement resultWindow = driver.findElement(By
				.xpath(XPATH_TO_RESULT_DIV));
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// confirm initial value of result window
		assertTrue(resultWindow.getText().equals(INITIAL_STATE_OF_RESULT_WINDOW));

		// find 1 button
		WebElement buttonOne = driver.findElement(By
				.xpath(XPATH_TO_BUTTON_ONE));
		// activate the 1 button by mouseclick
		buttonOne.click();
		// assert that 1 was entered result box
		assertTrue(resultWindow.getText().equals("1"));

		// find + button
		// might be a timeout issue
		WebElement buttonPlus = driver.findElement(By
				.xpath("//div[@id='cwbt46']"));
		// activate the plus button by mouseclick
		buttonPlus.click();
		// assert that 1 and + appears in result box
		assertTrue(resultWindow.getText().equals("1 +"));
		
		// press 1 button again and confirm
		buttonOne.click();
		assertTrue(resultWindow.getText().equals("1 + 1"));
		
		// find = button
		WebElement buttonEquals = driver.findElement(By
				.xpath("//div[@id='cwbt45']"));
		
		// press equals
		buttonEquals.click();
		// assert results
		assertTrue(resultWindow.getText().equals("2"));
		

	}

	@Test
	public void testValueOfPi(){
		// go to page
		WebDriver driver = new FirefoxDriver();
		driver.get(PATH_TO_GOOGLE_CALCULATOR);
		

		
		myPreConditions(driver);	// check pre conditions
		
		// button click sequence
		buttonClicker(driver,XPATH_TO_BUTTON_PI );
		// still have to validate manually
		//validateClickedButtonManually();
		
		
		// generate pi from java and compare
		// compare values
		
	}
	
	// compare inputted values in an assert
	private void validateClickedButtonManually(String expected, String actualXpath, WebElement resultWindow){
		String actual = "resultWindow.getText().equals(actualXpath)";
		
		assertTrue(expected.equals(actual));
	}
	private void buttonClicker(WebDriver driver, String xpath){
		
		if (driver.findElement(By.xpath(xpath)).isDisplayed()){
			driver.findElement(By.xpath(xpath)).click();
		}
		
	}
	
	
	private void myPreConditions(WebDriver driver){
		
		// preconditions - check initial state
		WebElement resultWindow = driver.findElement(By.xpath(XPATH_TO_RESULT_DIV));
		assertTrue(resultWindow.getText().equals(INITIAL_STATE_OF_RESULT_WINDOW));
		
	}
	
}

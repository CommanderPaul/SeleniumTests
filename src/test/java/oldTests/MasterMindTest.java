package oldTests;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;


/**
 * Testing for www.web-games-online.com/mastermind/
 * @author pwroe
 *
 */


public class MasterMindTest {

	private static final String SELECT_RED = "//img[@onclick] [@onmouseover][@src = 'images/color_1.gif']";
	private static final String SELECT_GREEN = "//img[@onclick] [@onmouseover][@src = 'images/color_2.gif']";
	private static final String SELECT_BLUE = "//img[@onclick] [@onmouseover][@src = 'images/color_3.gif']";
	private static final String SELECT_YELLOW = "//img[@onclick] [@onmouseover][@src = 'images/color_4.gif']";
	private static final String SELECT_BROWN = "//img[@onclick] [@onmouseover][@src = 'images/color_5.gif']";
	private static final String SELECT_ORANGE = "//img[@onclick] [@onmouseover][@src = 'images/color_6.gif']";
	private static final String SELECT_BLACK = "//img[@onclick] [@onmouseover][@src = 'images/color_7.gif']";
	private static final String SELECT_WHITE = "//img[@onclick] [@onmouseover][@src = 'images/color_8.gif']";
	
	private int boardLength;
	
	//@Test
	public void test() {
		// make a new web driver
		WebDriver driver = new FirefoxDriver();
		// go to website
		driver.get("http://www.web-games-online.com/mastermind");// need http or it will not know what kind of media to expect
		
		// select red
		WebElement buttonOne = driver.findElement(By.xpath(SELECT_RED));
		buttonOne.click();// select red
		
		// put red in first slot
		//WebElement move = driver.findElement(By.xpath("//tr[@id='Row10']/td[4]/a/img"));
		//move.click();
		
		placeMarkers(4,10,SELECT_RED,driver);
		
		hitCheckButton(driver, 10);// row 10
		
		placeMarkers(4, 9,SELECT_GREEN, driver); // populate row 9
		
		hitCheckButton(driver, 9); // row 9
		
		setAndCheckCodeLength(driver);
		
		driver.close();
	}
	
	// checks length and then rotates to next length 4,6,8
	private void setAndCheckCodeLength(WebDriver driver){
		
		// dropdown
		WebElement dropDown = driver.findElement(By.xpath("//select[@name='CodeLength']")); // /option[1]
		// options
		WebElement selection1 = driver.findElement(By.xpath("//select[@name='CodeLength']/option["+1 + "]"));
		WebElement selection2 = driver.findElement(By.xpath("//select[@name='CodeLength']/option["+2 + "]"));
		WebElement selection3 = driver.findElement(By.xpath("//select[@name='CodeLength']/option["+3 + "]"));
		
		if (selection1.isDisplayed()){
			dropDown.click();
			selection2.click();
			dropDown.sendKeys(Keys.RETURN);
			boardLength = 6;// should probably just read grid and get board length
		}else if(selection2.isDisplayed()){
			dropDown.click();
			selection3.click();
			dropDown.sendKeys(Keys.RETURN);
		}else if(selection3.isDisplayed()){
			dropDown.click();
			selection1.click();
			dropDown.sendKeys(Keys.RETURN);
		}
	}
	
	private void readBoardLength(WebDriver driver){
		// return actual board length
		WebElement selection1 = driver.findElement(By.xpath("//select[@name='CodeLength']/option["+1 + "]"));
		//driver.
	}
	
	
	/**
	 * marker number is number of markers, usually 4,6, or 8
	 * row is 10 when starting and goes to one on last move
	 * 	you get 10 guesses
	 * 
	 * @param markerNumber
	 * @param row
	 * @param driver
	 */
	private void placeMarkers(int markerNumber, int row, String color, WebDriver driver){
		// markerNumber stays at 4 for now
		
		for (int x = 1; x <= markerNumber; x++){
			
			// select color - hard coding red for now
			WebElement buttonOne = driver.findElement(By.xpath(randomColorGenerator()));
			buttonOne.click();// select red
			
			// move
			WebElement move = driver.findElement(By.xpath("//tr[@id='Row" + row +"']/td["+ x +"]/a/img"));
			move.click();
		}
		
		
		
		
	}
	
	private void hitCheckButton(WebDriver driver, int row){
		// check button needs to be on current row
		driver.findElement(By.xpath("//tr[@id='Row"+ row +"']//td/input")).click();
	}
	
	// returns random color string
	private String randomColorGenerator(){

		int random = (int) (Math.random() * 8);
		String selectedColor;

		switch (random) {
		case 1:
			selectedColor = SELECT_RED;
			break;
		case 2:
			selectedColor = SELECT_GREEN;
			break;
		case 3:
			selectedColor = SELECT_BLUE;
			break;
		case 4:
			selectedColor = SELECT_YELLOW;
			break;
		case 5:
			selectedColor = SELECT_BROWN;
			break;
		case 6:
			selectedColor = SELECT_ORANGE;
			break;
		case 7:
			selectedColor = SELECT_BLACK;
			break;
		case 8:
			selectedColor = SELECT_RED;
			break;
		default:
			selectedColor = SELECT_WHITE;
			break;
		}
		
		return selectedColor;
	}
	
	
}

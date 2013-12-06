package oldTests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ChainProject {

	//@Test
	public void test() {
		
		// make a new web driver
		WebDriver driver = new FirefoxDriver();
		// go to website
		driver.get("http://en.wikipedia.org/wiki/Norwegian_Lundehund");
		
		
		// click on link
		WebElement buttonOne = driver.findElement(By.xpath("(//a[@href='/wiki/File:Lundehund-labb.JPG'])[2]"));
		buttonOne.click();
		
		WebElement buttonTwo = driver.findElement(By.xpath("//a[@href='/wiki/Norwegian_Lundehund']"));
		buttonTwo.click();
		
		WebElement buttonThree = driver.findElement(By.xpath("//div[@id='p-logo']/a[@href='/wiki/Main_Page']"));
		buttonThree.click();
		
		WebElement buttonFour = driver.findElement(By.xpath("//ul[@id='p-lang-list']//a[@lang='ka']"));
		buttonFour.click();
		
		WebElement buttonFive = driver.findElement(By.xpath("(.//*[@id='mtpad']/div/a/img)[2]"));
		
		
		Assert.assertTrue(buttonFive.isDisplayed());
		
		driver.quit();
	}

}

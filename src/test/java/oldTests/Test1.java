package oldTests;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
/**
 * Because of JUnit's multi-threaded behavior, we should
 * try to keep most instantiations inside of the actual
 * test, otherwise a large number of tests can have 
 * poor performance.
 * @author pwroe
 *
 */
public class Test1 {



	//@Test
	public void test() {
		
		WebDriver driver = new FirefoxDriver();
		// 
		driver.get("https://google.com");
		
		WebElement element = driver.findElement(By.name("q"));
		
		element.sendKeys("Seleniumhq");
		
		element.sendKeys(Keys.RETURN);// can also use element.submit
		
		// use webdriver wait to stall for page to be rendered
		WebDriverWait waiter = new WebDriverWait(driver, 10);
		
		
		waiter.until(ExpectedConditions.textToBePresentInElement(By.xpath(
				"html/body/div[1]/div[4]/div[2]/div[1]/div[6]/div[1]/div[4]/div/div[2]/div[2]/div/ol/li[1]/div/h3/a"), "Selenium"));
		//html/body/div[1]/div[4]/div[2]/div[1]/div[6]/div[1]/div[4]/div/div[2]/div[2]/div/ol/li[1]/div/h3/a
		WebElement element2 = driver.findElement(
				By.xpath(
						"html/body/div[1]/div[4]/div[2]/div[1]/div[6]/div[1]/div[4]/div/div[2]/div[2]/div/ol/li[1]/div/h3/a"));
		
		
		element2.click();
		
		WebElement element3 = driver.findElement(By.xpath("//div[@id='mainContent']/table"));//html/body/div/div[2]/div[2]/table
		
		String actual = element3.getAttribute("id");
		String expected = "choice";
		
		//Assert.assertEquals(expected, actual);
		
		driver.close();
	}

}

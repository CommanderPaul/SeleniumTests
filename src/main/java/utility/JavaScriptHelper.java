package utility;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.Locatable;

/**
 * Execute script on instance of driver
 * @author pwroe
 *
 */
public class JavaScriptHelper {

	public void javaScriptExecutor() {

		WebDriver driver = new FirefoxDriver();

		if (driver instanceof JavascriptExecutor) {
			((JavascriptExecutor) driver).executeScript("thescript();");
			((JavascriptExecutor) driver).executeAsyncScript("thescript();");
		}

	}

	public void executeLocatable(){
		
		WebDriver driver = new FirefoxDriver();
		
		if (driver instanceof Locatable){
			((Locatable)driver).getCoordinates();
		}
	}
	
	public Action useActions(By by){
		
		WebDriver driver = new FirefoxDriver();
		
		Actions builder = new Actions(driver);
		
		WebElement webElement = driver.findElement(by);

		Action act = builder.click(webElement).build();// have to build it at the end of method chain
		
		return act;
	}
	
}

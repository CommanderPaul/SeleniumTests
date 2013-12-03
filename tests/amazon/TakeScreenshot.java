package amazon;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class TakeScreenshot {

	@Test
	public void test() {
		
		ChromeDriver chromeDriver = new ChromeDriver();
		
		
		
	}
	
	@Test
	public void testTakeScreenshot(){
		//Initialise firefox browser
		WebDriver driver = new FirefoxDriver();

		/* Next line makes the browser wait for 7 seconds before declaring it cant find an element.
		Good for slow loading websites*/
		driver.manage().timeouts().implicitlyWait(7,TimeUnit.SECONDS);
		driver.get("http://www.amazon.com");

		System.out.println("Taking Screen Shot");
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File("c:\\test\\screenshot.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.quit();
		}

}

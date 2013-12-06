package utility;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TakeScreenshot {

	private static final String FILE_PATH_AND_NAME = "c:\\test\\screenshot.jpg";

	public static void captureImage(WebDriver driver) {

		/*
		 * Next line makes the browser wait for 7 seconds before declaring it
		 * can't find an element. Good for slow loading websites
		 */
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);

		File screenshot = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils
					.copyFile(screenshot, new File(FILE_PATH_AND_NAME));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

package parallelPrep;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * DriverGenerator generates WebDrivers. The purpose is so that DriverGenerators
 * can be stored in a list instead of actual WebDrivers, which then allows the
 * WebDrivers to be instantiated one at a time.
 */

public abstract class DriverGenerator {

	public abstract WebDriver generate();

	public static class Firefox extends DriverGenerator {

		/**
		 * @see {@link WebDriver#generate()}
		 */
		@Override
		public WebDriver generate() {
			return new FirefoxDriver();
		}

	}

	public static class Chrome extends DriverGenerator {

		/**
		 * @see {@link WebDriver#generate()}
		 */
		@Override
		public WebDriver generate() {
			return new ChromeDriver();
		}

	}

}

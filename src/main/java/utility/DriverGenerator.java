package utility;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * DriverGenerator generates WebDrivers. The purpose is so that DriverGenerators
 * can be stored in a list instead of actual WebDrivers, which then allows the
 * WebDrivers to be instantiated one at a time.
 */

public abstract class DriverGenerator {

	public abstract WebDriver generate();

	private static final String DEFAULT_HUB_URL = "http://localhost:4444/wd/hub";
	private static final String JONATHANS_HUB_URL = "http://192.168.0.83:4444/wd/hub";
	private static final String JILLS_HUB_URL = "http://192.168.0.44:4444/wd/hub";
	private static final String JILLS2_HUB_URL = "http://192.168.0.44:4444/wd/hub";
	
	public static class Firefox extends DriverGenerator {

		/**
		 * @see {@link WebDriver#generate()}	//	what is this comment supposed to communicate?
		 */
		@Override
		public WebDriver generate() {
			return new FirefoxDriver();
		}
	}

	public static class Chrome extends DriverGenerator {
		@Override
		public WebDriver generate() {
			return new ChromeDriver();
		}
	}

	public static class RemoteFirefox extends DriverGenerator {
		@Override
		public WebDriver generate() {
			URL url;
			WebDriver driver;
			try {
				url = new URL(DEFAULT_HUB_URL);
				driver = new RemoteWebDriver(url, DesiredCapabilities.firefox());
			} catch (MalformedURLException e) {
				e.printStackTrace();
				driver = null;
			}
			return driver;
		}

	}
	public static class RemoteChrome extends DriverGenerator {
		@Override
		public WebDriver generate() {
			URL url;
			WebDriver driver;
			try {
				url = new URL(DEFAULT_HUB_URL);
				driver = new RemoteWebDriver(url, DesiredCapabilities.chrome());
			} catch (MalformedURLException e) {
				e.printStackTrace();
				driver = null;
			}
			return driver;
		}

	}
}

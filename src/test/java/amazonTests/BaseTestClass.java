package amazonTests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import utility.DriverGenerator;

public class BaseTestClass {
	
    public static final String CHROME_PATH = "C:\\Windows\\chromedriver.exe";
    public static final String CHROME_KEY = "webdriver.chrome.driver";
    public static final String DATA_PROVIDER = "Generator";
    
    public static final boolean USE_REMOTE_BROWSERS = false;	//	choose between local and remote browsers
    
	// This function will provide the parameter data
	@DataProvider(name = DATA_PROVIDER)
	public Object[][] parameterIntTestProvider() {

		if (USE_REMOTE_BROWSERS) {
			return new Object[][] { 
					{ new DriverGenerator.RemoteFirefox() },
					{ new DriverGenerator.RemoteChrome() } };
		} else {
			return new Object[][] { 
					{ new DriverGenerator.Firefox() },
					{ new DriverGenerator.Chrome() }
			};
		}
	}
    
    // It's important that this is @BeforeClass, not @BeforeMethod or @BeforeSuite
    @BeforeClass
    public void beforeClass() {
           System.setProperty(CHROME_KEY, CHROME_PATH);
    }
	
}

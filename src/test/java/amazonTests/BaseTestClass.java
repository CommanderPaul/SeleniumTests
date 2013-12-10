package amazonTests;

import junit.framework.Assert;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import utility.DriverGenerator;

public class BaseTestClass {
	
    public static final String CHROME_PATH = "C:\\Windows\\chromedriver.exe";
    public static final String CHROME_KEY = "webdriver.chrome.driver";
    public static final String DATA_PROVIDER = "Generator";
    
    public static final boolean USE_REMOTE_BROWSERS = false;	//	choose between local and remote browsers
    public static final boolean USE_STORED_PROFILE_FOR_FIREFOX = true;
    
	protected static final boolean FAIL_TEST = false;
	protected static final String AMAZON_HOME_PAGE = "http://www.amazon.com";
    
    
	// This function will provide the parameter data
	@DataProvider(name = DATA_PROVIDER)
	public Object[][] parameterIntTestProvider() {

		if (USE_REMOTE_BROWSERS) {
			
				return new Object[][] { 
						{ new DriverGenerator.RemoteFirefox() },
						{ new DriverGenerator.RemoteChrome() } };
				
		} else {
			
			if(USE_STORED_PROFILE_FOR_FIREFOX){
				return new Object[][] { 
						{ new DriverGenerator.ProfileFirefox() },
						{ new DriverGenerator.Chrome() }
				};
			}else{
				
				return new Object[][] { 
						{ new DriverGenerator.Firefox() },
						{ new DriverGenerator.Chrome() }
				};
			}
			
		}
	}
    
    // It's important that this is @BeforeClass, not @BeforeMethod or @BeforeSuite
    @BeforeClass
    public void beforeClass() {
           System.setProperty(CHROME_KEY, CHROME_PATH);
    }
    
    /**
     * Fail test setup and close browser
     * @param wd
     */
    protected void failSetupAndCloseBrowser(WebDriver wd){
    	Assert.assertTrue(
				"There was a problem with the test setup, not the assertions",
				FAIL_TEST);
		wd.quit();
    }
	
}

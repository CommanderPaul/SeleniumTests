package parallelPrep;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import utility.DriverGenerator;

public class BaseTestClass {
	
    public static final String CHROME_PATH = "C:\\Windows\\chromedriver.exe";
    public static final String CHROME_KEY = "webdriver.chrome.driver";
    public static final String DATA_PROVIDER = "Generator";
	//public static final String PATH_TO_CHROMEDRIVER_EXECUTABLE = "C:\\Windows\\chromedriver.exe";
	//public static final String PATH_TO_CHROME_WEBBROWSER = "C:\\'Program Files (x86)'\\Google\\Chrome\\Application\\chrome.exe";

    // This function will provide the parameter data
    @DataProvider(name = DATA_PROVIDER)
    public Object[][] parameterIntTestProvider() {
           return new Object[][] { { new DriverGenerator.Firefox() },  {new DriverGenerator.Chrome() } };
    }
    
    // It's important that this is @BeforeClass, not @BeforeMethod!
    @BeforeClass
    public void beforeClass() {
           System.setProperty(CHROME_KEY, CHROME_PATH);
    }
	
}

package amazon;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Parent Test Class 
 * @author pwroe
 *
 */
public class SuperTestClass {

	public static final String PATH_TO_CHROMEDRIVER_EXECUTABLE = "C:\\Windows\\chromedriver.exe";
	public static final String PATH_TO_CHROME_WEBBROWSER = "C:\\'Program Files (x86)'\\Google\\Chrome\\Application\\chrome.exe";
	
	@Before
	public void setupStuff(){
		System.setProperty(PATH_TO_CHROMEDRIVER_EXECUTABLE, PATH_TO_CHROME_WEBBROWSER);
	}
	
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}

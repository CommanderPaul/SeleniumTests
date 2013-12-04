package parallelPrep;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class ParallelPrepTest extends BaseTestClass {
	// dataProvider key word does a lot of thing in testng.
	// dataProvider runs the test for each item in the multidimensional array
	@Test( dataProvider= DATA_PROVIDER)
	public void sampleTest(DriverGenerator dg){
		WebDriver wd = dg.generate();
		
		// test goes here
		
		
		wd.quit();
	}
	

}

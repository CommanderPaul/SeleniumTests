package amazonTests;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.Test;

public class ProfileConfig{

	
	
	ProfileConfig(){
		
		
	}
	
	@Test
	public void loadStuff(){
		File profilelocation = new File("C:\\Users\\pwroe\\Dropbox\\workspace\\SeleniumTesting\\profiles\\firefoxProfiles");
		FirefoxProfile profile = new FirefoxProfile(profilelocation);
		WebDriver drivers = new FirefoxDriver(profile);
		// use http://username:password@www.xyz.com to pass username and password into url request
		// need params for chrome only
		drivers.get("https://httpwatch:quazz@www.httpwatch.com");
	}
}

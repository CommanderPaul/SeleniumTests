package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends amazonTests.PageObject{
	//TODO move constants that only apply to specific page
	public static final String LOGIN_XPATH = "//input[@id='ap_email']";
	public static final By NEW_CUSTOMER_RADIO_BUTTON = By.id("ap_signin_create_radio");
	public static final By SIGN_IN_BUTTON = By.id("signInSubmit-input");
	public static final By USER_NAME_FIELD = By.id("ap_customer_name");
	public static final By EMAIL_CONFIRMATION = By.id("ap_email_check");

	public String emailAddress;
	public String password;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		
	}

	
	public String generateRandomEmailAddress() {
		// need to store email address for email address confirmation
		emailAddress = generateRandomString() + "@gmail.com";
		return emailAddress;
	}
	
	public String generateRandomPassword(){
		password = generateRandomString();
		return password;
	}
	
	//add input parameter for length of string
	public String generateRandomString() {
		StringBuffer randomString = new StringBuffer();
		for (int x = 0; x < 11; x++) {
			int random = (int) (Math.random() * 256);
			randomString.append((char) random);
		}
		return randomString.toString();
	}
}

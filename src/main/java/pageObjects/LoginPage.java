package pageObjects;

import org.openqa.selenium.WebDriver;

public class LoginPage extends PageObject{
	
	public static final String LOGIN_XPATH = "//input[@id='ap_email']";
	public static final String NEW_CUSTOMER_RADIO_BUTTON = "//input[@id='ap_signin_create_radio']";
	public static final String SIGN_IN_BUTTON = "//input[@id='signInSubmit-input']";
	public static final String USER_NAME_FIELD = "//input[@id='ap_customer_name']";
	public static final String EMAIL_CONFIRMATION_XPATH = "//input[@id='ap_email_check']";
	
	
	
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
	
	public String generateRandomString() {
		StringBuffer randomString = new StringBuffer();
		for (int x = 0; x < 11; x++) {
			int random = (int) (Math.random() * 256);
			randomString.append((char) random);
		}
		return randomString.toString();
	}
}

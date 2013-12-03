package utility;

public class SetChromeDriver {

	public static final String PATH_TO_CHROMEDRIVER_EXECUTABLE = "C:\\Windows\\chromedriver.exe";
	public static final String PATH_TO_CHROME_WEBBROWSER = "C:\\'Program Files (x86)'\\Google\\Chrome\\Application\\chrome.exe";
	
	
	public SetChromeDriver(){
		System.setProperty(PATH_TO_CHROMEDRIVER_EXECUTABLE, PATH_TO_CHROME_WEBBROWSER);
		
	}
	
	
	
	
}

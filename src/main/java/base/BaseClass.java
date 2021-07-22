package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import functions.FunctionLibrary;

public class BaseClass {
	public WebDriver Driver;
	public static Properties appProps;

	FunctionLibrary functions = new FunctionLibrary();

	@BeforeSuite(alwaysRun = true)
	public void SuiteSetup() throws FileNotFoundException, IOException {
		appProps = new Properties();
		appProps.load(new FileInputStream(String.format("./Resources/Properties/page.properties")));

	}

	@BeforeClass(alwaysRun = true)
	@Parameters({"platform","browser"})
	public void ClassSetup(String platform,String browser) {

		openBrowser(platform,browser, appProps.getProperty("WWURL"));
	}

	@BeforeTest(alwaysRun = true)
	public void startTest() {

	}

	private void openBrowser(String Platform, String Browser, String URL) {
		if (Platform.equalsIgnoreCase("Windows")) {
			if (Browser.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver", appProps.getProperty("ChromeDriverWin"));
				Driver = new ChromeDriver();
								
			} else if (Browser.equalsIgnoreCase("Edge")) {
				System.setProperty("webdriver.edge.driver", appProps.getProperty("EdgeDriverWin"));
				Driver = new EdgeDriver();
				
			} else if (Browser.equalsIgnoreCase("Firefox")) {
				System.setProperty("webdriver.gecko.driver", appProps.getProperty("FirefoxDriverWin"));
				Driver = new FirefoxDriver();
				
			} else {
				functions.loginfo("There was no options for browser " + Browser);

			}
			
		}else if (Platform.equalsIgnoreCase("Mac")) {
			if (Browser.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver", appProps.getProperty("ChromeDriverMac"));
				Driver = new ChromeDriver();
								
			}else if (Browser.equalsIgnoreCase("Edge")) {
				functions.loginfo("There was no options for browser " + Browser + " on Mac platform.");
//				System.setProperty("webdriver.edge.driver", appProps.getProperty("EdgeDriverWin"));
//				Driver = new EdgeDriver();
				
			} else if (Browser.equalsIgnoreCase("Firefox")) {
				System.setProperty("webdriver.gecko.driver", appProps.getProperty("FirefoxDriverMac"));
				Driver = new FirefoxDriver();
				
			}else if(Browser.equalsIgnoreCase("Safari")) {
				functions.loginfo("There currently is no options for browser " + Browser + " on Mac platform.");
				
			
			}else {
				functions.loginfo("There was no options for browser " + Browser);

			}
		}
		
		
		functions.loginfo(URL);
		Driver.get(URL);
		Driver.manage().window().maximize();
	}

	@AfterMethod(alwaysRun = true)
	public void inbetweenReset(ITestResult result) {

	}

	@AfterSuite(alwaysRun = true)
	public void afterSuite() {

		Driver.quit();
	}

}

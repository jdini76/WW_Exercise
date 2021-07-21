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
	@Parameters("browser")
	public void ClassSetup(String browser) {

		openBrowser(browser, appProps.getProperty("WWURL"));
	}

	@BeforeTest(alwaysRun = true)
	public void startTest() {

	}

	private void openBrowser(String Browser, String URL) {
		if (Browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", appProps.getProperty("ChromeDriver"));
			Driver = new ChromeDriver();
			// File file = new File(URL);
			// URL = file.getAbsolutePath();
			functions.loginfo(URL);
			Driver.get(URL);
			Driver.manage().window().maximize();
		} else if (Browser.equalsIgnoreCase("Edge")) {
			System.setProperty("webdriver.edge.driver", appProps.getProperty("EdgeDriver"));
			Driver = new EdgeDriver();
			functions.loginfo(URL);
			Driver.get(URL);
			Driver.manage().window().maximize();
		} else if (Browser.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", appProps.getProperty("FirefoxDriver"));
			Driver = new FirefoxDriver();
			// File file = new File(URL);
			// URL = file.getAbsolutePath();
			functions.loginfo(URL);
			Driver.get(URL);
			Driver.manage().window().maximize();
		} else {
			functions.loginfo("There was no options for browser " + Browser);

		}

	}

	@AfterMethod(alwaysRun = true)
	public void inbetweenReset(ITestResult result) {

	}

	@AfterSuite(alwaysRun = true)
	public void afterSuite() {

		Driver.quit();
	}

}

package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import functions.FunctionLibrary;

public class LocationPage {
	FunctionLibrary func = new FunctionLibrary();
	WebDriver Driver;
	
	@FindBy(xpath= "//*[@id=\"main\"]/div[1]/div[2]/div/div/h1")
	private WebElement lblLocationName;
	@FindBy(xpath= "//*[@id=\"main\"]/div[1]/div[2]/div/div[2]/div[2]/div[1]")
	private WebElement imgHoursDownArrow;
	@FindBy(xpath= "//*[@id=\"main\"]/div[1]/div[2]/div/div[2]/div[2]/div[2]")
	private WebElement tblHours;
//	@FindBy(xpath= "")
//	private WebElement ;
	
	public LocationPage(WebDriver Driver) {
		this.Driver = Driver;
		PageFactory.initElements(this.Driver, this);
		
	}
	
	
	
	public String getPageTitle() {
		return Driver.getTitle();
	}
	
	public String getLocationTitle() {
		WebDriverWait wait = new WebDriverWait(Driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(lblLocationName));
		return lblLocationName.getText();
	}
	
	public void clickBusinessHours() {
		WebDriverWait wait = new WebDriverWait(Driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(imgHoursDownArrow));
		imgHoursDownArrow.click();
	}
	
	public void printBusinessHours() {
		WebDriverWait wait = new WebDriverWait(Driver, 10);
		wait.until(ExpectedConditions.visibilityOfAllElements(tblHours));
		List<WebElement> listBusinessHours = tblHours.findElements(By.xpath("./child::*"));
		WebElement tblHoursChildren = listBusinessHours.get(0);
		listBusinessHours = tblHoursChildren.findElements(By.xpath("./child::*"));
		for (WebElement item :listBusinessHours) {
			String strLine = item.getText();
			func.loginfo(strLine);
		}
	}
}

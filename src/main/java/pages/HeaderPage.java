package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderPage {
	WebDriver Driver;
	
	@FindBy(xpath="//*[@id=\"__next\"]/div[2]/div/div/div/div[2]/div[1]/ul/li[1]/span/button/span")
	WebElement lnkAttend;
	@FindBy(xpath="//*[@id=\"popup-2\"]/div/div[1]/a[1]/span/span[1]")
	WebElement lnkMenuItem;
	
	
	public HeaderPage(WebDriver Driver) {
		this.Driver = Driver;
		PageFactory.initElements(this.Driver, this);
		
	}
	
	public void goToLocationSearch() {
		lnkAttend.click();
		lnkMenuItem.click();
	}
}

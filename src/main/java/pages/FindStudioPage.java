package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FindStudioPage {
	WebDriver Driver;
	@FindBy(xpath = "//*[@id=\"search-container\"]/div/div[2]/button/div/span")
	private WebElement btnStudio;
	@FindBy(xpath = "//*[@id='location-search']")
	private WebElement txtSearchField;
	@FindBy(xpath = "//*[@id='location-search-cta']")
	private WebElement btnSearch;
	@FindBy(xpath = "//*[@id='search-results']")
	private WebElement tblSearchResults;
	@FindBy(xpath = "//*[@id=\"location-1252089\"]/a/div[1]/div/a")
	private WebElement lnkFirstResult;
	@FindBy(xpath = "//*[@id='location-1252089']/a/div[1]/span")
	private WebElement lblDistance;

	public FindStudioPage(WebDriver Driver) {
		this.Driver = Driver;
		PageFactory.initElements(this.Driver, this);
		
	}

	public String getPageTitle() {
		return Driver.getTitle();
	}

	public void searchStudioByZipCode(String zipCode) {
		btnStudio.click();
		txtSearchField.sendKeys(zipCode);
		btnSearch.click();

	}
	
	public String getFirstTitle() {
		WebDriverWait wait = new WebDriverWait(Driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(tblSearchResults));
		
		
		List<WebElement> listElementChildren = tblSearchResults.findElements(By.xpath("./child::*"));
		WebElement elementFirstResult = listElementChildren.get(0);
		listElementChildren= elementFirstResult.findElements(By.xpath("./child::*"));
		WebElement elementFirstHeader = listElementChildren.get(0);
		listElementChildren = elementFirstHeader.findElements(By.xpath("./child::*"));
		WebElement elementFirstTitle = listElementChildren.get(0);
		listElementChildren = elementFirstTitle.findElements(By.xpath("./child::*"));
		String strTitle = listElementChildren.get(0).getAttribute("innerText");
		return strTitle;
	}
	public String getFirstDistance() {
		WebDriverWait wait = new WebDriverWait(Driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(tblSearchResults));
		
		
		List<WebElement> listElementChildren = tblSearchResults.findElements(By.xpath("./child::*"));
		WebElement elementFirstResult = listElementChildren.get(0);
		listElementChildren= elementFirstResult.findElements(By.xpath("./child::*"));
		WebElement elementFirstHeader = listElementChildren.get(0);
		listElementChildren = elementFirstHeader.findElements(By.xpath("./child::*"));
		WebElement elementFirstTitle = listElementChildren.get(0);
		listElementChildren = elementFirstTitle.findElements(By.xpath("./child::*"));
		String strDistance = listElementChildren.get(1).getAttribute("innerText");
		return strDistance;
	}
	
	public void clickFirstResult() {
		WebDriverWait wait = new WebDriverWait(Driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(tblSearchResults));
		
		
		List<WebElement> listElementChildren = tblSearchResults.findElements(By.xpath("./child::*"));
		WebElement elementFirstResult = listElementChildren.get(0);
		listElementChildren= elementFirstResult.findElements(By.xpath("./child::*"));
		WebElement elementFirstHeader = listElementChildren.get(0);
		listElementChildren = elementFirstHeader.findElements(By.xpath("./child::*"));
		
		WebElement elementFirstLink = listElementChildren.get(0);
		
		elementFirstLink.click();
	}
	

	public Boolean validateSearchResults() {
		List<WebElement> tblResultsElements = Driver.findElements(By.xpath("//*[@id=\"search-results\"]"));
		if (tblResultsElements.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
}

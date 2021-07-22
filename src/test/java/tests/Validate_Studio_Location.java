package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import base.BaseClass;
import functions.FunctionLibrary;
import pages.FindStudioPage;
import pages.HeaderPage;
import pages.LocationPage;

public class Validate_Studio_Location extends BaseClass	{
	String zipCode = "10011";
	FunctionLibrary func = new FunctionLibrary();
	@AfterMethod(alwaysRun = true)
	public void resetPage() {
	try {
		HeaderPage headerPage = new HeaderPage(Driver);
		headerPage.goToLocationSearch();
	}catch (Exception e){
		func.loginfo("Unable to reset");
	}
		
		
	}
	
	
	@Test(enabled=true,groups= {"All","Smoke"})
	public void validatePageTitle() {
		System.out.println("######## RUNNING TEST VALIDATE PAGE TITLE #########");
		FindStudioPage findStudio = new FindStudioPage(Driver);
		System.out.println("Page Title: "+findStudio.getPageTitle());
		Assert.assertTrue(findStudio.getPageTitle().contains("Find WW Studios & Meetings Near You | WW USA"));
		
	}
	
	@Test(enabled= true, groups= {"All"})
	public void validateSearchResults() {
		System.out.println("######## RUNNING TEST VALIDATE SEARCH RESULTS #########");
		
		FindStudioPage findStudio = new FindStudioPage(Driver);
		
		findStudio.searchStudioByZipCode(zipCode);
		String strTitle = findStudio.getFirstTitle();
		String strDistance = findStudio.getFirstDistance();
		func.loginfo("First Result Title: "+ strTitle);		
		func.loginfo("First Result Distance: "+ strDistance);
		LocationPage locationPage = new LocationPage(Driver);
		findStudio.clickFirstResult();
		String strLocationTitle = locationPage.getLocationTitle();
		func.loginfo("Location title on Location Page: "+strLocationTitle);
		Assert.assertTrue(strLocationTitle.contains(strTitle));
	}

	@Test(enabled= true, groups= {"All"})
	public void printBusinessHours() {
	System.out.println("######### RUNNING TEST PRINT BUSINESS HOURS #########");
		
		FindStudioPage findStudio = new FindStudioPage(Driver);
		
		findStudio.searchStudioByZipCode(zipCode);
		LocationPage locationPage = new LocationPage(Driver);
		findStudio.clickFirstResult();
				
		locationPage.clickBusinessHours();
		locationPage.printBusinessHours();
		
		
	}
}
	
	



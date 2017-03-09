package it.akademija.admin;

import org.testng.Assert;

import org.testng.annotations.Test;

import it.akademija.voting.VotingSystem;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

@Test(groups = "admin-actions")
public class CountyTest extends VotingSystem{

	private int numberOfTimesTestWasRan = 0;
	
	
	@Parameters({"loginLink", "usernameAdmin", "password"})
	@BeforeClass
	public void setUp(String loginLink, String usernameAdmin, String password){
	//	driver.get(adminLink);
		pageCounty = new CountyPage(driver);
		pageLogin.login(usernameAdmin, password);
	}
	
//	@Parameters({ "countyFile", "newCountyName" })
//	@AfterTest(alwaysRun = true)
//	public void deleteAllRegisteredCounties(String countyFile, String newCountyName) throws IOException{
//		pageCounty.deleteMultipleCounties(countyFile);
//		pageCounty.deleteCounty(newCountyName);
//		driver.close();
//	}
	
	@Parameters({ "countyFile" })
	@Test(priority = 3, enabled = true)
	public void registerMultipleCountiesTest(String countyFile) throws IOException {
		pageCounty.registerMultipleCounties(countyFile);
	}

	/**
	 * TC05 and TC10
	 */
	@Parameters({ "countyName" })
	@Test(priority = 3, invocationCount = 2, enabled = true)
	public void registerCountyTest(String countyName) {
		pageCounty.registerCounty(countyName, "");
		if (numberOfTimesTestWasRan == 0) {
			Assert.assertTrue(pageCounty.alert.getText().contains("Apygarda " + countyName + " sukurta"));
		} else {
			Assert.assertTrue(pageCounty.alert.getText().contains("Apygarda su tokiu pavadinimu jau užregistruota."));
		}
		numberOfTimesTestWasRan++;
	}

	@Parameters({ "countyToDelete" })
	@Test(priority = 5, enabled = true)
	public void deleteCountyTest(String countyToDelete) {
		pageCounty.deleteCounty(countyToDelete);
		Assert.assertTrue(
				pageCounty.alert.getText().contains("Apygarda " + countyToDelete + " ištrinta"));
	}

	@Parameters({ "countyName", "newCountyName" })
	@Test(priority = 4, enabled = true, groups = {"county-name"})
	public void editCountyTest(String countyName, String newCountyName) {
		pageCounty.editCounty(countyName, newCountyName);
		Assert.assertTrue(
				pageCounty.alert.getText().contains("Apygardos pavadinimas pakeistas iš " + countyName + " į " + newCountyName));
	}

	@Parameters({ "newCountyName", "candidatesList" })
	@Test(priority = 6, enabled = true)
	public void addCandidatesListTest(String newCountyName, String candidatesList) {
		pageCounty.addCandidatesList(newCountyName, candidatesList);
	}
	
}

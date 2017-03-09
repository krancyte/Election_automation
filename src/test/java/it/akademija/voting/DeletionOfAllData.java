package it.akademija.voting;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import it.akademija.admin.CountyPage;
import it.akademija.admin.LoginPage;
import it.akademija.admin.PartyPage;

public class DeletionOfAllData extends VotingSystem{
	
	//private CountyPage pageCounty;
	
	@Parameters({"adminLink", "usernameAdmin", "password"})
	@BeforeClass
	public void setUp(String adminLink, String usernameAdmin, String password){
		driver.get(adminLink);
		pageLogin = new LoginPage(driver);
		pageCounty = new CountyPage(driver);
		pageParty = new PartyPage(driver);
		pageLogin.login(usernameAdmin, password);
	}
	

	@AfterMethod()
	public void logout(){
	//	pageLogin.logout();
	}
	
	@Parameters({ "countyFile", "newCountyName", "newPartyName", "partyName2" })
	@Test(priority = 100)
	public void deleteAllData(String countyFile, String newCountyName, String newPartyName, String partyName2) throws IOException{
		pageCounty.menuCounty.click();
		pageCounty.deleteMultipleCounties(countyFile);
		pageCounty.deleteCounty(newCountyName);
		pageParty.menuParties.click();
		pageParty.deleteParty(newPartyName);
		pageParty.deleteParty(partyName2);
	}

}

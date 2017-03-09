package it.akademija.admin;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import it.akademija.voting.VotingSystem;

public class RepresentativeTest extends VotingSystem{

	
	
	@Parameters({"loginLink", "usernameAdmin", "password"})
	@BeforeClass
	public void setUp(String loginLink, String usernameAdmin, String password){
	//	driver.get(adminLink);
		pageRepresentative = new RepresentativePage(driver);
		pageLogin.login(usernameAdmin, password);
	}
	
//	@AfterClass
//	public void endingTestActivities() {	
//		driver.close();
//	}
	
	/**
	 * TC07
	 */
	@Parameters({"representativeName", "representativeSurname", "districtName"})
	@Test (priority = 15, enabled = true)
	public void registerSingleRepresentativeTest(String representativeName, String representativeSurname, String districtName){
		pageRepresentative.registerDistrictRepresentative(representativeName, representativeSurname, districtName);
		
//		String[] alertLine = driver.findElement(By.xpath("//div[contains(text(),'Vartotojo prisijungimo vardas:')]")).getText().split(" ");
//		System.out.println(alertLine[3]);
//		String repLogin = alertLine[3];
//		login.add(repLogin);
//		System.out.println("List: " + login.get(0));
		Assert.assertTrue(pageRepresentative.alert.getText().contains("Apylinkës atstovas " + representativeName + " " + representativeSurname + " sukurtas."));
	}
	
	@Parameters({"representativeNamesFile", "representativeSurnamesFile", "variousDistrictsFile"})
	@Test (priority = 15, enabled = true)
	public void registerMultipleRepresentativesTest(String representativeNamesFile, String representativeSurnamesFile, String variousDistrictsFile) throws IOException{
		pageRepresentative.registerMultipleDistrictRepresentative(representativeNamesFile, representativeSurnamesFile, variousDistrictsFile);
	}

}

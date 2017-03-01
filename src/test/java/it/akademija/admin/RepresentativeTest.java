package it.akademija.admin;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class RepresentativeTest extends VotingSystem{

	
//	private List<String> login;

	
	/**
	 * TC07
	 */
	@Parameters({"representativeName", "representativeSurname", "districtName"})
	@Test (priority = 15)
	public void registerSingleRepresentativeTest(String representativeName, String representativeSurname, String districtName){
		pageRepresentative.registerDistrict(representativeName, representativeSurname, districtName);
		
//		String[] alertLine = driver.findElement(By.xpath("//div[contains(text(),'Vartotojo prisijungimo vardas:')]")).getText().split(" ");
//		System.out.println(alertLine[3]);
//		String repLogin = alertLine[3];
//		login.add(repLogin);
//		System.out.println("List: " + login.get(0));
		Assert.assertTrue(pageRepresentative.alert.getText().contains("Apylinkės atstovas " + representativeName + " " + representativeSurname + " sukurtas."));
	}
	
	@Parameters({"representativeNamesFile", "representativeSurnamesFile", "variousDistrictsFile"})
	@Test (priority = 15, enabled = true)
	public void registerMultipleRepresentativesTest(String representativeNamesFile, String representativeSurnamesFile, String variousDistrictsFile) throws IOException{
		pageRepresentative.registerMultipleDistricts(representativeNamesFile, representativeSurnamesFile, variousDistrictsFile);
	}

}

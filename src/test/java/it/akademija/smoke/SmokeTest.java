package it.akademija.smoke;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import it.akademija.admin.CountyPage;
import it.akademija.admin.DistrictPage;
import it.akademija.admin.LoginPage;
import it.akademija.admin.PartyPage;
import it.akademija.admin.RepresentativePage;
import it.akademija.admin.ResultsPage;

import it.akademija.representative.MultiMemberPage;
import it.akademija.representative.SingleMemberPage;
import it.akademija.voting.VotingSystem;
import utilities.Utilities;

public class SmokeTest extends VotingSystem {

	private List<String> listCounty;
	private String county;
	private List<String> listDistrict;
	private String district;
	
	@Parameters({ "loginLink" })
	@BeforeClass
	public void setUp(String loginLink) {

	//	driver.get(loginLink);
		pageLogin = new LoginPage(driver);
		pageCounty = new CountyPage(driver);
		pageDistrict = new DistrictPage(driver);
		pageRepresentative = new RepresentativePage(driver);
		pageParty = new PartyPage(driver);
		pageSingleMember = new SingleMemberPage(driver);
		pageMultiMember = new MultiMemberPage(driver);
		pageResults = new ResultsPage(driver);
		utilities = new Utilities(driver);
	}

	@Parameters({ "usernameAdmin", "password" })
	@Test(priority = 1, enabled = true)
	public void loginAdministrator(String usernameAdmin, String password) {
		pageLogin.login(usernameAdmin, password);

	}

	@Parameters({ "countyName", "countyRandom" })
	@Test(priority = 2, enabled = true)
	public void registerCounty(String countyName, String countyRandom) throws IOException {
		listCounty = utilities.putTextFromFileToAList(countyRandom);
		county = utilities.getRandomLine(listCounty);
		System.out.println(county);
		pageCounty.registerCounty(county, "");
	}

	@Parameters({ "districtRandom", "districtAddress", "districtVoter" })
	@Test(priority = 3, enabled = true)
	public void registerDistrict(String districtRandom, String districtAddress, String districtVoter) throws IOException {
		listDistrict = utilities.putTextFromFileToAList(districtRandom);
		district = utilities.getRandomLine(listDistrict);
		System.out.println(district);
		pageDistrict.registerDistrict(district, districtAddress, districtVoter, county, "");

	}

	@Parameters({ "representativeName", "representativeSurname" })
	@Test(priority = 4, enabled = true)
	public void registerRepresentative(String representativeName, String representativeSurname) {
		pageRepresentative.registerDistrictRepresentative(representativeName, representativeSurname, district);

		Assert.assertTrue(pageRepresentative.alert.getText()
				.contains("Apylinkės atstovas " + representativeName + " " + representativeSurname + " sukurtas."));
	}

	@Parameters({ "partyName", "partyNumber", "partyCsvFile" })
	@Test(priority = 5, enabled = true)
	public void registerParty(String partyName, String partyNumber, String partyCsvFile) {
		pageParty.registerParty(partyName, partyNumber, partyCsvFile, "");
	}

	@Parameters({"candidatesList" })
	@Test(priority = 6, enabled = true)
	public void addCandidatesList(String candidatesList) {
		pageCounty.menuCounty.click();
		pageCounty.addCandidatesList(county, candidatesList);
		pageLogin.logout();
	}

//	@Parameters({ "countyName" })
//	@Test(priority = 7, enabled = false)
//	public void deleteCounty(String countyName) {
//		pageCounty.deleteCounty(countyName);
//		Assert.assertTrue(pageCounty.alert.getText().contains("Apygarda " + countyName + " ištrinta"));
//	}

	@Parameters({ "partyName", "loginLink", "usernameAdmin", "password"  })
	@Test(priority = 10, enabled = true)
	public void deletePartyTest(String partyName, String loginLink, String usernameAdmin, String password) {
		driver.get(loginLink);
		pageLogin.login(usernameAdmin, password);
		pageParty.deleteParty(partyName);
		Assert.assertTrue(pageParty.alert.getText().contains(partyName + " ištrinta"));
	}

	@Parameters({ "loginLink" })
	@Test(priority = 8, enabled = true)
	public void fillingSingleMemberResults(String loginLink) {
	//	driver.get(loginLink);
		pageLogin.login(pageRepresentative.getRepresentativeUsername(0), pageRepresentative.getRepresentativePassword(0));
		pageSingleMember.fillOutResultRows();
		Assert.assertTrue(pageSingleMember.alertMessage.getText().contains("Jūsų apylinkės balsai užregistruoti"));
	}

	@Test(priority = 9, enabled = true)
	public void fillingMultiMemberResults() {
		pageMultiMember.fillOutResultRows();
		Assert.assertTrue(pageMultiMember.alertMessage.getText().contains("Jūsų apylinkės balsai užregistruoti"));
		pageMultiMember.buttonLogoutRepresentative.click();
	}

	@Parameters({ "loginLink", "usernameAdmin", "password" })
	@Test(priority = 11, enabled = true)
	public void confirmingSingleMemberResults(String loginLink, String usernameAdmin, String password) {
	//	driver.get(loginLink);
	//	pageLogin.login(usernameAdmin, password);
		pageResults.confirmResultsInSingleMember();
		System.out.println(pageResults.successMessage.getText());
		Assert.assertTrue(pageResults.successMessage.getText().contains(pageResults.getDistrictName() + " balsai patvirtinti."));
	//	Thread.sleep(1000);
	//	pageResults.successMessageClose.click();

	}

	@Test(priority = 12, enabled = true)
	public void confirmingMultiMemberResults() throws InterruptedException {
		pageResults.confirmResultsInMultiMember();
		Assert.assertTrue(pageResults.successMessage.getText().contains(pageResults.getDistrictName() + " balsai patvirtinti."));
		pageMultiMember.buttonLogoutRepresentative.click();
		// System.out.println(pageResults.successMessage.getText());
		// Assert.assertTrue(pageResults.successMessage.getText().contains("AguonÅ³
		// balsai patvirtinti."));
		// Thread.sleep(1000);
		// pageResults.successMessageClose.click();
	}

}

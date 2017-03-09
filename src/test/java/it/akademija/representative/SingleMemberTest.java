package it.akademija.representative;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import it.akademija.admin.RepresentativePage;
import it.akademija.voting.VotingSystem;

public class SingleMemberTest extends VotingSystem{

	
	
	@Parameters({"loginLink", "usernameAdmin", "password"})
	@BeforeClass
	public void setUp(String loginLink, String usernameAdmin, String password){
	//	driver.get(adminLink);
		pageSingleMember = new SingleMemberPage(driver);
		pageLogin.login(usernameAdmin, password);
	}
	
	@Test(priority = 27)
	public void fillingSingleMemberResults() {
		pageSingleMember.fillOutResultRows();
		Assert.assertTrue(pageSingleMember.alertMessage.getText().contains("Jūsų apylinkės balsai užregistruoti"));
	}
}

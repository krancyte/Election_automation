package it.akademija.representative;

import org.testng.Assert;
import org.testng.annotations.Test;

import it.akademija.voting.VotingSystem;

public class MultiMemberTest extends VotingSystem{

	
	@Test(priority = 30)
	public void fillingMultiMemberResults() {
		pageMultiMember.fillOutResultRows();
		Assert.assertTrue(pageMultiMember.alertMessage.getText().contains("Jūsų apylinkės balsai užregistruoti"));
	}
	
}

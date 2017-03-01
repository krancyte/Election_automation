package it.akademija.representative;

import org.testng.Assert;
import org.testng.annotations.Test;
import it.akademija.admin.VotingSystem;

public class SingleMemberTest extends VotingSystem{

	@Test(priority = 27)
	public void fillingSingleMemberResults() {
		pageSingleMember.fillOutResultRows();
		Assert.assertTrue(pageSingleMember.alertMessage.getText().contains("Jūsų apylinkės balsai užregistruoti"));
	}
}

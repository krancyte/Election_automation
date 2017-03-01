package it.akademija.admin;

import org.testng.annotations.Test;

public class ResultsTest extends VotingSystem {
	

	
	@Test (priority = 35)
	public void deleteResultsInSingleMemberTest(){
		pageResults.deleteResultsInSingleMember();
	}

}

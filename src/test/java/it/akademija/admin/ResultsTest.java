package it.akademija.admin;

import org.testng.annotations.Test;

import it.akademija.voting.VotingSystem;

public class ResultsTest extends VotingSystem {
	

	
	@Test (priority = 35)
	public void deleteResultsInSingleMemberTest(){
		pageResults.deleteResultsInSingleMember();
	}

}

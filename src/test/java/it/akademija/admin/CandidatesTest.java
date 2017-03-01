package it.akademija.admin;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CandidatesTest extends VotingSystem {
	
	@Parameters({"candidateNumber", "candidateName", "candidateSurname", "candidateBirthDate", "newCandidateName", "newCandidateSurname", "newCandidateBirthDate", "newCandidateInfo", "newCandidateNumber"})
	@Test(priority = 23)
	public void editNameAndSurnameTest(String candidateNumber, String candidateName, String candidateSurname, String candidateBirthDate, String newCandidateName, String newCandidateSurname, String newCandidateBirthDate, String newCandidateInfo, String newCandidateNumber) {
		pageCandidates.editNameAndSurname(candidateNumber, candidateName, candidateSurname, candidateBirthDate, newCandidateName, newCandidateSurname, newCandidateBirthDate, newCandidateInfo, newCandidateNumber);
		pageCandidates.alert.getText().equals("Kandidatas "+ newCandidateName + " " + newCandidateSurname + " atnaujintas");
	}

}

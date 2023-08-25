package in.fssa.evotingsystem;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.evotingsystem.model.Candidate;
import in.fssa.evotingsystem.service.CandidateService;

public class TestGetAllCandidates {

	@Test
	public void findById() {
		CandidateService candidateService = new CandidateService();

		assertDoesNotThrow(() -> {

			Candidate findUser = candidateService.findByElectionId(4);
			System.out.println(findUser);

		});

	}

	@Test
	public void getAllCandidates() {
		assertDoesNotThrow(() -> {
			CandidateService candidateService = new CandidateService();
			System.out.println(candidateService.getAllElection());
		});

	}
}

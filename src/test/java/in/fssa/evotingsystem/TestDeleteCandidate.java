package in.fssa.evotingsystem;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.evotingsystem.model.Candidate;
import in.fssa.evotingsystem.service.CandidateService;


public class TestDeleteCandidate {

	@Test
	public void testDeleteCandidate() {
		
		CandidateService candidateService = new CandidateService();
		
		Candidate deleteCandidate = new Candidate();
		
		
		deleteCandidate.setActive(false);
		
		assertDoesNotThrow(() -> {
			
			candidateService.delete(4);
		});
	}
}

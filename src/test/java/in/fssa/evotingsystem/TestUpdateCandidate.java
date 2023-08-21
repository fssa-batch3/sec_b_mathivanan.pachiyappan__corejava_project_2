package in.fssa.evotingsystem;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.evotingsystem.exception.ValidationException;
import in.fssa.evotingsystem.model.Candidate;
import in.fssa.evotingsystem.service.CandidateService;

public class TestUpdateCandidate {
	
	@Test
	public void testUpdateCandidateServices() {

		CandidateService candidateService = new CandidateService();

		Candidate newCandidate = new Candidate();
		
		newCandidate.setElectionId(2);
		newCandidate.setCandidateName("Raghul");
		newCandidate.setActive(true);;

		Exception exception = assertThrows(ValidationException.class, () -> {

			candidateService.update(100001, newCandidate);
		});

		String except = exception.getMessage();
		String message = "Candidate not exists";

		assertTrue(except.equals(message));

	}

}

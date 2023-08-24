package in.fssa.evotingsystem;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Random;

import org.junit.jupiter.api.Test;

import in.fssa.evotingsystem.exception.ValidationException;
import in.fssa.evotingsystem.model.Candidate;
import in.fssa.evotingsystem.service.CandidateService;

public class TestUpdateCandidate {

	@Test
	public void testUpdateCandidateWithVaidData() {

		CandidateService candidateService = new CandidateService();
		Candidate newCandidate = new Candidate();

		newCandidate.setCandidateId(37652);
		newCandidate.setElectionId(2);
		newCandidate.setCandidateName("Stalin");
		newCandidate.setCreatedAt(LocalDate.of(2023, 12, 3));

		
		   assertDoesNotThrow(() -> {
			   candidateService.update(12, newCandidate);
	        });
	}

	@Test
	public void testDeleteCandidateWithInvalidId() {

		CandidateService candidateService = new CandidateService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			candidateService.delete(-2);
		});
		String expectedMessage = "ID cannot be 0 or negative";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}
	
	@Test
	public void testCreateCandidateWithNameEmpty() {
		CandidateService candidateService = new CandidateService();
		Exception exception = assertThrows(ValidationException.class, () -> {

			Candidate newCandidate = new Candidate();

			newCandidate.setCandidateId(27456);
			newCandidate.setElectionId(202);
			newCandidate.setCandidateName("");
			newCandidate.setCreatedAt(LocalDate.of(2023, 12, 12));

			candidateService.create(newCandidate);
		});
		String expectedMessage = "Candidate Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateCandidateWithNameNull() {
		CandidateService candidateService = new CandidateService();
		Exception exception = assertThrows(ValidationException.class, () -> {

			Candidate newCandidate = new Candidate();

			newCandidate.setCandidateId(16457);
			newCandidate.setElectionId(202);
			newCandidate.setCandidateName(null);
			newCandidate.setCreatedAt(LocalDate.of(2023, 12, 12));

			candidateService.create(newCandidate);
		});
		String expectedMessage = "Candidate Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

}

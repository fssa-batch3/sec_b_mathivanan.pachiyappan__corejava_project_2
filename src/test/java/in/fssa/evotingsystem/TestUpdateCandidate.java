package in.fssa.evotingsystem;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import in.fssa.evotingsystem.exception.ValidationException;
import in.fssa.evotingsystem.model.Candidate;
import in.fssa.evotingsystem.service.CandidateService;

public class TestUpdateCandidate {
	
	@Test
	public void testUpdateCandidate() {
		// Create a mock candidate object with updated data
		Candidate updatedCandidate = new Candidate();
		updatedCandidate.setCandidateId(123); // Example candidate ID
		updatedCandidate.setElectionId(456); // Example election ID
		updatedCandidate.setCandidateName("John Doe");
		updatedCandidate.setCreatedAt(LocalDate.now());

		CandidateService candidateService = new CandidateService();

		assertDoesNotThrow(() -> {
			candidateService.updateCandidate(3, updatedCandidate);
	    });
	}

	@Test
	public void testDeleteCandidateWithInvalidId() {

		CandidateService candidateService = new CandidateService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			candidateService.deleteCandidate(-2);
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

			candidateService.createCandidate(newCandidate);
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

			candidateService.createCandidate(newCandidate);
		});
		String expectedMessage = "Candidate Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

}

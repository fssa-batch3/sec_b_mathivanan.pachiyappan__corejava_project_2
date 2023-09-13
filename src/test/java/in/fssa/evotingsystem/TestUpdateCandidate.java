package in.fssa.evotingsystem;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import in.fssa.evotingsystem.dao.CandidateDAO;
import in.fssa.evotingsystem.exception.ValidationException;
import in.fssa.evotingsystem.model.Candidate;
import in.fssa.evotingsystem.service.CandidateService;

public class TestUpdateCandidate {

	@Test
	public void testUpdateCandidateWithValidData() {
		CandidateService candidateService = new CandidateService();
		CandidateDAO candidateDAO = new CandidateDAO();

		// Create a mock candidate object with updated data
		Candidate updatedCandidate = new Candidate();

		updatedCandidate.setUserId(2657); // Example candidate ID
		updatedCandidate.setElectionId(4); // Example election ID
		updatedCandidate.setName("S.Anbumani");
		updatedCandidate.setPartyName("PMK");
		updatedCandidate.setImageUrl("https://iili.io/J90ymrv.png");
		updatedCandidate.setCreatedAt(LocalDate.now());
		updatedCandidate.setActive(true);

		assertDoesNotThrow(() -> {

			candidateService.updateCandidate(20, updatedCandidate);

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
	public void testUpdateCandidateWithNameEmpty() {
		CandidateService candidateService = new CandidateService();
		Exception exception = assertThrows(ValidationException.class, () -> {

			Candidate updatedCandidate = new Candidate();

			updatedCandidate.setUserId(2657);
			updatedCandidate.setElectionId(4);
			updatedCandidate.setName("");
			updatedCandidate.setCreatedAt(LocalDate.of(2023, 12, 12));

			candidateService.updateCandidate(20, updatedCandidate);
		});
		String expectedMessage = "Candidate Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateCandidateWithNameNull() {
		CandidateService candidateService = new CandidateService();
		Exception exception = assertThrows(ValidationException.class, () -> {

			Candidate updatedCandidate = new Candidate();

			updatedCandidate.setUserId(2657);
			updatedCandidate.setElectionId(4);
			updatedCandidate.setName(null);
			updatedCandidate.setCreatedAt(LocalDate.of(2023, 12, 12));

			candidateService.updateCandidate(20, updatedCandidate);
		});
		String expectedMessage = "Candidate Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
}

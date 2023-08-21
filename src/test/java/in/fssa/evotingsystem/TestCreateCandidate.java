package in.fssa.evotingsystem;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import in.fssa.evotingsystem.exception.ValidationException;
import in.fssa.evotingsystem.model.Candidate;
import in.fssa.evotingsystem.service.CandidateService;

public class TestCreateCandidate {

	@Test
	public void testCreateCandidateWithValidData() {
		CandidateService candidateService = new CandidateService();

		Candidate newCandidate = new Candidate();

		newCandidate.setId(621);
		newCandidate.setUserId(101);
		newCandidate.setElectionId(20);
		newCandidate.setCandidateName("Modi");
		newCandidate.setCreatedAt(LocalDate.of(2023, 12, 12));
		newCandidate.setActive(true);

		assertDoesNotThrow(() -> {
			candidateService.create(newCandidate);
		});
	}

	@Test
	public void testCreateCandidateWithInvalidData() {
		CandidateService candidateService = new CandidateService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			candidateService.create(null);
		});
		String expectedMessage = "Invalid Candidate Input";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateCandidateWithNameEmpty() {
		CandidateService candidateService = new CandidateService();
		Exception exception = assertThrows(ValidationException.class, () -> {

			Candidate newCandidate = new Candidate();

			newCandidate.setId(1);
			newCandidate.setUserId(101);
			newCandidate.setElectionId(202);
			newCandidate.setCandidateName("");
			newCandidate.setCreatedAt(LocalDate.of(2023, 12, 12));
			newCandidate.setActive(true);

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

			newCandidate.setId(1);
			newCandidate.setUserId(101);
			newCandidate.setElectionId(202);
			newCandidate.setCandidateName(null);
			newCandidate.setCreatedAt(LocalDate.of(2023, 12, 12));
			newCandidate.setActive(true);
			
			candidateService.create(newCandidate);
		});
		String expectedMessage = "Candidate Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateCandidateWithInvalidUserId() {
		CandidateService candidateService = new CandidateService();
		Exception exception = assertThrows(ValidationException.class, () -> {

			Candidate newCandidate = new Candidate();

			newCandidate.setId(18);
			newCandidate.setUserId(-101);
			newCandidate.setElectionId(202);
			newCandidate.setCandidateName("Modi");
			newCandidate.setCreatedAt(LocalDate.of(2023, 12, 12));
			newCandidate.setActive(true);

			candidateService.create(newCandidate);
		});
		String expectedMessage = "User ID cannot be 0 or negative";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateCandidateWithInvalidElectionId() {
		CandidateService candidateService = new CandidateService();
		Exception exception = assertThrows(ValidationException.class, () -> {

			Candidate newCandidate = new Candidate();

			newCandidate.setId(1);
			newCandidate.setUserId(101);
			newCandidate.setElectionId(-202);
			newCandidate.setCandidateName("Modi");
			newCandidate.setCreatedAt(LocalDate.of(2023, 12, 12));
			newCandidate.setActive(true);

			candidateService.create(newCandidate);
		});
		String expectedMessage = "Invalid Election ID";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateCandidateWithExistingId () {
		CandidateService candidateService = new CandidateService();
		Exception exception = assertThrows(RuntimeException.class, () -> {

			Candidate newCandidate = new Candidate();

			newCandidate.setId(12244);
			newCandidate.setUserId(101);
			newCandidate.setElectionId(202);
			newCandidate.setCandidateName("Modi");
			newCandidate.setCreatedAt(LocalDate.of(2023, 12, 12));
			newCandidate.setActive(true);

			candidateService.create(newCandidate);
		});
		String expectedMessage = "Duplicate constraint";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
}

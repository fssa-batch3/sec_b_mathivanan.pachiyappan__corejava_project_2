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

public class TestCreateCandidate {

	@Test
	public void testCreateCandidateWithValidData() {
		CandidateService candidateService = new CandidateService();

		int min = 600000001;
		int max = 999999999;
		int genarate = 1000;
		Random rand = new Random();
		int randNum = 0;

		for (int i = 0; i < genarate; i++) {
			randNum = rand.nextInt(max - min + 1) + min;
		}

		Candidate newCandidate = new Candidate();

		newCandidate.setCandidateId(randNum);
		newCandidate.setElectionId(2);
		newCandidate.setCandidateName("Modi");
		newCandidate.setCreatedAt(LocalDate.of(2023, 12, 3));

		assertDoesNotThrow(() -> {
			candidateService.createCandidate(newCandidate);
		});
	}

	@Test
	public void testCreateCandidateWithInvalidData() {
		CandidateService candidateService = new CandidateService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			candidateService.createCandidate(null);
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

	@Test
	public void testCreateCandidateWithInvalidUserId() {
		CandidateService candidateService = new CandidateService();
		Exception exception = assertThrows(ValidationException.class, () -> {

			Candidate newCandidate = new Candidate();

			newCandidate.setCandidateId(-1);
			newCandidate.setElectionId(202);
			newCandidate.setCandidateName("Modi");
			newCandidate.setCreatedAt(LocalDate.of(2023, 12, 12));

			candidateService.createCandidate(newCandidate);
		});
		String expectedMessage = "Candidate ID cannot be 0 or negative";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateCandidateWithInvalidElectionId() {
		CandidateService candidateService = new CandidateService();
		Exception exception = assertThrows(ValidationException.class, () -> {

			Candidate newCandidate = new Candidate();

			newCandidate.setCandidateId(1);
			newCandidate.setElectionId(-202);
			newCandidate.setCandidateName("Modi");
			newCandidate.setCreatedAt(LocalDate.of(2023, 12, 12));
			
			candidateService.createCandidate(newCandidate);
		});
		String expectedMessage = "Invalid Election ID";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
}

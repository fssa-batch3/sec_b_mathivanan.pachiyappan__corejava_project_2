package in.fssa.evotingsystem;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.evotingsystem.dao.CandidateDAO;
import in.fssa.evotingsystem.exception.ValidationException;
import in.fssa.evotingsystem.service.CandidateService;

public class TestDeleteCandidate {

	@Test
	void testDeleteCandidate() {
		assertDoesNotThrow(() -> {
			CandidateService userService = new CandidateService();
			CandidateDAO app = new CandidateDAO();
			app.changeActive(1);
			userService.delete(1);
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
}

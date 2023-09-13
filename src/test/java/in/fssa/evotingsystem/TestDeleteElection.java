package in.fssa.evotingsystem;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.evotingsystem.dao.ElectionDAO;
import in.fssa.evotingsystem.exception.ValidationException;
import in.fssa.evotingsystem.service.ElectionService;

public class TestDeleteElection {

	@Test
	void testDeleteElection() {
		assertDoesNotThrow(() -> {
			ElectionService electionService = new ElectionService();
			ElectionDAO app = new ElectionDAO();
			app.changeActive(3);
			electionService.deleteElection(3);
		});
	}

	@Test
	public void testDeleteElectionWithInvalidId() {

		ElectionService electionService = new ElectionService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			electionService.deleteElection(-2);
		});
		String expectedMessage = "ID can not be 0 or negative";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}
}

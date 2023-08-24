package in.fssa.evotingsystem;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.evotingsystem.dao.TalukDAO;
import in.fssa.evotingsystem.exception.ValidationException;
import in.fssa.evotingsystem.service.TalukService;

public class TestDeleteTaluk {

	@Test
	void testDeleteTaluk() {
		assertDoesNotThrow(() -> {
			TalukService userService = new TalukService();
			TalukDAO app = new TalukDAO();
			app.changeActive(1);
			userService.delete(1);
		});
	}
	
	@Test
	public void testDeleteTalukWithInvalidId() {

		TalukService talukService = new TalukService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			talukService.delete(-2);
		});
		String expectedMessage = "Id can not be 0 or negative";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}
}

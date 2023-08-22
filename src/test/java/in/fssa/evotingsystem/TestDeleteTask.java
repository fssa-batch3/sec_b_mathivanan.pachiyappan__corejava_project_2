package in.fssa.evotingsystem;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.evotingsystem.exception.ValidationException;
import in.fssa.evotingsystem.service.TalukService;

public class TestDeleteTask {

	@Test
	public void testDeleteTaluk() {

		TalukService talukService = new TalukService();

		assertDoesNotThrow(() -> {

			talukService.delete(2);
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

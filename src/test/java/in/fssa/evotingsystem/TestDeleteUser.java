package in.fssa.evotingsystem;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.evotingsystem.exception.ValidationException;
import in.fssa.evotingsystem.service.UserService;

public class TestDeleteUser {

	@Test
	public void testDeleteUser() {

		UserService userService = new UserService();

		assertDoesNotThrow(() -> {

			userService.delete(2);
		});
	}
	
	@Test
	public void testDeleteUserWithInvalidId() {

		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.delete(-2);
		});
		String expectedMessage = "Id can not be 0 or negative";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}
}

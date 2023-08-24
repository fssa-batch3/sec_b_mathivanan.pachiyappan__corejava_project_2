package in.fssa.evotingsystem;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.evotingsystem.dao.UserDAO;
import in.fssa.evotingsystem.exception.ValidationException;
import in.fssa.evotingsystem.service.UserService;

public class TestDeleteUser {

	@Test
	void testDeleteUser() {
		assertDoesNotThrow(() -> {
			UserService userService = new UserService();
			UserDAO app = new UserDAO();
			app.changeActive(1);
			userService.delete(1);
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

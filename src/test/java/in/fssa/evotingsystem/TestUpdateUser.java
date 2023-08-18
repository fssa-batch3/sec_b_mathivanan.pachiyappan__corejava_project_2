package in.fssa.evotingsystem;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.evotingsystem.exception.ValidationException;
import in.fssa.evotingsystem.model.User;
import in.fssa.evotingsystem.service.UserService;

public class TestUpdateUser {

	@Test
	public void testUpdateUserServices() {

		UserService userService = new UserService();

		User newUser = new User();

		newUser.setId(12345);
		newUser.setPhoneNumber(9876543210L); // Negative phone number
		newUser.setPassword("");
		newUser.setAddress("123 Main St, City");
		newUser.setVoterId(12345);
		newUser.setTalukId(1); // Assuming 1 is a valid taluk ID
		newUser.setActive(true);

		Exception exception = assertThrows(ValidationException.class, () -> {

			userService.update(1, newUser);
		});

		String except = exception.getMessage();
		String message = "user not exist";

		assertTrue(except.equals(message));

	}

}

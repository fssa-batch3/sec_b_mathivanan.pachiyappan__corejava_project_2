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
		
//		newUser .setPhoneNumber(9876543212L);
		newUser.setPassword("hbihbibiio");
		newUser.setAddress("123 Main St, City");
		newUser.setTalukId(1); // Assuming 1 is a valid taluk ID

		Exception exception = assertThrows(ValidationException.class, () -> {

			userService.update(10022, newUser);
		});

		String except = exception.getMessage();
		String message = "User not exsists";

		assertTrue(except.equals(message));

	}

}

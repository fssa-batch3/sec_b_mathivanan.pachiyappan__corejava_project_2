package in.fssa.evotingsystem;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.evotingsystem.exception.ValidationException;
import in.fssa.evotingsystem.model.User;
import in.fssa.evotingsystem.service.UserService;

public class TestUpdateUser {

	@Test
	public void testUpdateUser() {
	    
	    User updatedUser = new User();
	    updatedUser.setPassword("Njact#10");
	    updatedUser.setPhoneNumber(7492498077L);
	    updatedUser.setVoterId(123456);
	    updatedUser.setTalukId(3);
	    updatedUser.setAddress("123 Main St, City");
	    
	    UserService userService = new UserService();
	    
	    assertDoesNotThrow(() -> {
	        userService.updateUser(3, updatedUser);
	    });
	}

	@Test
	public void testDeleteUserWithInvalidId() {

		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.deleteUser(-2);
		});
		String expectedMessage = "Id can not be 0 or negative";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void testUpdateUserWithPasswordEmpty() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {

			User newUser = new User();

			newUser.setPhoneNumber(9876543210L); // Negative phone number
			newUser.setPassword("");
			newUser.setAddress("123 Main St, City");
			newUser.setVoterId(12345);
			newUser.setTalukId(1); // Assuming 1 is a valid taluk ID

			userService.createUser(newUser);
		});
		String expectedMessage = "Password cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateUserWithPasswordNull() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {

			User newUser = new User();

			newUser.setPhoneNumber(9876543210L); // Negative phone number
			newUser.setPassword(null);
			newUser.setAddress("123 Main St, City");
			newUser.setVoterId(12345);
			newUser.setTalukId(1); // Assuming 1 is a valid taluk ID

			userService.createUser(newUser);
		});
		String expectedMessage = "Password cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateUserWithAddressEmpty() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {

			User newUser = new User();

			newUser.setPhoneNumber(9876543210L); // Negative phone number
			newUser.setPassword("Njcat#10");
			newUser.setAddress("");
			newUser.setVoterId(12345);
			newUser.setTalukId(1); // Assuming 1 is a valid taluk ID

			userService.createUser(newUser);
		});
		String expectedMessage = "Address cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateUserWithAddressNull() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {

			User newUser = new User();

			newUser.setPhoneNumber(9876543210L); // Negative phone number
			newUser.setPassword("Njcat#10");
			newUser.setAddress(null);
			newUser.setVoterId(12345);
			newUser.setTalukId(1); // Assuming 1 is a valid taluk ID

			userService.createUser(newUser);
		});
		String expectedMessage = "Address cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateUserWithInvalidTalukId() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {

			User newUser = new User();

			newUser.setPhoneNumber(9876543210L);
			newUser.setPassword("Njcat#10");
			newUser.setAddress("123 Main St, City");
			newUser.setVoterId(12345);
			newUser.setTalukId(-1); // Invalid negative taluk ID

			userService.createUser(newUser);
		});
		String expectedMessage = "Invalid Taluk ID";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

}

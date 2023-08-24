package in.fssa.evotingsystem;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;

import in.fssa.evotingsystem.exception.ValidationException;
import in.fssa.evotingsystem.model.User;
import in.fssa.evotingsystem.service.UserService;

public class TestUpdateUser {

	@Test
    public void testUpdateUserWithValidData() {
        UserService userService = new UserService();

		User newUser = new User();

		newUser.setPhoneNumber(9787264837L);
        newUser.setPassword("Mathi@321"); // Set a password
        newUser.setAddress("No 123, st str, chennai");
        newUser.setVoterId(6782);
        newUser.setTalukId(1);

        // Assuming your update method returns some status or throws an exception, adjust accordingly
        assertDoesNotThrow(() -> {
            userService.update(5, newUser);
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

			userService.create(newUser);
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

			userService.create(newUser);
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
			newUser.setPassword("Njcat#10van");
			newUser.setAddress("");
			newUser.setVoterId(12345);
			newUser.setTalukId(1); // Assuming 1 is a valid taluk ID

			userService.create(newUser);
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
			newUser.setPassword("Njcat#10van");
			newUser.setAddress(null);
			newUser.setVoterId(12345);
			newUser.setTalukId(1); // Assuming 1 is a valid taluk ID

			userService.create(newUser);
		});
		String expectedMessage = "Address cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateUserWithInvalidVoterId() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {

			User newUser = new User();

			newUser.setPhoneNumber(8737634567L);
			newUser.setPassword("Njcat#10van");
			newUser.setAddress("123 Main St, City");
			newUser.setVoterId(-2);
			newUser.setTalukId(13);

			userService.update(15, newUser);
		});
		String expectedMessage = "Invalid Voter ID";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateUserWithInvalidTalukId() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {

			User newUser = new User();

			newUser.setPhoneNumber(9876543210L);
			newUser.setPassword("Njcat#10van");
			newUser.setAddress("123 Main St, City");
			newUser.setVoterId(12345);
			newUser.setTalukId(-1); // Invalid negative taluk ID

			userService.create(newUser);
		});
		String expectedMessage = "Invalid Taluk ID";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}


}

package in.fssa.evotingsystem;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.evotingsystem.exception.ValidationException;
import in.fssa.evotingsystem.model.User;
import in.fssa.evotingsystem.service.UserService;

public class TestCreateUser {

	@Test
	public void testCreateUserWithValidData() {
		UserService userService = new UserService();

		User newUser = new User();

		newUser.setId(1004);
		newUser.setPhoneNumber(9112264837L);
		newUser.setPassword("Njcat#10van");
		newUser.setAddress("112 Main St, City");
		newUser.setVoterId(6782);
		newUser.setTalukId(1); // Assuming 1 is a valid taluk ID
		newUser.setActive(true);

		assertDoesNotThrow(() -> {
			userService.create(newUser);
		});
	}

	@Test
	public void testCreateUserWithInvalidData() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(null);
		});
		String expectedMessage = "Invalid User Input";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateUserWithPhoneNumberNegative() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {

			User newUser = new User();

			newUser.setId(12345);
			newUser.setPhoneNumber(-9876543210L); // Negative phone number
			newUser.setPassword("Njcat#10van");
			newUser.setAddress("123 Main St, City");
			newUser.setVoterId(12345);
			newUser.setTalukId(1); // Assuming 1 is a valid taluk ID
			newUser.setActive(true);

			userService.create(newUser);
		});
		String expectedMessage = "Invalid Phone Number";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateUserWithPasswordEmpty() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {

			User newUser = new User();

			newUser.setId(12345);
			newUser.setPhoneNumber(9876543210L); // Negative phone number
			newUser.setPassword("");
			newUser.setAddress("123 Main St, City");
			newUser.setVoterId(12345);
			newUser.setTalukId(1); // Assuming 1 is a valid taluk ID
			newUser.setActive(true);

			userService.create(newUser);
		});
		String expectedMessage = "Password cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateUserWithPasswordNull() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {

			User newUser = new User();

			newUser.setId(12345);
			newUser.setPhoneNumber(9876543210L); // Negative phone number
			newUser.setPassword(null);
			newUser.setAddress("123 Main St, City");
			newUser.setVoterId(12345);
			newUser.setTalukId(1); // Assuming 1 is a valid taluk ID
			newUser.setActive(true);
			
			userService.create(newUser);
		});
		String expectedMessage = "Password cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateUserWithAddressEmpty() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {

			User newUser = new User();

			newUser.setId(12345);
			newUser.setPhoneNumber(9876543210L); // Negative phone number
			newUser.setPassword("Njcat#10van");
			newUser.setAddress("");
			newUser.setVoterId(12345);
			newUser.setTalukId(1); // Assuming 1 is a valid taluk ID
			newUser.setActive(true);

			userService.create(newUser);
		});
		String expectedMessage = "Address cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateUserWithAddressNull() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {

			User newUser = new User();

			newUser.setId(12345);
			newUser.setPhoneNumber(9876543210L); // Negative phone number
			newUser.setPassword("Njcat#10van");
			newUser.setAddress(null);
			newUser.setVoterId(12345);
			newUser.setTalukId(1); // Assuming 1 is a valid taluk ID
			newUser.setActive(true);
			
			userService.create(newUser);
		});
		String expectedMessage = "Address cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateUserWithInvalidVoterId() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {

			User newUser = new User();

			newUser.setId(1);
			newUser.setPhoneNumber(1234565890L);
			newUser.setPassword("Njcat#10van");
			newUser.setAddress("123 Main St, City");
			newUser.setVoterId(-2);
			newUser.setTalukId(1); 
			newUser.setActive(true);

			userService.create(newUser);
		});
		String expectedMessage = "Invalid Voter ID";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateUserWithInvalidTalukId() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {

			User newUser = new User();

			newUser.setId(12345);
			newUser.setPhoneNumber(9876543210L);
			newUser.setPassword("Njcat#10van");
			newUser.setAddress("123 Main St, City");
			newUser.setVoterId(12345);
			newUser.setTalukId(-1); // Invalid negative taluk ID
			newUser.setActive(true);

			userService.create(newUser);
		});
		String expectedMessage = "Invalid Taluk ID";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateUserWithExistingPhoneNumber () {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {

			User newUser = new User();

			newUser.setId(4);
			newUser.setPhoneNumber(1234567890L);
			newUser.setPassword("Njcat#10van");
			newUser.setAddress("123 Main St, City");
			newUser.setVoterId(12345);
			newUser.setTalukId(1); // Assuming 1 is a valid taluk ID
			newUser.setActive(true);

			userService.create(newUser);
		});
		String expectedMessage = "Duplicate constraint";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
}

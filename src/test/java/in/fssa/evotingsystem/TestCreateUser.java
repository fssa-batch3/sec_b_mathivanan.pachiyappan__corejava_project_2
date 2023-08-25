package in.fssa.evotingsystem;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;

import in.fssa.evotingsystem.exception.ValidationException;
import in.fssa.evotingsystem.model.User;
import in.fssa.evotingsystem.service.UserService;

public class TestCreateUser {

	@Test
	public void testCreateUserWithValidData() {

		UserService userService = new UserService();

		long min = 600000001l;
		long max = 9999999999l;
		int genarate = 1000;
		Random rand = new Random();
		long randNum = 0;

		for (int i = 0; i < genarate; i++) {
			randNum = rand.nextLong(max - min + 1) + min;
		}

		User newUser = new User();

		newUser.setPhoneNumber(randNum);
		newUser.setPassword("Njcat#10van");
		newUser.setAddress("112 Main St, City");
		newUser.setVoterId(6782);
		newUser.setTalukId(1); // Assuming 1 is a valid taluk ID

		assertDoesNotThrow(() -> {
			userService.createUser(newUser);
		});
	}

	@Test
	public void testCreateUserWithInvalidData() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(null);
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

			newUser.setPhoneNumber(-9876543210L); // Negative phone number
			newUser.setPassword("Njcat#10van");
			newUser.setAddress("123 Main St, City");
			newUser.setVoterId(12345);
			newUser.setTalukId(1); // Assuming 1 is a valid taluk ID

			userService.createUser(newUser);
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
	public void testCreateUserWithPasswordNull() {
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
	public void testCreateUserWithAddressEmpty() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {

			User newUser = new User();

			newUser.setPhoneNumber(9876543210L); // Negative phone number
			newUser.setPassword("Njcat#10van");
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
	public void testCreateUserWithAddressNull() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {

			User newUser = new User();

			newUser.setPhoneNumber(9876543210L); // Negative phone number
			newUser.setPassword("Njcat#10van");
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
	public void testCreateUserWithInvalidVoterId() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {

			User newUser = new User();

			newUser.setPhoneNumber(1234565890L);
			newUser.setPassword("Njcat#10van");
			newUser.setAddress("123 Main St, City");
			newUser.setVoterId(-2);
			newUser.setTalukId(1);

			userService.createUser(newUser);
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

			newUser.setPhoneNumber(9876543210L);
			newUser.setPassword("Njcat#10van");
			newUser.setAddress("123 Main St, City");
			newUser.setVoterId(12345);
			newUser.setTalukId(-1); // Invalid negative taluk ID

			userService.createUser(newUser);
		});
		String expectedMessage = "Invalid Taluk ID";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateUserWithExistingPhoneNumber() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {

			User newUser = new User();

			newUser.setPhoneNumber(8208285560L);
			newUser.setPassword("Njcat#10van");
			newUser.setAddress("123 Main St, City");
			newUser.setVoterId(12345);
			newUser.setTalukId(1); // Assuming 1 is a valid taluk ID

			userService.createUser(newUser);
		});
		String expectedMessage = "Phone number already exists";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
}

package in.fssa.evotingsystem;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.evotingsystem.model.User;
import in.fssa.evotingsystem.service.UserService;

public class TestGetAllUsers {

	@Test
	public void findById() {
		UserService userService = new UserService();

		assertDoesNotThrow(() -> {

			User findUser = userService.findByUserId(2);
			System.out.println(findUser);

		});

	}

	@Test
	public void getAllUsers() {
		assertDoesNotThrow(() -> {
			UserService userService = new UserService();
			System.out.println(userService.getAllUsers());

		});

	}

}

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

			User finalUser = userService.findById(2);
			// System.out.println(finalUser);

		});

	}

	@Test
	public void getAllUsers() {
		UserService userService = new UserService();
		System.out.println(userService.getAll());

	}

	@Test
	public void counting() {
		UserService userService = new UserService();

		System.out.println(userService.count());

	}

}

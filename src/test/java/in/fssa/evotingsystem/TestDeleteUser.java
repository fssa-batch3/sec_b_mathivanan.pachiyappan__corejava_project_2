package in.fssa.evotingsystem;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.evotingsystem.model.User;
import in.fssa.evotingsystem.service.UserService;

public class TestDeleteUser {

	@Test
	public void testDeleteUser() {
		
		UserService userService = new UserService();
		
		User deleteUser = new User();
		
		
		deleteUser.setActive(false);
		
		assertDoesNotThrow(() -> {
			
			userService.delete(1);
		});
	}
}

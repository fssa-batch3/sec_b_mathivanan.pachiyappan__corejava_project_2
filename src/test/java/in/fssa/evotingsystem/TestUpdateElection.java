package in.fssa.evotingsystem;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import in.fssa.evotingsystem.exception.ValidationException;
import in.fssa.evotingsystem.model.Election;
import in.fssa.evotingsystem.service.ElectionService;


public class TestUpdateElection {

	@Test
	public void testUpdateElectionServices() {

		ElectionService userService = new ElectionService();

		Election newUser = new Election();

		newUser.setId(2);
		newUser.setBoothAddress("Community hall");
		newUser.setElectionName("Prime minister election 2023");
		newUser.setElectionDate(LocalDate.of(2023, 9, 12));
		newUser.setTalukId(1);
		newUser.setActive(true);

		Exception exception = assertThrows(ValidationException.class, () -> {

			userService.update(21, newUser);
		});

		String except = exception.getMessage();
		String message = "Election not exist";

		assertTrue(except.equals(message));

	}
}

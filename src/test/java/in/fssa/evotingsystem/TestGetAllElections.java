package in.fssa.evotingsystem;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.evotingsystem.model.Election;
import in.fssa.evotingsystem.service.ElectionService;

public class TestGetAllElections {

	@Test
	public void findById() {
		ElectionService electionService = new ElectionService();

		assertDoesNotThrow(() -> {

			Election findElection = electionService.findById(2);
			 System.out.println(findElection);

		});

	}

	@Test
	public void getAllElections() {
		assertDoesNotThrow(() -> {
		ElectionService electionService = new ElectionService();
		System.out.println(electionService.getAll());
		});
	}
}

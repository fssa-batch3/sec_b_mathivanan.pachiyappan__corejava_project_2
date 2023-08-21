package in.fssa.evotingsystem;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.evotingsystem.model.Election;
import in.fssa.evotingsystem.service.ElectionService;

public class TestDeleteElection {
	
	@Test
	public void testDeleteElection() {
		
		ElectionService electioService = new ElectionService();
		
		Election deleteElection = new Election();
		
		
		deleteElection.setActive(false);
		
		assertDoesNotThrow(() -> {
			
			electioService.delete(1);
		});
	}

}

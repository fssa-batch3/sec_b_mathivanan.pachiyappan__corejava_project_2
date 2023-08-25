package in.fssa.evotingsystem;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.evotingsystem.model.Taluk;
import in.fssa.evotingsystem.service.TalukService;

public class TestGetAllTaluk {

	@Test
	public void findById() {
		TalukService talukService = new TalukService();

		assertDoesNotThrow(() -> {

			Taluk findTaluk = talukService.findByTalukId(1);
			System.out.println(findTaluk);

		});

	}

	@Test
	public void getAllUsers() {
		assertDoesNotThrow(() -> {
			TalukService talukService = new TalukService();
			System.out.println(talukService.getAllTaluk());

		});

	}

}

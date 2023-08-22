package in.fssa.evotingsystem;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.evotingsystem.exception.ValidationException;
import in.fssa.evotingsystem.model.Taluk;
import in.fssa.evotingsystem.service.TalukService;

public class TestUpdateTaluk {

	@Test
	public void testUpdateTalukServices() {

		TalukService talukService = new TalukService();

		Taluk newTaluk = new Taluk();

		newTaluk.setTalukName("U1");

		Exception exception = assertThrows(ValidationException.class, () -> {

			talukService.update(1001, newTaluk);
		});

		String except = exception.getMessage();
		String message = "Taluk not exsists";

		assertTrue(except.equals(message));
	}

	@Test
	public void testDeleteTalukWithInvalidId() {

		TalukService talukService = new TalukService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			talukService.delete(-2);
		});
		String expectedMessage = "Id can not be 0 or negative";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}
}

package in.fssa.evotingsystem;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.evotingsystem.exception.ValidationException;
import in.fssa.evotingsystem.model.Taluk;
import in.fssa.evotingsystem.service.TalukService;

public class TestCreateTaluk {

	@Test
	public void testCreateTalukWithValidData() {
		TalukService talukService = new TalukService();

		Taluk newTaluk = new Taluk();

		newTaluk.setTalukName("A1");

		assertDoesNotThrow(() -> {
			talukService.create(newTaluk);
		});
	}
	
	@Test
	public void testCreateTalukWithInvalidData() {
		TalukService talukService = new TalukService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			talukService.create(null);
		});
		String expectedMessage = "Invalid Taluk Input";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateTalukWithTalukNameEmpty() {
		TalukService userService = new TalukService();
		Exception exception = assertThrows(ValidationException.class, () -> {

			Taluk newUser = new Taluk();

			newUser.setTalukName("");
			
			userService.create(newUser);
		});
		String expectedMessage = "Taluk Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateTalukWithTalukNameNull() {
		TalukService talukService = new TalukService();
		Exception exception = assertThrows(ValidationException.class, () -> {

			Taluk newTaluk = new Taluk();

			newTaluk.setTalukName(null);
			
			talukService.create(newTaluk);
		});
		String expectedMessage = "Taluk Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
}

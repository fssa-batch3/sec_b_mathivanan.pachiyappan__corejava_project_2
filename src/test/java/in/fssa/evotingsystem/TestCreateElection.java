package in.fssa.evotingsystem;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import in.fssa.evotingsystem.exception.ValidationException;
import in.fssa.evotingsystem.model.Election;
import in.fssa.evotingsystem.service.ElectionService;

public class TestCreateElection {

	@Test
	public void testCreateElectionWithValidData() {
		ElectionService electionService = new ElectionService();

		Election newElection = new Election();

		newElection.setBoothAddress("Community Mahall");
		newElection.setElectionName("Prime minister election 2023");
		newElection.setElectionDate(LocalDate.of(2023, 12, 3));
		newElection.setTalukId(1);

		assertDoesNotThrow(() -> {
			electionService.createElection(newElection);
		});
	}

	@Test
	public void testCreateElectionWithInvalidData() {
		ElectionService electionService = new ElectionService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			electionService.createElection(null);
		});
		String expectedMessage = "Invalid Election Input";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateElectionWithBoothAddressEmpty() {
		ElectionService electionService = new ElectionService();
		Exception exception = assertThrows(ValidationException.class, () -> {

			Election newElection = new Election();

			newElection.setBoothAddress("");
			newElection.setElectionName("Prime minister election 2023");
			newElection.setElectionDate(LocalDate.of(2023, 3, 12));
			newElection.setTalukId(1);

			electionService.createElection(newElection);
		});
		String expectedMessage = "Booth Address cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateElectionWithBoothAddressNull() {
		ElectionService electionService = new ElectionService();
		Exception exception = assertThrows(ValidationException.class, () -> {

			Election newElection = new Election();

			newElection.setBoothAddress(null);
			newElection.setElectionName("Prime minister election 2023");
			newElection.setElectionDate(LocalDate.of(2023, 3, 12));
			newElection.setTalukId(1);

			electionService.createElection(newElection);
		});
		String expectedMessage = "Booth Address cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateElectionWithElectionNameEmpty() {
		ElectionService electionService = new ElectionService();
		Exception exception = assertThrows(ValidationException.class, () -> {

			Election newElection = new Election();

			newElection.setBoothAddress("Community hall");
			newElection.setElectionName("");
			newElection.setElectionDate(LocalDate.of(2023, 3, 12));
			newElection.setTalukId(1);

			electionService.createElection(newElection);

		});
		String expectedMessage = "Election Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateElectionWithElectionNameNUll() {
		ElectionService electionService = new ElectionService();
		Exception exception = assertThrows(ValidationException.class, () -> {

			Election newElection = new Election();

			newElection.setBoothAddress("Community hall");
			newElection.setElectionName(null);
			newElection.setElectionDate(LocalDate.of(2023, 3, 12));
			newElection.setTalukId(1);

			electionService.createElection(newElection);

		});
		String expectedMessage = "Election Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateElectionWithInvalidDate() {
		ElectionService electionService = new ElectionService();
		Exception exception = assertThrows(ValidationException.class, () -> {

			Election newElection = new Election();

			newElection.setBoothAddress("Community hall");
			newElection.setElectionName("Prime minister election 2023");
			newElection.setElectionDate(LocalDate.of(2023, 3, 12));
			newElection.setTalukId(1);

			electionService.createElection(newElection);

		});
		String expectedMessage = "Invalid Election Date";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateElectionWithInvalidTalukId() {
		ElectionService electionService = new ElectionService();
		Exception exception = assertThrows(ValidationException.class, () -> {

			Election newElection = new Election();

			newElection.setBoothAddress("Community hall");
			newElection.setElectionName("Prime minister election 2023");
			newElection.setElectionDate(LocalDate.of(2023, 3, 12));
			newElection.setTalukId(-1);

			electionService.createElection(newElection);

		});
		String expectedMessage = "Invalid Taluk ID";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

}

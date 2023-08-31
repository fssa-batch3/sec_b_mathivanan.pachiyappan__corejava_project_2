package in.fssa.evotingsystem;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import in.fssa.evotingsystem.exception.ValidationException;
import in.fssa.evotingsystem.model.Election;
import in.fssa.evotingsystem.service.ElectionService;

public class TestUpdateElection {

	@Test
	public void testUpdateElectionWithValidData() {
	    Election updateElection = new Election();
	    updateElection.setBoothAddress("Community Hall");
	    updateElection.setElectionName("Prime Minister Election 2023");
	    updateElection.setElectionDate(LocalDate.of(2023, 9, 12));
	    updateElection.setTalukId(1);

	    ElectionService electionService = new ElectionService();

	    assertDoesNotThrow(() -> {
	        electionService.updateElection(3, updateElection);
	    });
	}


	@Test
	public void testDeleteElectionWithInvalidId() {

		ElectionService electionService = new ElectionService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			electionService.deleteElection(-2);
		});
		String expectedMessage = "ID can not be 0 or negative";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void testUpdateElectionWithBoothAddressEmpty() {
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
	public void testUpdateElectionWithBoothAddressNull() {
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
	public void testUpdateElectionWithElectionNameEmpty() {
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
	public void testUpdateElectionWithElectionNameNUll() {
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
	public void testUpdateElectionWithInvalidDate() {
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
	public void testUpdateElectionWithInvalidTalukId() {
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

package in.fssa.evotingsystem.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import in.fssa.evotingsystem.dao.ElectionDAO;
import in.fssa.evotingsystem.exception.PersistanceException;
import in.fssa.evotingsystem.exception.ServiceException;
import in.fssa.evotingsystem.exception.ValidationException;
import in.fssa.evotingsystem.model.Election;
import in.fssa.evotingsystem.validator.ElectionValidator;

/**
 * This class provides services related to managing elections.
 */
public class ElectionService {

	private ElectionDAO electionDAO = new ElectionDAO();

	/**
	 * Converts a string representation of a date to a LocalDate object.
	 *
	 * @param dateString The date string to be converted.
	 * @return The LocalDate representation of the input date string.
	 */
	public static LocalDate convertToDate(String dateString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		try {
			LocalDate localDate = LocalDate.parse(dateString, formatter);
			return localDate;
		} catch (Exception e) {
			System.out.println("Invalid date format!");
			return null;
		}
	}

	/**
	 * Converts a LocalDate object to a java.util.Date object.
	 *
	 * @param newDate The LocalDate to be converted.
	 * @return The java.util.Date representation of the input LocalDate.
	 */
	public static java.util.Date convertDate(LocalDate newDate) {
		LocalDateTime localDateTime = newDate.atStartOfDay();
		java.util.Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		return date;
	}

	/**
	 * Converts a java.sql.Date object to a LocalDate object.
	 *
	 * @param sqlDate The java.sql.Date to be converted.
	 * @return The LocalDate representation of the input java.sql.Date.
	 */
	public static LocalDate convertSqlDateToLocalDate(Date sqlDate) {
		java.sql.Date c = (java.sql.Date) sqlDate;
		java.util.Date utilDate = new java.util.Date(c.getTime());
		return utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	/**
	 * Creates a new election.
	 *
	 * @param election The election to be created.
	 * @throws ServiceException    If there was an issue creating the election.
	 * @throws ValidationException If the provided election data is not valid.
	 */
	public void createElection(Election election) throws ServiceException, ValidationException {
		try {
			ElectionValidator.validate(election);
			electionDAO.create(election);
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Updates an existing election's information.
	 *
	 * @param newId    The ID of the election to be updated.
	 * @param election The updated election information.
	 * @throws ServiceException    If there was an issue updating the election.
	 * @throws ValidationException If the provided election data is not valid.
	 */
	public void updateElection(int newId, Election election) throws ServiceException, ValidationException {
		try {
			ElectionValidator.isElectionIdExistsForUpdate(newId, election.getId());
			ElectionValidator.isIdExists(newId);
			ElectionValidator.validateUpdate(newId, election);

			electionDAO.update(newId, election);
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Deletes an election by ID.
	 *
	 * @param id The ID of the election to be deleted.
	 * @throws ServiceException    If there was an issue deleting the election.
	 * @throws ValidationException If the provided election ID is not valid.
	 */
	public void deleteElection(int id) throws ServiceException, ValidationException {
		try {
			ElectionValidator.isIdExists(id);
			ElectionValidator.validateId(id);
			electionDAO.delete(id);
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Finds an election by its ID.
	 *
	 * @param newId The ID of the election to be found.
	 * @return The found election, or null if not found.
	 * @throws ServiceException    If there was an issue finding the election.
	 * @throws ValidationException If the provided election ID is not valid.
	 */
	public Election findByElectionId(int newId) throws ServiceException, ValidationException {
		try {
			ElectionValidator.validateId(newId);
			return electionDAO.findById(newId);
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Retrieves a list of all elections.
	 *
	 * @return The list of all elections.
	 * @throws ServiceException If there was an issue retrieving the elections.
	 */
	public List<Election> getAllElections() throws ServiceException {
		try {
			return electionDAO.findAll();
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}

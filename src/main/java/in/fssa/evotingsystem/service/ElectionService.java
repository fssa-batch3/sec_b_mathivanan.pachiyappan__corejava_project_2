package in.fssa.evotingsystem.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import in.fssa.evotingsystem.dao.ElectionDAO;
import in.fssa.evotingsystem.exception.ValidationException;
import in.fssa.evotingsystem.model.Election;
import in.fssa.evotingsystem.validator.ElectionValidator;


/**
 * Service class that provides methods to interact with election-related operations.
 */
public class ElectionService {

	ElectionDAO electiondao = new ElectionDAO();

	/**
     * Converts a date string in "dd/MM/yyyy" format to a LocalDate object.
     *
     * @param dateString The date string to be converted.
     * @return The converted LocalDate object, or null if the date format is invalid.
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
     * @param newDate The LocalDate object to be converted.
     * @return The converted java.util.Date object.
     */

	public static java.util.Date convertDate(LocalDate newDate) {
		LocalDateTime localDateTime = newDate.atStartOfDay();
		java.util.Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		return date;
	}
	
	/**
     * Converts a java.sql.Date to a LocalDate object.
     *
     * @param sqlDate The java.sql.Date to be converted.
     * @return The converted LocalDate object.
     */

	public static LocalDate convertSqlDateToLocalDate(Date sqlDate) {
		java.sql.Date c = (java.sql.Date) sqlDate;
		java.util.Date utilDate = new java.util.Date(c.getTime());
		return utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	/**
     * Creates a new election and stores it in the database.
     *
     * @param election The election object to be created.
     * @return The created election.
     * @throws Exception If validation of election data fails.
     */
	
	public Election create(Election election) throws Exception {

		ElectionValidator.validate(election);

		electiondao.create(election);
		return election;

	}

	/**
     * Retrieves the count of elections in the database.
     *
     * @return The count of elections.
     */
	
	public int count() {
		return electiondao.count();

	}

	/**
     * Updates an existing election's information in the database.
     *
     * @param newId The ID of the election to be updated.
     * @param election The updated election object.
     * @return The updated ElectionDAO object.
     * @throws ValidationException If validation of election data fails.
     */
	
	public ElectionDAO update(int newId, Election election) throws ValidationException {

		ElectionValidator.validate(election);

		electiondao.update(newId, election);

		return electiondao;

	}

	/**
     * Deletes an election from the database by its ID.
     *
     * @param Id The ID of the election to be deleted.
     * @return A string indicating the result of the deletion operation.
     */
	
	public String delete(int Id) {

		electiondao.delete(Id);
		
		return "Election Deleted";

	}

	/**
     * Retrieves an election from the database by its ID.
     *
     * @param newId The ID of the election to be retrieved.
     * @return The retrieved election, or null if not found.
     */
	
	public Election findById(int newId) {

		return electiondao.findById(newId);

	}

 
	/**
     * Retrieves a list of all elections from the database.
     *
     * @return A list of all elections.
     */
	
	public List<Election> getAll() {

		return electiondao.findAll();

	}

}

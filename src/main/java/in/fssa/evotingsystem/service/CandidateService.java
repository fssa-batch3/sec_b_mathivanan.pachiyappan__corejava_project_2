package in.fssa.evotingsystem.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import in.fssa.evotingsystem.dao.CandidateDAO;
import in.fssa.evotingsystem.exception.ValidationException;
import in.fssa.evotingsystem.model.Candidate;
import in.fssa.evotingsystem.validator.CandidateValidator;

/**
 * Service class that provides methods to interact with candidate-related operations.
 */

public class CandidateService {

	CandidateDAO candidatedao = new CandidateDAO();

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
     * Creates a new candidate and stores it in the database.
     *
     * @param candidate The candidate object to be created.
     * @return The created candidate.
     * @throws Exception If validation of candidate data fails.
     */
	public Candidate create(Candidate candidate) throws Exception {

		CandidateValidator.validate(candidate);

		candidatedao.create(candidate);
		return candidate;

	}

	 /**
     * Retrieves the count of candidates in the database.
     *
     * @return The count of candidates.
     */
	public int count() {
		return candidatedao.count();

	}

	/**
     * Updates an existing candidate's information in the database.
     *
     * @param newId The ID of the candidate to be updated.
     * @param candidate The updated candidate object.
     * @return The updated CandidateDAO object.
     * @throws ValidationException If validation of candidate data fails.
     */
	public CandidateDAO update(int newId, Candidate candidate) throws ValidationException {

		CandidateValidator.validate(candidate);

		candidatedao.update(newId, candidate);

		return candidatedao;

	}

	/**
     * Deletes a candidate from the database by its ID.
     *
     * @param Id The ID of the candidate to be deleted.
     * @return A string indicating the result of the deletion operation.
     */
	public String delete(int Id) {

		candidatedao.delete(Id);
		
		return "Candidate Deleted";

	}

	/**
     * Retrieves a candidate from the database by its ID.
     *
     * @param newId The ID of the candidate to be retrieved.
     * @return The retrieved candidate, or null if not found.
     */
	public Candidate findById(int newId) {

		return candidatedao.findById(newId);

	}


	/**
     * Retrieves a list of all candidates from the database.
     *
     * @return A list of all candidates.
     */
	public List<Candidate> getAll() {

		return candidatedao.findAll();

	}
}

package in.fssa.evotingsystem.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import in.fssa.evotingsystem.dao.CandidateDAO;
import in.fssa.evotingsystem.exception.PersistanceException;
import in.fssa.evotingsystem.exception.ServiceException;
import in.fssa.evotingsystem.exception.ValidationException;
import in.fssa.evotingsystem.model.Candidate;
import in.fssa.evotingsystem.validator.CandidateValidator;
import in.fssa.evotingsystem.validator.ElectionValidator;

/**
 * This class provides services related to managing candidates.
 */
public class CandidateService {

	private CandidateDAO candidateDAO = new CandidateDAO();

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
	 * Creates a new candidate.
	 *
	 * @param candidate The candidate to be created.
	 * @throws ServiceException    If there was an issue creating the candidate.
	 * @throws ValidationException If the provided candidate data is not valid.
	 */
	public void createCandidate(Candidate candidate) throws ServiceException, ValidationException {
		try {
			CandidateValidator.validate(candidate);
			candidateDAO.create(candidate);
		} catch (PersistanceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Updates a candidate's information based on the provided new ID and candidate data.
	 *
	 * @param newId The new ID for the candidate.
	 * @param candidate The updated candidate object to be validated and stored.
	 * @throws ServiceException If there's a service-level issue during the update process.
	 * @throws ValidationException If the candidate data is invalid for updating.
	 */
	public void updateCandidate(int newId, Candidate candidate) throws ServiceException, ValidationException {
	    try {
	        CandidateValidator.isCandidateIdExistsForUpdate(newId, candidate.getCandidateId());
	        CandidateValidator.isIdExists(newId);
	        CandidateValidator.validateUpdate(newId, candidate);
	        candidateDAO.update(newId, candidate);
	    } catch (PersistanceException e) {
	        throw new ServiceException(e.getMessage());
	    }
	}


	/**
	 * Deletes a candidate by ID.
	 *
	 * @param id The ID of the candidate to be deleted.
	 * @throws ServiceException    If there was an issue deleting the candidate.
	 * @throws ValidationException If the provided candidate ID is not valid.
	 */
	public void deleteCandidate(int id) throws ServiceException, ValidationException {
		try {
			CandidateValidator.isIdExists(id);
			CandidateValidator.validateId(id);

			candidateDAO.delete(id);
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Finds a candidate by their ID.
	 *
	 * @param newId The ID of the candidate to be found.
	 * @return The found candidate, or null if not found.
	 * @throws ServiceException    If there was an issue finding the candidate.
	 * @throws ValidationException If the provided candidate ID is not valid.
	 */
	public Candidate findByCandidateId(int newId) throws ServiceException, ValidationException {
		try {
			ElectionValidator.validateId(newId);
			return candidateDAO.findById(newId);
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Retrieves a list of all candidates.
	 *
	 * @return The list of all candidates.
	 * @throws ServiceException If there was an issue retrieving the candidates.
	 */
	public List<Candidate> getAllCandidates() throws ServiceException {
		try {
			return candidateDAO.findAll();
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}

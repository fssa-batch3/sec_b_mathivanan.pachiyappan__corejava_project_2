package in.fssa.evotingsystem.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.google.protobuf.ServiceException;

import in.fssa.evotingsystem.dao.CandidateDAO;
import in.fssa.evotingsystem.exception.PersistanceException;
import in.fssa.evotingsystem.exception.ValidationException;
import in.fssa.evotingsystem.model.Candidate;
import in.fssa.evotingsystem.validator.CandidateValidator;
import in.fssa.evotingsystem.validator.ElectionValidator;

/**
 * This class provides services related to managing candidates.
 */
public class CandidateService {

    private CandidateDAO candidatedao = new CandidateDAO();

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
     * @throws ServiceException If there was an issue creating the candidate.
     * @throws ValidationException If the provided candidate data is not valid.
     */
    public void create(Candidate candidate) throws ServiceException, ValidationException {
        try {
            CandidateValidator.validate(candidate);
            candidatedao.create(candidate);
        } catch (PersistanceException e) {
            throw new ServiceException("Failed to Create Candidate");
        }
    }

    /**
     * Updates an existing candidate's information.
     *
     * @param newId The ID of the candidate to be updated.
     * @param candidate The updated candidate information.
     * @throws ServiceException If there was an issue updating the candidate.
     * @throws ValidationException If the provided candidate data is not valid.
     */
    public void update(int newId, Candidate candidate) throws ServiceException, ValidationException {
        try {
            CandidateValidator.isIdExists(newId);
            CandidateValidator.validate(candidate);
            candidatedao.update(newId, candidate);
        } catch (PersistanceException e) {
            throw new ServiceException("Failed to Update Candidate");
        }
    }

    /**
     * Deletes a candidate by ID.
     *
     * @param id The ID of the candidate to be deleted.
     * @throws ServiceException If there was an issue deleting the candidate.
     * @throws ValidationException If the provided candidate ID is not valid.
     */
    public void delete(int id) throws ServiceException, ValidationException {
        try {
            ElectionValidator.validateId(id);
            ElectionValidator.isIdExists(id);
            candidatedao.delete(id);
        } catch (PersistanceException e) {
            throw new ServiceException("Failed to Delete Candidate");
        }
    }

    /**
     * Finds a candidate by their ID.
     *
     * @param newId The ID of the candidate to be found.
     * @return The found candidate, or null if not found.
     * @throws ServiceException If there was an issue finding the candidate.
     * @throws ValidationException If the provided candidate ID is not valid.
     */
    public Candidate findById(int newId) throws ServiceException, ValidationException {
        try {
            ElectionValidator.validateId(newId);
            return candidatedao.findById(newId);
        } catch (PersistanceException e) {
            throw new ServiceException("Failed to Find Candidate");
        }
    }

    /**
     * Retrieves a list of all candidates.
     *
     * @return The list of all candidates.
     * @throws ServiceException If there was an issue retrieving the candidates.
     */
    public List<Candidate> getAll() throws ServiceException {
        try {
            return candidatedao.findAll();
        } catch (PersistanceException e) {
            throw new ServiceException("Failed to List All Candidates");
        }
    }
}

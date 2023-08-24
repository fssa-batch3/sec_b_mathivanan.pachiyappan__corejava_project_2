package in.fssa.evotingsystem.validator;

import in.fssa.evotingsystem.dao.ElectionDAO;
import in.fssa.evotingsystem.exception.PersistanceException;
import in.fssa.evotingsystem.exception.ValidationException;
import in.fssa.evotingsystem.model.Election;
import in.fssa.evotingsystem.util.StringUtil;

import java.time.LocalDate;

/**
 * The ElectionValidator class provides methods to validate attributes of Election entities.
 */
public class ElectionValidator {

	/**
     * Validates the attributes of an Election entity.
     *
     * @param newElection The Election object to be validated.
     * @throws ValidationException If the Election object or its attributes are invalid.
     */
    public static void validate(Election newElection) throws ValidationException {

        if (newElection == null) {
            throw new ValidationException("Invalid Election Input");
        }

        if (newElection.getTalukId() <= 0 || newElection.getTalukId() >= 999999999) {
            throw new ValidationException("Invalid Taluk ID");
        }

        StringUtil.rejectIfInvalidString(newElection.getElectionName(), "Election Name");
        StringUtil.rejectIfInvalidString(newElection.getBoothAddress(), "Booth Address");

        validateDate(newElection.getElectionDate()); // Validate the election date
    }

    /**
     * Validates whether an Election entity with the given ID exists.
     *
     * @param id The ID of the Election entity to check.
     * @throws ValidationException If the ID is invalid or the Election does not exist.
     * @throws PersistanceException 
     */
    public static void isIdExists(int id) throws ValidationException {
        validateId(id);

        ElectionDAO electiondao = new ElectionDAO();
        Election election;
		try {
			election = electiondao.findById(id);
		} catch (PersistanceException e) {
			throw new ValidationException(e.getMessage());
		}

        if (election == null) {
			throw new ValidationException("Election not exists");
        }
    }

    /**
     * Validates an Election ID.
     *
     * @param id The ID of the Election entity to be validated.
     * @throws ValidationException If the ID is invalid.
     */
    public static void validateId(int id) throws ValidationException {
        if (id < 1) {
            throw new ValidationException("ID can not be 0 or negative");
        }
    }

    /**
     * Validates an Election date to ensure it's not null and not in the past.
     *
     * @param date The Election date to be validated.
     * @throws ValidationException If the date is invalid (null or in the past).
     */
    public static void validateDate(LocalDate date) throws ValidationException {
        LocalDate currentDate = LocalDate.now();

        if (date == null || date.isBefore(currentDate)) {
            throw new ValidationException("Invalid Election Date");
        }
    }
}

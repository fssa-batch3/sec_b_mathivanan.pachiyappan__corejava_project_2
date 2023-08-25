package in.fssa.evotingsystem.validator;

import java.time.LocalDate;

import in.fssa.evotingsystem.dao.CandidateDAO;
import in.fssa.evotingsystem.exception.PersistanceException;
import in.fssa.evotingsystem.exception.ValidationException;
import in.fssa.evotingsystem.model.Candidate;
import in.fssa.evotingsystem.util.StringUtil;

/**
 * The CandidateValidator class provides methods to validate attributes of
 * Candidate entities.
 */
public class CandidateValidator {

	/**
	 * Validates the attributes of a Candidate entity.
	 *
	 * @param newCandidate The Candidate object to be validated.
	 * @throws ValidationException If the Candidate object or its attributes are
	 *                             invalid.
	 */
	public static void validate(Candidate newCandidate) throws ValidationException {

		if (newCandidate == null) {
			throw new ValidationException("Invalid Candidate Input");
		}

		if (newCandidate.getElectionId() <= 0 || newCandidate.getElectionId() >= 999999999) {
			throw new ValidationException("Invalid Election ID");
		}

		if (newCandidate.getCandidateId() < 1) {
			throw new ValidationException("Candidate ID cannot be 0 or negative");
		}

		isCandidateIdExists(newCandidate.getCandidateId());

		StringUtil.rejectIfInvalidString(newCandidate.getCandidateName(), "Candidate Name");

		validateDate(newCandidate.getCreatedAt());
	}

	/**
	 * Validates whether a Candidate entity with the given ID exists.
	 *
	 * @param id The ID of the Candidate entity to check.
	 * @throws ValidationException If the ID is invalid or the Candidate does not
	 *                             exist.
	 */
	public static void isIdExists(int id) throws ValidationException {
		validateId(id);

		CandidateDAO candidateDAO = new CandidateDAO();
		Candidate candidate;
		try {
			candidate = candidateDAO.findById(id);
		} catch (PersistanceException e) {
			throw new ValidationException(e.getMessage());
		}

		if (candidate == null) {
			throw new ValidationException("Candidate not exists");
		}
	}

	/**
	 * Validates a Candidate ID.
	 *
	 * @param id The ID of the Candidate entity to be validated.
	 * @throws ValidationException If the ID is invalid.
	 */
	public static void validateId(int id) throws ValidationException {
		if (id < 1) {
			throw new ValidationException("ID cannot be 0 or negative");
		}
	}

	public static void isCandidateIdExists(int candidateId) throws ValidationException {

		CandidateDAO candidatedao = new CandidateDAO();
		try {
			Candidate existingUser = candidatedao.findByCandidateId(candidateId);
			if (existingUser != null) {
				throw new ValidationException("Candidate ID already exists");
			}
		} catch (PersistanceException e) {
			throw new ValidationException(e.getMessage());
		}
	}

	/**
	 * Validates a Candidate's creation date to ensure it's not null and not in the
	 * past.
	 *
	 * @param date The creation date of the Candidate to be validated.
	 * @throws ValidationException If the date is invalid (null or in the past).
	 */
	public static void validateDate(LocalDate date) throws ValidationException {
		LocalDate currentDate = LocalDate.now();

		if (date == null || date.isBefore(currentDate)) {
			throw new ValidationException("Invalid Candidate Date");
		}
	}

}

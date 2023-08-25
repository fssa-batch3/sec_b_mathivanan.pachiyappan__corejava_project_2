package in.fssa.evotingsystem.validator;

import in.fssa.evotingsystem.dao.TalukDAO;
import in.fssa.evotingsystem.exception.PersistanceException;
import in.fssa.evotingsystem.exception.ValidationException;
import in.fssa.evotingsystem.model.Taluk;
import in.fssa.evotingsystem.util.StringUtil;

/**
 * The TalukValidator class provides methods to validate attributes of Taluk entities.
 */

public class TalukValidator {
	
	/**
     * Validates the attributes of a Taluk entity.
     *
     * @param newTaluk The Taluk object to be validated.
     * @throws ValidationException If the Taluk object or its attributes are invalid.
     */
	public static void validate(Taluk newTaluk) throws ValidationException {

		if (newTaluk == null) {
			throw new ValidationException("Invalid Taluk Input");
		}
		
		if (newTaluk.getId() <= 600000001 && newTaluk.getId() >= 999999999) {

			throw new ValidationException("Invalid Taluk ID");
		}
		
		StringUtil.rejectIfInvalidString(newTaluk.getTalukName(), "Taluk Name");

	}
	
	/**
	 * Checks if a Taluk ID exists in the database.
	 *
	 * @param id The ID of the Taluk to check.
	 * @throws ValidationException If the ID is not valid or if the Taluk does not
	 *                             exist.
	 */
	public static void isIdExists(int id) throws ValidationException {

		validateId(id);

		TalukDAO talukDAO = new TalukDAO();
		Taluk taluk = new Taluk();

		try {
		      taluk = talukDAO.findById(id);
		} catch (PersistanceException e) {
			throw new ValidationException(e.getMessage());
		}

		if (taluk == null) {
			throw new ValidationException("Taluk not exsists");
		}

	}
	
	/**
	 * Validates a taluk ID.
	 *
	 * @param id The ID of the Taluk to validate.
	 * @throws ValidationException If the ID is not valid.
	 */
	public static void validateId(int id) throws ValidationException {

		if (id < 1) {

			throw new ValidationException("Id can not be 0 or negative");
		}

	}

}

package in.fssa.evotingsystem.validator;

import in.fssa.evotingsystem.dao.UserDAO;
import in.fssa.evotingsystem.exception.PersistanceException;
import in.fssa.evotingsystem.exception.ValidationException;
import in.fssa.evotingsystem.model.User;
import in.fssa.evotingsystem.util.StringUtil;

/**
 * The UserValidator class provides methods to validate User entity-related
 * operations and attributes.
 */
public class UserValidator {

	private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,10}$";

	/**
	 * Validates the attributes of a User entity.
	 *
	 * @param newUser The User object to be validated.
	 * @throws ValidationException If the User object or its attributes are invalid.
	 */
	public static void validate(User newUser) throws ValidationException {

		if (newUser == null) {
			throw new ValidationException("Invalid User Input");
		}

		if (newUser.getPassword() == null || newUser.getPassword().trim().isEmpty()) {
			throw new ValidationException("Password cannot be Null or Empty");
		}

		if (StringUtil.isInvalidString(newUser.getPassword())) {
			throw new ValidationException("Invalid Password");
		}

		if (!isValidPassword(newUser.getPassword())) {
			throw new ValidationException("Password must be at least 8 characters ex: Njcat#10");
		}

		if (newUser.getPhoneNumber() <= 600000001L || newUser.getPhoneNumber() >= 9999999999L) {
			throw new ValidationException("Invalid Phone Number");
		}

		isPhoneNumberExists(newUser.getPhoneNumber());

		if (newUser.getVoterId() <= 0 || newUser.getVoterId() >= 999999999) {
			throw new ValidationException("Invalid Voter ID");
		}

		if (newUser.getTalukId() <= 0) {
			throw new ValidationException("Invalid Taluk ID");
		}

		StringUtil.rejectIfInvalidString(newUser.getPassword(), "Password");
		StringUtil.rejectIfInvalidString(newUser.getAddress(), "Address");
	}

	/**
	 * Checks if a user ID exists in the database.
	 *
	 * @param id The ID of the User to check.
	 * @throws ValidationException If the ID is not valid or if the User does not
	 *                             exist.
	 */
	public static void isIdExists(int id) throws ValidationException {

		validateId(id);

		UserDAO userDAO = new UserDAO();
		User user = new User();

		try {
			user = userDAO.findById(id);
		} catch (PersistanceException e) {
			throw new ValidationException(e.getMessage());
		}

		if (user == null) {
			throw new ValidationException("User not exists");
		}

	}

	/**
	 * Validates a user ID.
	 *
	 * @param id The ID of the User to validate.
	 * @throws ValidationException If the ID is not valid.
	 */
	public static void validateId(int id) throws ValidationException {

		if (id < 1) {

			throw new ValidationException("Id can not be 0 or negative");
		}

	}

	/**
	 * Checks if a phone number already exists in the database.
	 *
	 * @param phoneNumber The phone number to check.
	 * @throws ValidationException If the phone number is not valid or if it already
	 *                             exists.
	 */
	public static void isPhoneNumberExists(long phoneNumber) throws ValidationException {

		UserDAO userDAO = new UserDAO();
		try {
			User existingUser = userDAO.findByPhoneNumber(phoneNumber);
			if (existingUser != null) {
				throw new ValidationException("Phone number already exists");
			}
		} catch (PersistanceException e) {
			throw new ValidationException(e.getMessage());
		}
	}

	private static boolean isValidPassword(String password) {
		return password.matches(PASSWORD_PATTERN);
	}

	/**
	 * Validates the attributes of an updated User entity.
	 *
	 * @param updatedUser The updated User object to be validated.
	 * @throws ValidationException If the updated User object or its attributes are
	 *                             invalid.
	 */
	public static void validateUpdate(int id,User updatedUser) throws ValidationException {

		if (updatedUser == null) {
			throw new ValidationException("Invalid User Input for Update");
		}

		if (id < 1) {
			throw new ValidationException("Invalid User ID for Update");
		}

		if (updatedUser.getPassword() != null && !updatedUser.getPassword().trim().isEmpty()) {
			if (StringUtil.isInvalidString(updatedUser.getPassword())) {
				throw new ValidationException("Invalid Password for Update");
			}
			if (!isValidPassword(updatedUser.getPassword())) {
				throw new ValidationException("Password for Update must be at least 8 characters");
			}
		}

		if (updatedUser.getPhoneNumber() <= 600000001L || updatedUser.getPhoneNumber() >= 9999999999L) {
			throw new ValidationException("Invalid Phone Number for Update");
		}

		isPhoneNumberExistsForUpdate(updatedUser.getId(), updatedUser.getPhoneNumber());

		if (updatedUser.getVoterId() <= 0 || updatedUser.getVoterId() >= 999999999) {
			throw new ValidationException("Invalid Voter ID for Update");
		}

		if (updatedUser.getTalukId() <= 0) {
			throw new ValidationException("Invalid Taluk ID for Update");
		}

		StringUtil.rejectIfInvalidString(updatedUser.getAddress(), "Address for Update");
	}

	/**
	 * Checks if a phone number already exists in the database for a user other than
	 * the given ID.
	 *
	 * @param userId      The ID of the user being updated.
	 * @param phoneNumber The phone number to check.
	 * @throws ValidationException If the phone number is not valid or if it already
	 *                             exists.
	 */
	public static void isPhoneNumberExistsForUpdate(int userId, long phoneNumber) throws ValidationException {

		UserDAO userDAO = new UserDAO();
		try {
			User existingUser = userDAO.findByPhoneNumber(phoneNumber);
			if (existingUser != null && existingUser.getId() != userId) {
				throw new ValidationException("Phone number already exists for another user");
			}
		} catch (PersistanceException e) {
			throw new ValidationException(e.getMessage());
		}
	}

}

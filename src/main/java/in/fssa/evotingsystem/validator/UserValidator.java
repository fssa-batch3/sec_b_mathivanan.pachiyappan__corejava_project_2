package in.fssa.evotingsystem.validator;

import java.util.Objects;

import in.fssa.evotingsystem.dao.UserDAO;
import in.fssa.evotingsystem.exception.ValidationException;
import in.fssa.evotingsystem.model.User;
import in.fssa.evotingsystem.util.StringUtil;

/**
 * The UserValidator class provides methods to validate User entity-related
 * operations and attributes.
 */
public class UserValidator {

	private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

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

		if (Objects.isNull(newUser.getPassword())) {
			throw new ValidationException("Password cannot be Null or Empty");
		}

		if (newUser.getPassword().equals("")) {
			throw new ValidationException("Password cannot be Null or Empty");
		}

		if (StringUtil.isInvalidString(newUser.getPassword())) {
			throw new ValidationException("Invalid Password");
		}

		if (!isValidPassword(newUser.getPassword())) {
			throw new ValidationException("Password Pattern Mismatch");
		}

		if (newUser.getPhoneNumber() <= 600000001L || newUser.getPhoneNumber() >= 9999999999L) {
			throw new ValidationException("Invalid Phone Number");
		}

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

		UserDAO ud = new UserDAO();
		User user = new User();

		user = ud.findById(id);

		if (user == null) {
			throw new ValidationException("User not exsists");
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

			throw new ValidationException("id can not be 0 or negative");
		}

	}

	private static boolean isValidPassword(String password) {
		return password.matches(PASSWORD_PATTERN);
	}

}

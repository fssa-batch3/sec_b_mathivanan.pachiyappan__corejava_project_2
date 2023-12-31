package in.fssa.evotingsystem.service;

import java.util.List;

import in.fssa.evotingsystem.dao.UserDAO;
import in.fssa.evotingsystem.exception.PersistanceException;
import in.fssa.evotingsystem.exception.ServiceException;
import in.fssa.evotingsystem.exception.ValidationException;
import in.fssa.evotingsystem.model.User;
import in.fssa.evotingsystem.validator.UserValidator;

/**
 * This class provides services related to managing users.
 */

public class UserService {

	private UserDAO userDAO = new UserDAO();

	/**
	 * Creates a new user.
	 *
	 * @param user The user to be created.
	 * @throws ServiceException    If there was an issue creating the user.
	 * @throws ValidationException If the provided user data is not valid.
	 */
	public void createUser(User user) throws ServiceException, ValidationException {
		try {
			UserValidator.validate(user);
			userDAO.create(user);
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Updates an existing user's information.
	 *
	 * @param newId   The ID of the user to be updated.
	 * @param newUser The updated user information.
	 * @throws ServiceException    If there was an issue updating the user.
	 * @throws ValidationException If the provided user data is not valid.
	 */
	public void updateUser(int newId, User newUser) throws ServiceException, ValidationException {

		try {
			UserValidator.isPhoneNumberExistsForUpdate(newId, newUser.getPhoneNumber());
			UserValidator.isIdExists(newId);
//			UserValidator.validateUpdate(newId,newUser);

			userDAO.update(newId, newUser);
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Deletes a user by ID.
	 *
	 * @param id The ID of the user to be deleted.
	 * @throws ServiceException    If there was an issue deleting the user.
	 * @throws ValidationException If the provided user ID is not valid.
	 */
	public void deleteUser(int id) throws ServiceException, ValidationException {

		try {
			UserValidator.validateId(id);
			UserValidator.isIdExists(id);
			userDAO.delete(id);
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Finds a user by their ID.
	 *
	 * @param id The ID of the user to be found.
	 * @return The found user, or null if not found.
	 * @throws ServiceException    If there was an issue finding the user.
	 * @throws ValidationException If the provided user ID is not valid.
	 */
	public User findByUserId(int id) throws ServiceException, ValidationException {
		try {
			UserValidator.isIdExists(id);
			return userDAO.findById(id);
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public User findByPhoneNumber(long number) throws ServiceException, ValidationException {
		try {
			UserValidator.isPhoneNumberExists(number);
			return userDAO.findByPhoneNumber(number);
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Retrieves a list of all users.
	 *
	 * @return The list of all users.
	 * @throws ServiceException If there was an issue retrieving the users.
	 */
	public List<User> getAllUsers() throws ServiceException {
		try {
			return userDAO.findAll();
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public User loginUser(long phoneNumber, String password) throws ServiceException, ValidationException {
		try {
			// Validate the phone number and password
			UserValidator.validatePhoneNumber(phoneNumber);
			UserValidator.validatePassword(password);

			// Check if a user with the provided phone number exists in the database
			User user = userDAO.findByPhoneNumber(phoneNumber);

			if (user != null) {
				
				// If the user exists, check if the provided password matches the stored
				// password
				if (password.equals(user.getPassword())) {
					// Authentication successful, return the user
					return user;
				} else {
					// Password does not match
					throw new ServiceException("Incorrect password");
				}
			} else {
				// User with the provided phone number does not exist
				throw new ServiceException("User not found");
			}
		} catch (PersistanceException e) {
			System.out.println("User not found");
			throw new ServiceException(e.getMessage());
		}
	}
}

package in.fssa.evotingsystem.service;

import java.util.List;

import in.fssa.evotingsystem.dao.UserDAO;
import in.fssa.evotingsystem.exception.ValidationException;
import in.fssa.evotingsystem.model.User;
import in.fssa.evotingsystem.validator.UserValidator;

/**
 * Service class that provides methods to interact with user-related operations.
 */
public class UserService {

    private UserDAO userdao = new UserDAO();

    /**
     * Creates a new user and stores it in the database.
     *
     * @param user The user object to be created.
     * @return The created user.
     * @throws ValidationException If validation of user data fails.
     */
    public User create(User user) throws ValidationException {
        UserValidator.validate(user);
        userdao.create(user);
        return user;
    }

    /**
     * Retrieves the count of users in the database.
     *
     * @return The count of users.
     */
    public int count() {
        return userdao.count();
    }

    /**
     * Updates an existing user's information in the database.
     *
     * @param newId The ID of the user to be updated.
     * @param newUser The updated user object.
     * @throws ValidationException If validation of user data or ID existence fails.
     */
    public void update(int newId, User newUser) throws ValidationException {
        UserValidator.isIdExists(newId);
        UserValidator.validate(newUser);
        userdao.update(newId, newUser);
    }

    /**
     * Deletes a user from the database by their ID.
     *
     * @param id The ID of the user to be deleted.
     * @throws ValidationException If validation of the user's ID existence fails.
     */
    public void delete(int id) throws ValidationException {
        UserValidator.isIdExists(id);
        userdao.delete(id);
    }

    /**
     * Retrieves a user from the database by their ID.
     *
     * @param id The ID of the user to be retrieved.
     * @return The retrieved user.
     * @throws ValidationException If validation of the user's ID fails.
     */
    public User findById(int id) throws ValidationException {
        UserValidator.validateId(id);
        return userdao.findById(id);
    }

    /**
     * Retrieves a list of all users from the database.
     *
     * @return A list of all users.
     */
    public List<User> getAll() {
        return userdao.findAll();
    }
}

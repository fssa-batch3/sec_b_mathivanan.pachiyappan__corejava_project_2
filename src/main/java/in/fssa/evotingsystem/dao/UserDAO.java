package in.fssa.evotingsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.evotingsystem.Interface.UserInterface;
import in.fssa.evotingsystem.exception.PersistanceException;
import in.fssa.evotingsystem.model.User;
import in.fssa.evotingsystem.util.ConnectionUtil;

/**
 * The UserDAO class provides methods to interact with the database and perform
 * operations related to User entities.
 */
public class UserDAO implements UserInterface {

	/**
	 * Creates a new User entity in the database.
	 *
	 * @param user The User object to be created.
	 * @throws PersistanceException If there's an issue with the database operation.
	 */
	@Override
	public void create(User user) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;

		// TODO table name
		try {
			String query = "INSERT INTO users (id, phone_number, password, address, voter_id, taluk_id) VALUES ( ?, ?, ?, ?, ?, ? );";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, user.getId());
			ps.setLong(2, user.getPhoneNumber());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getAddress());
			ps.setInt(5, user.getVoterId());
			ps.setInt(6, user.getTalukId());

			ps.executeUpdate();

			System.out.println("User Successfully Created :");

		} catch (SQLException e) {
			if (e.getMessage().contains("Duplicate entry")) {
				throw new PersistanceException("Duplicate constraint");
			} else {
				System.out.println(e.getMessage());
				throw new PersistanceException(e);
			}

		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

	/**
	 * Marks a User entity as inactive in the database.
	 *
	 * @param newId The ID of the User to be marked as inactive.
	 * @throws PersistanceException If there's an issue with the database operation.
	 */
	@Override
	public void delete(int newId) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE users SET is_active = 0 WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
//	        ps.setBoolean(1, false);
			ps.setInt(1, newId);

			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("User with ID " + newId + " set as inactive.");
			} else {
				System.out.println("User with ID " + newId + " not found.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e);
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	/**
	 * Updates a User entity's information in the database.
	 *
	 * @param id      The ID of the User to be updated.
	 * @param newUser The updated User object.
	 * @throws PersistanceException If there's an issue with the database operation.
	 */
	@Override
	public void update(int id, User newUser) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE users SET password = ?, address = ?, taluk_id = ? Where is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, newUser.getAddress());
			ps.setString(2, newUser.getPassword());
			ps.setInt(3, newUser.getTalukId());

			ps.executeUpdate();

			System.out.println("User Successfully Updated");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e);
		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

	/**
	 * Find a user by their phone number.
	 *
	 * @param phoneNumber The phone number to search for.
	 * @return The User object if found, null otherwise.
	 * @throws PersistanceException If there's an issue with database access.
	 */
	public User findByPhoneNumber(long userPhoneNo) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		User user = null;

		try {
			String query = "SELECT * FROM users  WHERE is_active = 1 and phone_number = ? ";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, userPhoneNo);
			rs = ps.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setPhoneNumber(rs.getLong("phone_number"));
				user.setAddress(rs.getString("address"));
				user.setVoterId(rs.getInt("voter_id"));
				user.setTalukId(rs.getInt("taluk_id"));
				user.setActive(rs.getBoolean("is_active"));
				user.setPassword(rs.getString("password"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e);
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return user;
	}

	/**
	 * Retrieves a User entity from the database based on the provided user ID.
	 *
	 * @param userId The ID of the User to retrieve.
	 * @return The matched User entity or null if not found.
	 * @throws PersistanceException If there's an issue with the database operation.
	 */
	public User findById(int userId) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User matchedUser = null;

		try {
			String query = "SELECT * FROM users WHERE id = ? AND is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, userId);
			rs = ps.executeQuery();

			if (rs.next()) {
				matchedUser = new User();
				matchedUser.setId(rs.getInt("id"));
				matchedUser.setPhoneNumber(rs.getLong("phone_number"));
				matchedUser.setAddress(rs.getString("address"));
				matchedUser.setVoterId(rs.getInt("voter_id"));
				matchedUser.setTalukId(rs.getInt("taluk_id"));
				matchedUser.setActive(rs.getBoolean("is_active"));
				matchedUser.setPassword(rs.getString("password"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e);
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		System.out.println(matchedUser);
		return matchedUser;
	}

	/**
	 * Retrieves a list of active User entities from the database.
	 *
	 * @return A list of active User entities.
	 * @throws PersistanceException If there's an issue with the database operation.
	 */
	public List<User> findAll() throws PersistanceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<User> userList = new ArrayList<User>();

		try {
			String query = "SELECT * from users where is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				User newUser = new User();
				newUser.setId(rs.getInt("id"));
				newUser.setPhoneNumber(rs.getLong("phone_number"));
				newUser.setAddress(rs.getString("address"));
				newUser.setVoterId(rs.getInt("voter_id"));
				newUser.setTalukId(rs.getInt("taluk_id"));
				newUser.setActive(rs.getBoolean("is_active"));
				newUser.setPassword(rs.getString("password"));

				userList.add(newUser);
			}

		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e);

		} finally {

			ConnectionUtil.close(con, ps);

		}

		return userList;

	}

	public void changeActive(int newId) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE users SET is_active = 1 WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
//	        ps.setBoolean(1, false);
			ps.setInt(1, newId);

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e);
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

}

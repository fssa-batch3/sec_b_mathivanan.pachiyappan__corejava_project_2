package in.fssa.evotingsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.evotingsystem.Interface.UserInterface;
import in.fssa.evotingsystem.model.User;
import in.fssa.evotingsystem.util.ConnectionUtil;

public class UserDAO implements UserInterface {
	
	@Override
	public void create(User user) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO User (id, phone_number, password, address, voter_id, taluk_id) VALUES ( ?, ?, ?, ?, ?, ? );";
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
				throw new RuntimeException("Duplicate constraint");
			} else {
				System.out.println(e.getMessage());
				throw new RuntimeException(e);
			}

		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

	@Override
	public void delete(int newId) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE User SET is_active = false WHERE id = ?";
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
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	@Override
	public void update(int id, User newUser) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE User SET address = ?,taluk_id = ? Where id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, newUser.getAddress());
			ps.setString(2, newUser.getPassword());
			ps.setInt(3, newUser.getTalukId());
			ps.setInt(4, id);

			ps.executeUpdate();

			System.out.println("User Successfully Updated");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

	public User findById(int userId) throws RuntimeException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User matchedUser = null;

		try {
			String query = "SELECT * FROM User WHERE id = ? AND is_active = 1";
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
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		System.out.println(matchedUser);
		return matchedUser;
	}

	// JDBC Connections

	public List<User> findAll() throws RuntimeException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<User> userList = new ArrayList<User>();

		try {
			String query = "SELECT * from User where is_active = 1";
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
			throw new RuntimeException(e);

		} finally {

			ConnectionUtil.close(con, ps);

		}

		return userList;

	}

	public int count() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;

		List<User> userList = new ArrayList<User>();
		try {
			String query = "SELECT * FROM User Where is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return count;

	}

}

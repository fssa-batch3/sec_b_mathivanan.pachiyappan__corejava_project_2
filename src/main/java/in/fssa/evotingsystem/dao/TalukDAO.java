package in.fssa.evotingsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.evotingsystem.exception.PersistanceException;
import in.fssa.evotingsystem.interfaces.TalukInterface;
import in.fssa.evotingsystem.model.Taluk;
import in.fssa.evotingsystem.util.ConnectionUtil;

/**
 * The TalukDAO class provides methods to interact with the database and perform operations related to Taluk entities.
 */
public class TalukDAO implements TalukInterface {

	/**
     * Creates a new Taluk entity in the database.
     *
     * @param taluk The Taluk object to be created.
     * @throws PersistanceException If there's an issue with the database operation.
     */
	@Override
	public void create(Taluk taluk) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO taluks (id, taluk_name) VALUES ( ?, ? );";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, taluk.getId());
			ps.setString(2, taluk.getTalukName());

			ps.executeUpdate();

			System.out.println("Taluk Successfully Created :");

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
     * Marks a Taluk entity as inactive in the database.
     *
     * @param newId The ID of the Taluk to be marked as inactive.
     * @throws PersistanceException If there's an issue with the database operation.
     */
	@Override
	public void delete(int newId) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE taluks SET is_active = false WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
//	        ps.setBoolean(1, false);
			ps.setInt(1, newId);

			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Taluk with ID " + newId + " set as inactive.");
			} else {
				System.out.println("Taluk with ID " + newId + " not found.");
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
     * Updates a Taluk entity's information in the database.
     *
     * @param id The ID of the Taluk to be updated.
     * @param newTaluk The updated Taluk object.
     * @throws PersistanceException If there's an issue with the database operation.
     */
	@Override
	public void update(int id, Taluk newTaluk) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE taluks SET taluk_name = ? Where id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, newTaluk.getTalukName());
			ps.setInt(2, id);
			
			ps.executeUpdate();

			System.out.println("Taluk Name Successfully Updated");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e);
		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

	/**
     * Retrieves a Taluk entity from the database based on the provided taluk ID.
     *
     * @param talukId The ID of the Taluk to retrieve.
     * @return The matched Taluk entity or null if not found.
     * @throws PersistanceException If there's an issue with the database operation.
     */
	public Taluk findById(int talukId) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Taluk matchedTaluk = null;

		try {
			String query = "SELECT taluk_name FROM taluks WHERE id = ? AND is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, talukId);
			rs = ps.executeQuery();

			if (rs.next()) {
				matchedTaluk = new Taluk();
				matchedTaluk.setTalukName(rs.getString("taluk_name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e);
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		System.out.println(matchedTaluk);
		return matchedTaluk;
	}

	/**
     * Retrieves a list of Taluk entities from the database.
     *
     * @return A list of Taluk entities.
     * @throws PersistanceException If there's an issue with the database operation.
     */
	public List<Taluk> findAll() throws PersistanceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Taluk> talukList = new ArrayList<Taluk>();

		try {
			String query = "SELECT taluk_name from taluks where is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				Taluk newTaluk = new Taluk();
				newTaluk.setTalukName(rs.getString("taluk_name"));

				talukList.add(newTaluk);
			}

		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e);

		} finally {

			ConnectionUtil.close(con, ps);

		}

		return talukList;

	}

	public void changeActive(int newId) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE taluks SET is_active = 1 WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
//	        ps.setBoolean(1, false);
			ps.setInt(1, newId);

			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Taluk with ID " + newId + " set as inactive.");
			} else {
				System.out.println("Taluk with ID " + newId + " not found.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e);
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}
}

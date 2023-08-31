package in.fssa.evotingsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import in.fssa.evotingsystem.exception.PersistanceException;
import in.fssa.evotingsystem.interfaces.ElectionInterface;
import in.fssa.evotingsystem.model.Election;
import in.fssa.evotingsystem.service.ElectionService;
import in.fssa.evotingsystem.util.ConnectionUtil;

/**
 * The ElectionDAO class provides methods to interact with the database and perform operations related to Election entities.
 */
public class ElectionDAO implements ElectionInterface{
	
	/**
     * Creates a new Election entity in the database.
     *
     * @param election The Election object to be created.
     * @throws PersistanceException If there's an issue with the database operation.
     */
	@Override
	public void create(Election election) throws PersistanceException{
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO elections (id, election_name, booth_address, election_date, taluk_id) VALUES ( ?, ?, ?, ?, ? );";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, election.getId());
			ps.setString(2, election.getElectionName());
			ps.setString(3, election.getBoothAddress());
			
			java.util.Date electionDateUtil = ElectionService.convertDate(election.getElectionDate());
			java.sql.Date electionDateSql = new java.sql.Date(electionDateUtil.getTime());

			ps.setDate(4, electionDateSql);

			
			ps.setInt(5, election.getTalukId());

			ps.executeUpdate();

			System.out.println("Election Successfully Created :");

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
     * Marks an Election entity as inactive in the database.
     *
     * @param newId The ID of the Election to be marked as inactive.
     * @throws PersistanceException If there's an issue with the database operation.
     */
	@Override
	public void delete(int newId) throws PersistanceException{
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE elections SET is_active = false WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
//	        ps.setBoolean(1, false);
			ps.setInt(1, newId);

			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Election with ID " + newId + " set as inactive.");
			} else {
				System.out.println("Election with ID " + newId + " not found.");
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
     * Updates an Election entity's information in the database.
     *
     * @param id The ID of the Election to be updated.
     * @param newElection The updated Election object.
     * @throws PersistanceException If there's an issue with the database operation.
     */
	@Override
	public void update(int id, Election newElection) throws PersistanceException{
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE elections SET booth_address = ?, election_name = ?, election_date = ? Where id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, newElection.getBoothAddress());
			ps.setString(2, newElection.getElectionName());
			
			java.util.Date dueDateUtil = ElectionService.convertDate(newElection.getElectionDate());
			java.sql.Date dueDateSql = new java.sql.Date(dueDateUtil.getTime());

			ps.setDate(3, dueDateSql);
			ps.setInt(4, id);

			ps.executeUpdate();

			System.out.println("Election Successfully Updated");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e);
		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

	 /**
     * Retrieves an Election entity from the database based on the provided Election ID.
     *
     * @param id The ID of the Election to retrieve.
     * @return The matched Election entity or null if not found.
     * @throws PersistanceException If there's an issue with the database operation.
     */
	public Election findById(int Id) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Election matchedElection = null;

		try {
			String query = "SELECT booth_address, election_name, election_date, taluk_id FROM elections WHERE is_active = 1 AND id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, Id);
			rs = ps.executeQuery();

			if (rs.next()) {
				matchedElection = new Election();
				matchedElection.setBoothAddress(rs.getString("booth_address"));
				matchedElection.setElectionName(rs.getString("election_name"));
				LocalDate date = ElectionService.convertSqlDateToLocalDate(rs.getDate("election_date"));
				matchedElection.setElectionDate(date);
				matchedElection.setTalukId(rs.getInt("taluk_id"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e);
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		System.out.println(matchedElection);
		return matchedElection;
	}

	/**
     * Retrieves a list of Election entities from the database.
     *
     * @return A list of Election entities.
     * @throws PersistanceException If there's an issue with the database operation.
     */
	public List<Election> findAll() throws PersistanceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Election> ElectionList = new ArrayList<Election>();

		try {
			String query = "SELECT booth_address, election_name, election_date, taluk_id,id from elections where is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				Election newElection = new Election();
				newElection.setBoothAddress(rs.getString("booth_address"));
				newElection.setElectionName(rs.getString("election_name"));
				LocalDate date = ElectionService.convertSqlDateToLocalDate(rs.getDate("election_date"));
				newElection.setElectionDate(date);
				newElection.setTalukId(rs.getInt("taluk_id"));
				newElection.setId(rs.getInt("id"));

				ElectionList.add(newElection);
			}

		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e);

		} finally {

			ConnectionUtil.close(con, ps);

		}

		return ElectionList;

	}

	public void changeActive(int newId) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE elections SET is_active = 1 WHERE id = ?";
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

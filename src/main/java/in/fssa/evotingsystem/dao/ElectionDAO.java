package in.fssa.evotingsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import in.fssa.evotingsystem.Interface.ElectionInterface;
import in.fssa.evotingsystem.model.Election;
import in.fssa.evotingsystem.service.ElectionService;
import in.fssa.evotingsystem.util.ConnectionUtil;

public class ElectionDAO implements ElectionInterface{
	
	@Override
	public void create(Election election) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO Election (id, election_name, booth_address, election_date, taluk_id) VALUES ( ?, ?, ?, ?, ? );";
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
			String query = "UPDATE Election SET is_active = false WHERE id = ?";
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
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	@Override
	public void update(int id, Election newElection) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE Election SET booth_address = ?,taluk_id = ? Where id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, newElection.getBoothAddress());
			ps.setString(2, newElection.getElectionName());
			
			java.util.Date dueDateUtil = ElectionService.convertDate(newElection.getElectionDate());
			java.sql.Date dueDateSql = new java.sql.Date(dueDateUtil.getTime());

			ps.setDate(2, dueDateSql);
			
			ps.setInt(3, id);

			ps.executeUpdate();

			System.out.println("Election Successfully Updated");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

	public Election findById(int Id) throws RuntimeException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Election matchedElection = null;

		try {
			String query = "SELECT * FROM Election WHERE id = ? AND is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, Id);
			rs = ps.executeQuery();

			if (rs.next()) {
				matchedElection = new Election();
				matchedElection.setId(rs.getInt("id"));
				matchedElection.setBoothAddress(rs.getString("booth_address"));
				matchedElection.setElectionName(rs.getString("election_name"));
				LocalDate date = ElectionService.convertSqlDateToLocalDate(rs.getDate("election_date"));
				matchedElection.setElectionDate(date);
				matchedElection.setTalukId(rs.getInt("taluk_id"));
				matchedElection.setActive(rs.getBoolean("is_active"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		System.out.println(matchedElection);
		return matchedElection;
	}

	// JDBC Connections

	public List<Election> findAll() throws RuntimeException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Election> ElectionList = new ArrayList<Election>();

		try {
			String query = "SELECT * from Election where is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				Election newElection = new Election();
				newElection.setId(rs.getInt("id"));
				newElection.setBoothAddress(rs.getString("booth_address"));
				newElection.setElectionName(rs.getString("election_name"));
				LocalDate date = ElectionService.convertSqlDateToLocalDate(rs.getDate("election_date"));
				newElection.setElectionDate(date);
				newElection.setTalukId(rs.getInt("taluk_id"));
				newElection.setActive(rs.getBoolean("is_active"));

				ElectionList.add(newElection);
			}

		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);

		} finally {

			ConnectionUtil.close(con, ps);

		}

		return ElectionList;

	}

	public int count() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;

		List<Election> ElectionList = new ArrayList<Election>();
		try {
			String query = "SELECT * FROM Election Where is_active = 1";
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

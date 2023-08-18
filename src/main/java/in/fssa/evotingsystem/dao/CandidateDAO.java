package in.fssa.evotingsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import in.fssa.evotingsystem.Interface.CandidateInterface;
import in.fssa.evotingsystem.model.Candidate;
import in.fssa.evotingsystem.service.CandidateService;
import in.fssa.evotingsystem.util.ConnectionUtil;

public class CandidateDAO implements CandidateInterface {

	@Override
	public void create(Candidate candidate) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO Candidate (id, user_id, election_id, created_at, name) VALUES ( ?, ?, ?, ?, ? );";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, candidate.getId());
			ps.setInt(2, candidate.getUserId());
			ps.setInt(3, candidate.getElectionId());
			
			java.util.Date candidateDateUtil = CandidateService.convertDate(candidate.getCreatedAt());
			java.sql.Date candidateDateSql = new java.sql.Date(candidateDateUtil.getTime());

			ps.setDate(4, candidateDateSql);

			ps.setString(5, candidate.getCandidateName());

			ps.executeUpdate();

			System.out.println("Candidate Successfully Created :");

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
			String query = "UPDATE Candidate SET is_active = false WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
//	        ps.setBoolean(1, false);
			ps.setInt(1, newId);

			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Candidate with ID " + newId + " set as inactive.");
			} else {
				System.out.println("Candidate with ID " + newId + " not found.");
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
	public void update(int id, Candidate newCandidate) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE Candidate SET name = ? Where id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, newCandidate.getCandidateName());

			ps.setInt(2, id);

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

	public Candidate findById(int Id) throws RuntimeException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Candidate matchedCandidate = null;

		try {
			String query = "SELECT * FROM Candidate WHERE id = ? AND is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, Id);
			rs = ps.executeQuery();

			if (rs.next()) {
				matchedCandidate = new Candidate();
				matchedCandidate.setId(rs.getInt("id"));
				matchedCandidate.setUserId(rs.getInt("user_id"));
				matchedCandidate.setElectionId(rs.getInt("election_id"));
				matchedCandidate.setCandidateName(rs.getString("name"));
				LocalDate date = CandidateService.convertSqlDateToLocalDate(rs.getDate("created_at"));
				matchedCandidate.setCreatedAt(date);
				matchedCandidate.setActive(rs.getBoolean("is_active"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		System.out.println(matchedCandidate);
		return matchedCandidate;
	}

	// JDBC Connections

	public List<Candidate> findAll() throws RuntimeException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Candidate> CandidateList = new ArrayList<Candidate>();

		try {
			String query = "SELECT * from Candidate where is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				Candidate candidates = new Candidate();
				candidates.setId(rs.getInt("id"));
				candidates.setUserId(rs.getInt("user_id"));
				candidates.setElectionId(rs.getInt("election_id"));
				candidates.setCandidateName(rs.getString("name"));
				LocalDate date = CandidateService.convertSqlDateToLocalDate(rs.getDate("created_at"));
				candidates.setCreatedAt(date);
				candidates.setActive(rs.getBoolean("is_active"));

				CandidateList.add(candidates);
			}

		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);

		} finally {

			ConnectionUtil.close(con, ps);

		}

		return CandidateList;

	}

	public int count() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;

		List<Candidate> CandidateList = new ArrayList<Candidate>();
		try {
			String query = "SELECT * FROM Candidate Where is_active = 1";
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

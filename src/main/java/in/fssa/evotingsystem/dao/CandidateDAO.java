package in.fssa.evotingsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import in.fssa.evotingsystem.exception.PersistanceException;
import in.fssa.evotingsystem.model.Candidate;
import in.fssa.evotingsystem.util.ConnectionUtil;

public class CandidateDAO {

    public void create(Candidate candidate) throws PersistanceException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            String query = "INSERT INTO candidates (user_id, election_id, name, party_name, image_url, created_at) VALUES ( ?, ?, ?, ?, ?, ? )";
            con = ConnectionUtil.getConnection();
            ps = con.prepareStatement(query);

            ps.setInt(1, candidate.getUserId());
            ps.setInt(2, candidate.getElectionId());
            ps.setString(3, candidate.getName());
            ps.setString(4, candidate.getPartyName());
            ps.setString(5, candidate.getImageUrl());
            ps.setDate(6, java.sql.Date.valueOf(candidate.getCreatedAt()));
            
            ps.executeUpdate();

            System.out.println("Candidate Successfully Created");

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

    public void delete(int id) throws PersistanceException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            String query = "UPDATE candidates SET is_active = false WHERE id = ?";
            con = ConnectionUtil.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Candidate with ID " + id + " set as inactive.");
            } else {
                System.out.println("Candidate with ID " + id + " not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new PersistanceException(e);
        } finally {
            ConnectionUtil.close(con, ps);
        }
    }

    public void update(int id, Candidate newCandidate) throws PersistanceException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            String query = "UPDATE candidates SET name = ?, party_name = ?, image_url = ? WHERE id = ?";
            con = ConnectionUtil.getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, newCandidate.getName());
            ps.setString(2, newCandidate.getPartyName());
            ps.setString(3, newCandidate.getImageUrl());
            ps.setInt(4, id);

            ps.executeUpdate();

            System.out.println("Candidate Successfully Updated");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new PersistanceException(e);
        } finally {
            ConnectionUtil.close(con, ps);
        }
    }

    public Candidate findByUserId(int userId) throws PersistanceException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Candidate candidate = null;

        try {
            String query = "SELECT * FROM candidates WHERE is_active = true AND user_id = ?";
            con = ConnectionUtil.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            if (rs.next()) {
                candidate = new Candidate();
                candidate.setId(rs.getInt("id"));
                candidate.setUserId(rs.getInt("user_id"));
                candidate.setElectionId(rs.getInt("election_id"));
                candidate.setName(rs.getString("name"));
                candidate.setPartyName(rs.getString("party_name"));
                candidate.setImageUrl(rs.getString("image_url"));
                LocalDate createdAt = rs.getDate("created_at").toLocalDate();
                candidate.setCreatedAt(createdAt);
                candidate.setActive(rs.getBoolean("is_active"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new PersistanceException(e);
        } finally {
            ConnectionUtil.close(con, ps, rs);
        }

        return candidate;
    }

    public Candidate findById(int id) throws PersistanceException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Candidate candidate = null;

        try {
            String query = "SELECT * FROM candidates WHERE is_active = true AND id = ?";
            con = ConnectionUtil.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                candidate = new Candidate();
                candidate.setId(rs.getInt("id"));
                candidate.setUserId(rs.getInt("user_id"));
                candidate.setElectionId(rs.getInt("election_id"));
                candidate.setName(rs.getString("name"));
                candidate.setPartyName(rs.getString("party_name"));
                candidate.setImageUrl(rs.getString("image_url"));
                LocalDate createdAt = rs.getDate("created_at").toLocalDate();
                candidate.setCreatedAt(createdAt);
                candidate.setActive(rs.getBoolean("is_active"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new PersistanceException(e);
        } finally {
            ConnectionUtil.close(con, ps, rs);
        }

        return candidate;
    }

    public List<Candidate> findAll() throws PersistanceException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Candidate> candidateList = new ArrayList<>();

        try {
            String query = "SELECT * FROM candidates WHERE is_active = true";
            con = ConnectionUtil.getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Candidate candidate = new Candidate();
                candidate.setId(rs.getInt("id"));
                candidate.setUserId(rs.getInt("user_id"));
                candidate.setElectionId(rs.getInt("election_id"));
                candidate.setName(rs.getString("name"));
                candidate.setPartyName(rs.getString("party_name"));
                candidate.setImageUrl(rs.getString("image_url"));
                LocalDate createdAt = rs.getDate("created_at").toLocalDate();
                candidate.setCreatedAt(createdAt);
                candidate.setActive(rs.getBoolean("is_active"));
                candidateList.add(candidate);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new PersistanceException(e);
        } finally {
            ConnectionUtil.close(con, ps, rs);
        }

        return candidateList;
    }

    public void changeActive(int id) throws PersistanceException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            String query = "UPDATE candidates SET is_active = true WHERE id = ?";
            con = ConnectionUtil.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, id);

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

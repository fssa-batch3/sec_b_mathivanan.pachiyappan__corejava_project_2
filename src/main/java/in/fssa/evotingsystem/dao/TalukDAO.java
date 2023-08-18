package in.fssa.evotingsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.evotingsystem.Interface.TalukInterface;
import in.fssa.evotingsystem.model.Taluk;
import in.fssa.evotingsystem.model.User;
import in.fssa.evotingsystem.util.ConnectionUtil;

public class TalukDAO implements TalukInterface {

	@Override
	public void create(Taluk taluk) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO Taluk (id, taluk_name) VALUES ( ?, ? );";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, taluk.getId());
			ps.setString(2, taluk.getTalukName());

			ps.executeUpdate();

			System.out.println("Taluk Successfully Created :");

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
			String query = "UPDATE Taluk SET is_active = false WHERE id = ?";
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
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	@Override
	public void update(int id, Taluk newTaluk) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE Taluk SET taluk_name = ? Where id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, newTaluk.getTalukName());
			ps.setInt(2, id);

			ps.executeUpdate();

			System.out.println("Taluk Name Successfully Updated");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

	public Taluk findById(int talukId) throws RuntimeException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Taluk matchedTaluk = null;

		try {
			String query = "SELECT * FROM Taluk WHERE id = ? AND is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, talukId);
			rs = ps.executeQuery();

			if (rs.next()) {
				matchedTaluk = new Taluk();
				matchedTaluk.setId(rs.getInt("id"));
				matchedTaluk.setTalukName(rs.getString("taluk_name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		System.out.println(matchedTaluk);
		return matchedTaluk;
	}

	public List<Taluk> findAll() throws RuntimeException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Taluk> talukList = new ArrayList<Taluk>();

		try {
			String query = "SELECT * from User where is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				Taluk newTaluk = new Taluk();
				newTaluk.setId(rs.getInt("id"));
				newTaluk.setTalukName(rs.getString("taluk_name"));

				talukList.add(newTaluk);
			}

		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);

		} finally {

			ConnectionUtil.close(con, ps);

		}

		return talukList;

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

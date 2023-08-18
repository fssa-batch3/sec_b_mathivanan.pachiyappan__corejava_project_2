package in.fssa.evotingsystem.util;

import java.sql.*;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Provides utility methods for handling database connections.
 */

public class ConnectionUtil {

	/**
     * Retrieves a database connection using the specified environment variables.
     *
     * @return A database connection.
     * @throws RuntimeException If there's an error while establishing the connection.
     */
	public static Connection getConnection() {

		Dotenv env = Dotenv.load();

		String url = env.get("DATABASE_HOSTNAME");
		String username = env.get("DATABASE_USERNAME");
		String password = env.get("DATABASE_PASSWORD");

		Connection connection = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);

		} catch (Exception e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return connection;
	}

	/**
     * Closes the database connection and prepared statement.
     *
     * @param connection The database connection to close.
     * @param ps         The prepared statement to close.
     */
	
	public static void close(Connection connection, PreparedStatement ps) {

		try {

			if (ps != null) {

				ps.close();
			}
			if (connection != null) {

				connection.close();

			}

		} catch (SQLException e) {

			e.printStackTrace();

		}
	}

	/**
     * Closes the database connection, prepared statement, and result set.
     *
     * @param connection The database connection to close.
     * @param ps         The prepared statement to close.
     * @param rs         The result set to close.
     */
	
	public static void close(Connection connection, PreparedStatement ps, ResultSet rs) {

		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

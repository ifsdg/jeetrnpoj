package com.situ.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * @author Mrkua
 *
 */
public class DBUtil {
	public Connection connect() {

		try {
		
			return DataSouseutil.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void close(Connection conn) throws SQLException {
		conn.close();
	}

	public void close(ResultSet res, Connection conn, PreparedStatement stat) throws SQLException {
		if (res != null) {
			res.close();
		}
		conn.close();
		stat.close();
	}

	public void close(Connection conn, PreparedStatement stat) throws SQLException {
		conn.close();
		stat.close();
	}

	public void close(Connection conn, ResultSet res) throws SQLException {
		conn.close();
		res.close();
	}

}
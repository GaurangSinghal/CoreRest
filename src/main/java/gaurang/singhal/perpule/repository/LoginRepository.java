package gaurang.singhal.perpule.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import gaurang.singhal.perpule.controller.LoginController;
import gaurang.singhal.perpule.model.Credentials;
import gaurang.singhal.perpule.model.DbInfo;

public class LoginRepository {
	private static final String url = "jdbc:google:mysql://perpule-245213:us-central1:gaurang/PERPULE?user=root&password=8994";
//	private static final String url = "jdbc:mysql://34.68.109.124/PERPULE?user=root&password=8994";
//	private static final String user = "root";
//	private static final String password = "8994";
	private static final String driver = "com.mysql.jdbc.GoogleDriver";
//	private static final String driver = "com.mysql.jdbc.Driver";
	private static PreparedStatement preStmt;

	private static final Logger log = Logger.getLogger(LoginController.class.getName());

	// JDBC variables for opening and managing connection
	private static Connection con;

	private static Statement stmt;

	private static ResultSet rs;

	public boolean authenticate(Credentials credentials) {
		System.out.println(credentials);
		String query = "select password from credentials where id=" + credentials.getId();
		boolean flag = false;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url);

			// getting Statement object to execute query
			stmt = con.createStatement();

			// executing SELECT query
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				flag = credentials.getPassword().equals(rs.getString("password")) ? true : false;
			}

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();

			log.info(sqlEx.toString());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info(e.toString());
		} finally {
			// close connection ,stmt and resultset here
			try {
				con.close();
			} catch (SQLException se) {
				/* can't do anything */ }
			try {
				stmt.close();
			} catch (SQLException se) {
				/* can't do anything */ }
			try {
				rs.close();
			} catch (SQLException se) {
				/* can't do anything */ }
		}

		return flag;
	}

	public void addToken(String token, int id) {
		String query = "update credentials set token=? where id=?";
		boolean flag = false;
		try {
			Class.forName(driver);
			// opening database connection to MySQL server
			con = DriverManager.getConnection(url);

			// getting Statement object to execute query
			preStmt = con.prepareStatement(query);
			preStmt.setString(1, token);
			preStmt.setInt(2, id);

			// executing SELECT query
			preStmt.executeUpdate();

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
			log.info(sqlEx.toString());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info(e.toString());
		} finally {
			// close connection ,stmt and resultset here
			try {
				con.close();
			} catch (SQLException se) {
				/* can't do anything */ }
			try {
				preStmt.close();
			} catch (SQLException se) {
				/* can't do anything */ }
		}

	}

	public boolean addCredential(DbInfo info) {

		String query = "insert into credentials values(?,?,'')";
		int n = 0;
		try {
			Class.forName(driver);
			// opening database connection to MySQL server
			con = DriverManager.getConnection(url);

			// getting Statement object to execute query
			preStmt = con.prepareStatement(query);
			preStmt.setInt(1, info.getId());
			preStmt.setString(2, info.getPassword());

			// executing SELECT query
			n = preStmt.executeUpdate();

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
			log.info(sqlEx.toString());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info(e.toString());
		} finally {
			// close connection ,stmt and resultset here
			try {
				con.close();
			} catch (SQLException se) {
				/* can't do anything */ }
			try {
				preStmt.close();
			} catch (SQLException se) {
				/* can't do anything */ }
		}
		return n > 0 ? true : false;
	}
}

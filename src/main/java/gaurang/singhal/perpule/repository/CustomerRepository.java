package gaurang.singhal.perpule.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import gaurang.singhal.perpule.controller.LoginController;
import gaurang.singhal.perpule.model.Customer;
import gaurang.singhal.perpule.model.DbInfo;

public class CustomerRepository {

	// JDBC URL, username and password of MySQL server
	private static final String url = "jdbc:google:mysql://perpule-245213:us-central1:gaurang/PERPULE?user=root&password=8994";
//	private static final String user = "root";
//	private static final String password = "8994";
	private static final String driver = "com.mysql.jdbc.GoogleDriver";

	// JDBC variables for opening and managing connection
	private static Connection con;
	private static PreparedStatement preStmt;

	private static Statement stmt;
	
	private static ResultSet rs;

	private static final Logger log = Logger.getLogger(LoginController.class.getName());

	public boolean addCustomer(DbInfo customer) {
		
		System.out.println(customer);
		String query = "insert into customer values(?,?,?,?)";
		int n=0;
		try {
			Class.forName(driver);  
			// opening database connection to MySQL server
			con = DriverManager.getConnection(url);

			// getting Statement object to execute query
			preStmt = con.prepareStatement(query);
			preStmt.setInt(1, customer.getId());
			preStmt.setString(2, customer.getName());
			preStmt.setString(3, customer.getAddress());
			preStmt.setInt(4, customer.getAge());

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

		return n>0;
	}

	public Customer getCustomer(int id) {
		String query = "select * from customer where id = "+id;
//		String query = "select * from customer";
		Customer customer=null;
		try {
			Class.forName(driver);  
			// opening database connection to MySQL server
			con = DriverManager.getConnection(url);

			// getting Statement object to execute query
			stmt = con.createStatement();

			// executing SELECT query
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				customer =new Customer();
				customer.setId(rs.getInt("id"));
				customer.setName(rs.getString("name"));
				customer.setAddress(rs.getString("address"));
				customer.setAge(rs.getInt("age"));
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

		return customer;
	}

}

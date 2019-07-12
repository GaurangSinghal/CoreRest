package gaurang.singhal.perpule.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionManager {
//    private static final Logger LOGGER = Logger.getLogger(SqlConnectionManagerGeneral.class.getName());

	public static Connection getConnection() throws Exception {
		try {
			String connectionURL;
//             if(PerpuleUtil.isDevelopmentEnv()) {
//                 connectionURL = "jdbc:mysql://35.184.45.59:3306/perpule_quality_assurance?user=backend&password=b@ck3nd";
//                 Class.forName("com.mysql.jdbc.Driver").newInstance();
//             }else{
			connectionURL = "jdbc:google:mysql://perpule-1248:us-central1:perpule-sql/perpule_prod?user=backend&password=b@ck3nd";
			Class.forName("com.mysql.jdbc.GoogleDriver").newInstance();

//             }
//             LOGGER.info("connection url "+connectionURL);
			Connection connection = DriverManager.getConnection(connectionURL);
			return connection;
		} catch (Exception e) {
			throw e;
		}
	}

//	public static Connection getConnection(String dbName) throws Exception {
//		try {
//			// String connectionURL =
//			// "jdbc:mysql://perpule-sql-3.cn2vavr0dzre.us-west-2.rds.amazonaws.com:3306/perpule_prod?user=backend&password=b@ck3nd";
//			// connectionURL = connectionURL +
//			// "&autoReconnect=true&failOverReadOnly=false&maxReconnects=10";
//
//			String connectionURL = "jdbc:mysql://35.184.45.59:3306/" + dbName + "?user=backend&password=b@ck3nd";
//			Connection connection = null;
//			// Class.forName("com.mysql.jdbc.GoogleDriver").newInstance();
//			Class.forName("com.mysql.jdbc.Driver").newInstance();
//			connection = DriverManager.getConnection(connectionURL);
//			return connection;
//		} catch (SQLException e) {
//			throw e;
//		} catch (Exception e) {
//			throw e;
//		}
//	}

//	public static void main(String[] args) {
//		try {
//			PreparedStatement ps = getConnection().prepareStatement("Select * from shop where shopId=?");
//			ps.setLong(1, 800L);
//			ResultSet rs = ps.executeQuery();
//			while (rs.next()) {
//				System.out.println("Column : " + rs.getString("shopName"));
//			}
//		} catch (Exception e) {
//			System.out.println("Error : " + e);
//		}
//	}

}
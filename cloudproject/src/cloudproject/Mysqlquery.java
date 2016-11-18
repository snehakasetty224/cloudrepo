package cloudproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Mysqlquery {

	public static int main(String query) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;
		connection = DriverManager.getConnection("jdbc:mysql://cmpe281.cznys0ru7jj1.us-west-2.rds.amazonaws.com:3306/CMPE281_2","cmpe281", "testing123");
		int count=0;
		if (connection != null) {
			//String query = "SELECT count(*) as count FROM Hosts where status=1";
			// create the java statement
			Statement st = connection.createStatement();
			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);
			// iterate through the java resultset
			while (rs.next())
			{
				count = rs.getInt("count");
				System.out.println(count);
			}
			st.close();
		} else {
			System.out.println("Failed to make connection!");
		}
		return count;
	}
	
	
}

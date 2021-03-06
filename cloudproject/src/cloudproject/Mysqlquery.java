package cloudproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class Mysqlquery {
	// Function for the details for the dashboard resitered vehicles and registered sensors from MYSQL
	public static int getCount(String query) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;
		connection = DriverManager.getConnection("jdbc:mysql://cmpe281.cznys0ru7jj1.us-west-2.rds.amazonaws.com:3306/CMPE281_SNEHA","cmpe281", "testing123");
		int count=0;
		if (connection != null) {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next())
			{
				count = rs.getInt("count");
				System.out.println(count);
			}
			st.close();
		} else {
			System.out.println("Failed to make connection!");
		}
		connection.close();
		return count;
	}
	
	// Function for returning role and id if username and password are correct from MYSQL
	public static ArrayList<String> getRole(String query) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;
		connection = DriverManager.getConnection("jdbc:mysql://cmpe281.cznys0ru7jj1.us-west-2.rds.amazonaws.com:3306/CMPE281_SNEHA","cmpe281", "testing123");
		ArrayList<String> list = new ArrayList<String>();
		if (connection != null) {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next())
			{
				list.add(rs.getString("roles"));
				list.add(rs.getString("id"));
			}
			st.close();
		} else {
			System.out.println("Failed to make connection!");
		}
		connection.close();
		return list;
	}
	
	// Function for getting map points from MYSQL
	public static ArrayList<Map> getMap(String query) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;
		connection = DriverManager.getConnection("jdbc:mysql://cmpe281.cznys0ru7jj1.us-west-2.rds.amazonaws.com:3306/CMPE281_SNEHA","cmpe281", "testing123");
		ArrayList<Map> list = new ArrayList<Map>();
		if (connection != null) {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next())
			{
				Map map=new Map();
				map.setLatitude(rs.getDouble("latitude"));
				map.setLongitude(rs.getDouble("longitude"));
				map.setDescription(rs.getString("description"));
				list.add(map);
			}
			st.close();
			
		} else {
			System.out.println("Failed to make connection!");
		}
		connection.close();
		return list;
	}
	
	// Function for the bill amount from MYSQL
	public static ArrayList<Bill> getBill(String query) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;
		connection = DriverManager.getConnection("jdbc:mysql://cmpe281.cznys0ru7jj1.us-west-2.rds.amazonaws.com:3306/CMPE281_SNEHA","cmpe281", "testing123");
		ArrayList<Bill> list = new ArrayList<Bill>();
		if (connection != null) {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next())
			{
				Bill amount=new Bill();
				amount.setY(rs.getString("m"));
				amount.setA(rs.getDouble("sum"));
			
				list.add(amount);
			}
			st.close();
			
		} else {
			System.out.println("Failed to make connection!");
		}
		connection.close();
		return list;
	}

	public static ArrayList<Routes> getRoutes(String query) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;
		connection = DriverManager.getConnection("jdbc:mysql://cmpe281.cznys0ru7jj1.us-west-2.rds.amazonaws.com:3306/CMPE281_SNEHA","cmpe281", "testing123");
		ArrayList<Routes> list = new ArrayList<Routes>();
		if (connection != null) {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next())
			{
				Routes routes = new Routes();
				routes.setSrc_latitude(rs.getDouble("src_latitude"));
				routes.setSrc_longitude(rs.getDouble("src_longitude"));
				routes.setDest_latitude(rs.getDouble("dest_latitude"));
				routes.setDest_longitude(rs.getDouble("dest_longitude"));
				list.add(routes);
			}
			st.close();
			
		} else {
			System.out.println("Failed to make connection!");
		}
		connection.close();
		return list;
	}
	
	public static ArrayList<AdminBill> getAdminBill(String query) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;
		connection = DriverManager.getConnection("jdbc:mysql://cmpe281.cznys0ru7jj1.us-west-2.rds.amazonaws.com:3306/CMPE281_SNEHA","cmpe281", "testing123");
		ArrayList<AdminBill> list = new ArrayList<AdminBill>();
		if (connection != null) {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next())
			{
				AdminBill adminamount=new AdminBill();
				adminamount.setAmount(rs.getDouble("amount"));
				if (rs.getString("isPaid").equals("1")){
				adminamount.setIspaid("yes");
				}
				else{
				adminamount.setIspaid("no");	
				}
				adminamount.setUserid(rs.getString("userId"));
				adminamount.setDate(rs.getString("d"));
				list.add(adminamount);
			}
			st.close();
			
		} else {
			System.out.println("Failed to make connection!");
		}
		connection.close();
		return list;
	}
	
}

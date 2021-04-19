package pk1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class dao 
{
	private static String url = "jdbc:mysql://localhost:3306/ds";
	private static String user = "root";
	private static String pw = "lan123";

	private static String addOrderQuery = "INSERT INTO student VALUES (?,?,?);";
	
	
	public static void addOrder(int id, String name, int year) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection(url,user,pw);
		PreparedStatement statement = connection.prepareStatement(addOrderQuery);
		
		statement.setInt(1, id);
		statement.setString(2, name);
		statement.setInt(3, year);
	
		
		statement.executeUpdate(); 
		
		statement.close();
		connection.close();
	}
}
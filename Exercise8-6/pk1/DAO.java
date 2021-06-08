package pk1;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DAO {
	
	public static ArrayList<Student> getStudents(String name) {
		ArrayList<Student> studentList = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/"+name;
			Connection con = DriverManager.getConnection(url, 
			                                             "root", 
														 "lan123");

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from student1");
			
			while (rs.next()) {
				studentList.add(new Student(rs.getInt(1), rs.getString(2), rs.getDouble(3)));
			}
			
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return studentList;
	}
	
	public static void insertStudents(String name, ArrayList<Student> studentList) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/"+name;
			Connection con = DriverManager.getConnection(url, 
			                                             "root", 
														 "lan123");
			DAO.delete(name);
			Statement stmt = con.createStatement();
			
			for (Student s:studentList) {
				stmt.executeUpdate("insert into student1 values ("+ s.getId() +", \""+ s.getName() +"\", "+s.getGrade()+");");
			}
			
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void delete(String name) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/"+name;
			Connection con = DriverManager.getConnection(url, 
			                                             "root", 
														 "lan123");

			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM student1");
			
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static boolean login(String username, String password) throws RemoteException {
		//List<Media> list = new ArrayList<Media>();

		boolean valid = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/dsEx5";
			Connection con = DriverManager.getConnection(url, 
			                                             "root", 
														 "lan123");

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from login");
			
			while (rs.next()) {
				//Media s = new Newspaper(rs.getString(1), rs.getString(2));
				//Media s = new Book(rs.getString(1), rs.getString(2));
				//list.add(s);
				if (rs.getString(1).equals(username) && rs.getString(2).equals(password)) {
					valid = true;
				}
			}
			
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		return valid;
	}

}

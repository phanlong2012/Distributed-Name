package pk1;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getServletPath());
		System.out.println(request.getParameter("username") + " " +request.getParameter("password"));
		if (LoginServlet.login(request.getParameter("username"), request.getParameter("password"))){
			response.setContentType("text/html");

		    PrintWriter out = response.getWriter();
		    out.println("<h1>" + "Login Successfully" + "</h1>");
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

package pk2;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pk1.Client;
import pk1.Server;
import pk1.Student;

/**
 * Servlet implementation class Insert1
 */
@WebServlet("/insert1")
public class Insert1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doGet");
		//res.sendRedirect("WebPage1.jsp");
		try {
			Server.serverSocket();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");

		
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		int year = Integer.parseInt(req.getParameter("year"));
		Client.clientSocket(new Student(id, name, year));
		
		
	}

}

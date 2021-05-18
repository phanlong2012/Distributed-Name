package pk1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalculatorServlet
 */
@WebServlet(urlPatterns = {"/calculate"})
public class CalculatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static String display = "";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("button").equals("="))	{
			double res = 0;
			for (int i = 0; i < display.length(); i++) {
				if ((display.charAt(i)+"").equals("+")) {
					res = Integer.parseInt(display.substring(0,i)) + Integer.parseInt(display.substring(i+1,display.length()));
				}
				else if ((display.charAt(i)+"").equals("-")) {
					res = Integer.parseInt(display.substring(0,i)) - Integer.parseInt(display.substring(i+1,display.length()));
				}
				else if ((display.charAt(i)+"").equals("*")) {
					res = Integer.parseInt(display.substring(0,i)) * Integer.parseInt(display.substring(i+1,display.length()));
				}
				else if ((display.charAt(i)+"").equals("/")) {
					res = Integer.parseInt(display.substring(0,i)) / Integer.parseInt(display.substring(i+1,display.length()));
				}
	        }
			display = Double.toString(res);
		}
		else if(request.getParameter("button").equals("c"))	{
			display = "";
		}
		else {
			display+=request.getParameter("button");
			System.out.println(display);
		}
		request.setAttribute("display", display);
		request.getRequestDispatcher("calculator1.jsp").forward(request, response);
	}
	
}

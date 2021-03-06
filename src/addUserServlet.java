import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class addUserServlet extends HttpServlet
{
	StoreDatabase sd;

	public void init() throws ServletException {
		sd = StoreDatabase.instance();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("POST request from html na may tamang value");
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		String un = request.getParameter("username");
		String pw = request.getParameter("password");
		String fn = request.getParameter("fname");
		String ln = request.getParameter("lname");
		String em = request.getParameter("email");
		String mn = request.getParameter("mobile");
		String add = request.getParameter("add");
		String[] str = {un, pw, fn, ln, em, mn, add};
		if(sd.checkAvailability(un, em) == true) {
			out.println("Username or Email already taken");
		}
		else {
			if(sd.insertUserToDB(str)) {
				out.println("Registration Complete");
			}
			else {
				out.println("Registration Failed");
			}
		}
		out.close();
	}

	public void destroy() {
		sd = null;
	}
}

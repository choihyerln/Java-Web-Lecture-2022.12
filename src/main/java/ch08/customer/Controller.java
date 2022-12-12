package ch08.customer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch08.CustomerDao;

/**
 * Servlet implementation class Controller
 */
@WebServlet({ "/ch08/customer/list", "/ch08/customer/register", "/ch08/customer/update", "/ch08/customer/delete" })
public class Controller extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String servletPath = request.getServletPath();
		CustomerDao dao = new CustomerDao();
		
		
		if (servletPath.indexOf("list") > 0) {
			
		}
		else if (servletPath.indexOf("register") > 0) {
			
		} else if (servletPath.indexOf("update") > 0) {
			
		} else if (servletPath.indexOf("delete") > 0) {
			
		} else {
			System.out.println("잘못된 경로");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

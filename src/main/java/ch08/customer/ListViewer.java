package ch08.customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch08.Customer;

/**
 * Servlet implementation class ListViewer
 */
@WebServlet("/ch08/customer/listView")
public class ListViewer extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		List<Customer> list = (List<Customer>)request.getAttribute("customerList");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter(); 
		out.print("<!<!DOCTYPE html>");
		out.print("<html lang=\"ko\">");
		out.print("<head>");
		out.print("    <meta charset=\"UTF-8\">");
		out.print("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
		out.print("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		out.print("    <title>회원 리스트</title>");
		out.print("</head>");
		out.print("<body style=\"margin: 40px;\">");
		out.print("    <h1>회원 리스트</h1>");
		out.print("    <hr>");
		
		data += "    <table border=\"1\">"
				+ "        <tr>"
				+ "            <th>사용자ID</th><th>사용자명</th><th>가입일</th>"
				+ "        </tr>";
		out.print(data);
		
		for (Customer c: list) {
			out.print("     <tr>");
			out.print("     <td>" + c.getUid() + "</td>");
			out.print("     <td>" + c.getUname() + "</td>");
			out.print("     <td>" + c.getRegDate().toString() + "</td>");
			out.print("     </tr>");
		}
		
		out.print("</table>");
		out.print(" <br>");
		out.print("<a href=\"/ch08/customer/registerCustomer.html\">회원 가입</a>");
		out.print("</body>");
		out.print("</html>");
	}
	
	}
}

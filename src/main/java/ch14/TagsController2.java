package ch14;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ElController
 */
@WebServlet("/ch14/tags2")
public class TagsController2 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Address addr1 = new Address(12345, "서울", "한국");
		Address addr2 = new Address(34567, "용인", "한국");
		Member m1 = new Member(101, "홍길동", addr1);
		Member m2 = new Member(102, "홍자바", addr2);
		Member m3 = new Member(103, "제임스", new Address(98765, "뉴욕", "미국"));
		Member m4 = new Member(104, "마리아", new Address(43210, "LA", "미국"));
		
		Member[] members = {m1, m2, m3, m4};
		request.setAttribute("members", members);
		
		RequestDispatcher rd = request.getRequestDispatcher("/ch14/tags2.jsp");
		rd.forward(request, response);
	}
}
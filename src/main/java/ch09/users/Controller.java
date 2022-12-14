package ch09.users;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Servlet implementation class Controller
 */
//@WebServlet(
//		name = "UserController", 
//		urlPatterns = { 
//				"/ch09/users/list", 
//				"/ch09/users/login", 
//				"/ch09/users/logout", 
//				"/ch09/users/register"
//		})
public class Controller extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String requestUri = request.getRequestURI();
		String[] uri = requestUri.split("/");
		String action = uri[uri.length - 1];
		UserDao dao = new UserDao();
		HttpSession session = request.getSession();
		
		if (requestUri.contains("list")) {
			List<User> list = dao.listUsers();
			RequestDispatcher rd = request.getRequestDispatcher("/ch09/users/listView");
			request.setAttribute("userList", list);
			rd.forward(request, response);
		} else if (requestUri.contains("login")) {
			
		} else if (requestUri.contains("logout")) {
			System.out.println(session.getAttribute("uid"));
			System.out.println(session.getAttribute("uname"));
			session.invalidate();
			response.sendRedirect("/ch09/users/list");
		} else {
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String requestUri = request.getRequestURI();
		UserDao dao = new UserDao();
		HttpSession session = request.getSession();
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if (requestUri.contains("login")) {
			String uid = request.getParameter("uid");
			String pwd = request.getParameter("pwd");
			User u = dao.getUserInfo(uid);
			if (u.getUid() != null) {			// uid ??????
				if (BCrypt.checkpw(pwd, u.getPwd())) {
				//System.out.println(u.getUid() + ", " + u.getUname());
				session.setAttribute("uid", u.getUid());
				session.setAttribute("uname", u.getUname());
				
				// Welcome message
				out.print("<script>");
				out.print("alert('" + u.getUname() + "??? ???????????????." + "');");
				out.print("location.href = '" + "/ch09/users/list" + "';");
				out.print("</script>");
				} else {
					// ??? ????????? ?????????
					out.print("<script>");
					out.print("alert('????????? ?????????????????????. ?????? ???????????????.');");
					out.print("location.href = '" + "/ch09/users/login.html" + "';");
					out.print("</script>");
				}
			} else {				// uid??? ??????
				System.out.println("?????? ??????????????????.");

				// ?????? ?????? ???????????? ??????
				out.print("<script>");
				out.print("alert('?????? ?????? ???????????? ???????????????.');");
				out.print("location.href = '" + "/ch09/users/register.html" + "';");
				out.print("</script>");
			}
		} else if (requestUri.contains("register")) {
			
		} else {
			
		}
	}
}

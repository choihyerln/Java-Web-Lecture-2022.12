package ch12.users2;

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
 * Servlet implementation class UserServiceController
 */
@WebServlet({ "/ch12/users2/list", "/ch12/users2/login", "/ch12/users2/logout", 
			"/ch12/users2/register", "/ch12/users2/update", "/ch12/users2/delete"})
public class User2ServiceController extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String[] uri = request.getRequestURI().split("/");
		String action = uri[uri.length - 1];
		UserDao dao = new UserDao();
		HttpSession session = request.getSession();
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String uid = null, pwd = null, pwd2 = null, uname = null, email = null;
		User u =null;
		RequestDispatcher rd = null;
		switch(action) {
		case "list":
			List<User> list = dao.listUsers();
			request.setAttribute("userList", list);
			rd = request.getRequestDispatcher("/ch12/users2/list.jsp");
			rd.forward(request, response);
			break;
		case "login":			
			uid = request.getParameter("uid");
			pwd = request.getParameter("pwd");
			u = dao.getUserInfo(uid);
			if (u.getUid() != null) {			// uid 존재
				if (BCrypt.checkpw(pwd, u.getPwd())) {
					//System.out.println(u.getUid() + ", " + u.getUname());
					session.setAttribute("uid", u.getUid());
					session.setAttribute("uname", u.getUname());
					
					// Welcome message
//					out.print("<script>");
//					out.print("alert('" + u.getUname() + "님 환영합니다." + "');");
//					out.print("location.href = '" + "/ch09/users/list" + "';");
//					out.print("</script>");
					request.setAttribute("msg", u.getUname() + "님 환영합니다.");
					request.setAttribute("url", "/ch12/users2/list");
					rd = request.getRequestDispatcher("/ch12/users2/alertMsg.jsp");
					rd.forward(request, response);
					
					
				} else {
					// 재 로그인 페이지
//					out.print("<script>");
//					out.print("alert('잘못된 패스워드입니다. 다시 입력하세요.');");
//					out.print("location.href = '" + "/ch09/users/login.html" + "';");
//					out.print("</script>");
					request.setAttribute("msg", "잘못된 패스워드입니다. 다시 입력하세요.");
					request.setAttribute("url", "/ch12/users2/login.html");
					rd = request.getRequestDispatcher("/ch12/users2/alertMsg.jsp");
					rd.forward(request, response);
				}
			}
			else {								// uid가 없음
				System.out.println("없는 사용자입니다.");
				
				// 회원 가입 페이지로 안내
//				out.print("<script>");
//				out.print("alert('회원 가입 페이지로 이동합니다.');");
//				out.print("location.href = '" + "/ch09/users/register.html" + "';");
//				out.print("</script>");
				request.setAttribute("msg", "회원 가입 페이지로 이동합니다.");
				request.setAttribute("url", "/ch12/users2/register");
				rd = request.getRequestDispatcher("/ch12/users2/alertMsg.jsp");
				rd.forward(request, response);
			}
			break;
		case "logout":
			session.invalidate();
			response.sendRedirect("/ch12/users2/list");
			break;
		case "register":
			if (request.getMethod().equals("GET")) {
				response.sendRedirect("/ch12/users2/register");
			} else {
				uid = request.getParameter("uid");
				pwd = request.getParameter("pwd");
				pwd2 = request.getParameter("pwd2");
				uname = request.getParameter("uname");
				email = request.getParameter("email");
				if (pwd.equals(pwd2)) {
					u = new User(uid, pwd, uname, email);
					dao.resisterUser(u);
					response.sendRedirect("/ch12/users2/list");
				}
				else {
					request.setAttribute("msg", "동일한 패스워드를 입력하세요.");
					request.setAttribute("url", "/ch12/users2/register");
					rd = request.getRequestDispatcher("/ch12/users2/alertMsg.jsp");
					rd.forward(request, response);
//					out.print("<script>");
//					out.print("alert('동일한 패스워드를 입력하세요.');");
//					out.print("location.href='" + "/ch12/users2/register.html" + "';");
//					out.print("</script>");
				}
			}
			break;
		case "update":
			if (request.getMethod().equals("GET")) {
				uid = request.getParameter("uid");
				u = dao.getUserInfo(uid);
				request.setAttribute("user", u);
				rd = request.getRequestDispatcher("/ch12/users2/update.jsp");
				rd.forward(request, response);
			}
			else {
				uid = request.getParameter("uid");
				uname = request.getParameter("uname");
				email = request.getParameter("email");
				u = new User(uid, uname, email);
				dao.updateUser(u);
				session.setAttribute("uname", uname);
				response.sendRedirect("/ch12/users2/list");
			}
			break;
		case "delete":
			uid = request.getParameter("uid");
			dao.deleteUser(uid);
			response.sendRedirect("/ch12/users2/list");
			break;
		default:
			System.out.println(request.getMethod() + " 잘못된 경로");
		} 
	}
}
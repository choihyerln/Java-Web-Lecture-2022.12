package ch06;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Resister
 */
@WebServlet("/ch06/regMember")
public class ResisterMember extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Params.doPost() method");
		request.setCharacterEncoding("utf-8");		// 한글 처리를 위해 반드시 해줘야 함	
		
		String uid = request.getParameter("uid");
		String pwd = request.getParameter("pwd");
		String pwd2 = request.getParameter("pwd2");
		String name = request.getParameter("name");
		String birthday = request.getParameter("birthday");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		String[] hobbies = request.getParameterValues("hobby");
		
		String data = "uid: " + uid + "\n";
		data += "pwd: " + pwd.equals(pwd2) + "\n";
		data += "name: " + name + "\n";
		data += "birthday: " + birthday + "\n";
		data += "email: " + email + "\n";
		data += "gender: " + gender + "\n";
		for (String hobby: hobbies)
			data += "취미: " + hobby + "\n";
		System.out.println(data);
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<!<!DOCTYPE html>");
		out.print("<html lang=\"ko\">");
		out.print("<head>");
		out.print("    <meta charset=\"UTF-8\">");
		out.print("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
		out.print("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		out.print("    <title>Test</title>");
		out.print("</head>");
		out.print("<body style=\"margin: 40px;\">");
		out.print("    <h1>회원가입 정보</h1>");
		out.print("    <hr>");
		
		//out.print(data);
		String[] ulList = data.split("\n");
		out.print("<ul>");
		for (String li: ulList)
			out.print("<li>" + li + "</li>");
		out.print("</ul>");
		
		out.print("	<br>");
		out.print("	<button onclick=\"location.href='/ch06/registerMember.html'\">재실행</button>");
		out.print("	<button onclick=\"location.href='/ch06/registerMemberValidate.html'\">재실행(검증)</button>");
		out.print("</body>");
		out.print("</html>");
	}
}
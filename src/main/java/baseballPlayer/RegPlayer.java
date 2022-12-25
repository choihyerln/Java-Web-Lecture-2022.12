package baseballPlayer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/baseballPlayer/regPlayer")
public class RegPlayer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static PlayerDAO dao = new PlayerDAO();
	private static Player p;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bNum, height;
		String name, position, birthDate;
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		bNum = Integer.parseInt(request.getParameter("bNum"));
		
		/* 유효성 검사 */
		if (dao.getPlayer(bNum).getbNum() != 0) {
			response.sendRedirect("/baseballPlayer/regPlayer.html");
		} else {
			name = request.getParameter("name");
			position = request.getParameter("position");
			birthDate = request.getParameter("birthDate");
			height = Integer.parseInt(request.getParameter("height"));
			
			p = new Player(bNum, name, position, birthDate, height);
			dao.insertPlayer(p);
			response.sendRedirect("/baseballPlayer/playerList");
		
		}
	}

}

package baseballPlayer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/baseballPlayer/updatePlayer")
public class UpdatePlayer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static PlayerDAO dao = new PlayerDAO();
	private static final String[] POSITION = {"투수", "포수", "내야수", "외야수"};
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		int bNum = Integer.parseInt(request.getParameter("bNum"));
		Player p = dao.getPlayer(bNum);
		PrintWriter out = response.getWriter();
		out.print(html(p));
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int bNum = Integer.parseInt(request.getParameter("bNum"));
		int height = Integer.parseInt(request.getParameter("height"));
		String name = request.getParameter("name");
		String position = request.getParameter("position");
		String birthDate = request.getParameter("birthDate");
		
		Player p = new Player(bNum, name, position, birthDate, height);
		System.out.println(p);
		dao.updatePlayer(p);
		response.sendRedirect("/baseballPlayer/playerList");
	}

	private StringBuilder html(Player p) {
		StringBuilder sb = new StringBuilder();
		sb.append("<!DOCTYPE html>\n"
				+ "<html lang=\"ko\">\n"
				+ "<head>\n"
				+ "    <meta charset=\"UTF-8\">\n"
				+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
				+ "    <title>선수 정보 수정</title>\n"
				+ "    <style>\n"
				+ "        * {\n"
				+ "          font-family: -apple-system, BlinkMacSystemFont, 'Apple SD Gothic Neo',\n"
				+ "            'Pretendard Variable', Pretendard, Roboto, 'Noto Sans KR', 'Segoe UI',\n"
				+ "            'Malgun Gothic', 'Apple Color Emoji', 'Segoe UI Emoji',\n"
				+ "            'Segoe UI Symbol', sans-serif;\n"
				+ "          color: #333;\n"
				+ "        }\n"
				+ "        tr,\n"
				+ "        td,\n"
				+ "        th {\n"
				+ "          padding: 25px;\n"
				+ "          border-bottom: solid 1px #eee;\n"
				+ "          text-align: left;\n"
				+ "        }\n"
				+ "  \n"
				+ "        tr:last-child,\n"
				+ "        td:last-child {\n"
				+ "          border: none;\n"
				+ "        }\n"
				+ "  \n"
				+ "        input[type='text'],\n"
				+ "        [type='date'],\n"
				+ "        [type='number'] {\n"
				+ "          background-color: #e8f0fd8c;\n"
				+ "          font-size: 14px;\n"
				+ "          border: none;\n"
				+ "          padding-inline: 20px;\n"
				+ "          padding-block: 12px;\n"
				+ "          border-radius: 5px;\n"
				+ "          width: 90%;\n"
				+ "        }\n"
				+ "        table {\n"
				+ "          margin: 0 auto;\n"
				+ "          border-collapse: collapse;\n"
				+ "        }\n"
				+ "  \n"
				+ "        .contain-01 {\n"
				+ "          display: flex;\n"
				+ "          justify-content: center;\n"
				+ "        }\n"
				+ "        .contain-02 {\n"
				+ "          display: inline-block;\n"
				+ "        }\n"
				+ "        .title {\n"
				+ "          display: flex;\n"
				+ "          justify-content: space-between;\n"
				+ "          align-items: center;\n"
				+ "          padding: 20px;\n"
				+ "          border-bottom: solid 1px #eee;\n"
				+ "          align-items: flex-end;\n"
				+ "        }\n"
				+ "        input[type='submit'] {\n"
				+ "          font-size: 15px;\n"
				+ "          height: 49px;\n"
				+ "          border: none;\n"
				+ "          background-color: #4880ee;\n"
				+ "          color: white;\n"
				+ "          padding-inline: 32px;\n"
				+ "          border-radius: 5px;\n"
				+ "          width: 100%;\n"
				+ "        }\n"
				+ "  \n"
				+ "        input:disabled {\n"
				+ "          background-color: #e9e9e9;\n"
				+ "        }\n"
				+ "  \n"
				+ "        input[type='button'] {\n"
				+ "          background-color: #e8f0fd;\n"
				+ "          /* border: 1px solid #d7d7d7; */\n"
				+ "          height: 28px;\n"
				+ "          color: #4880ee;\n"
				+ "          border: none;\n"
				+ "          border-radius: 5px;\n"
				+ "          font-weight: 600;\n"
				+ "          padding-inline: 10px;\n"
				+ "        }\n"
				+ "  \n"
				+ "        h1 {\n"
				+ "          margin: 0;\n"
				+ "        }\n"
				+ "  \n"
				+ "        body {\n"
				+ "          margin-top: 80px;\n"
				+ "          margin-bottom: 100px;\n"
				+ "        }\n"
				+ "      </style>\n"
				+ "</head>\n"
				+ "<body>\n"
				+ "    <div class=\"contain-01\">\n"
				+ "        <div class=\"contain-02\">\n"
				+ "            <div class=\"title\">\n"
				+ "                <h1>선수 정보 수정</h1>\n"
				+ "                <input type=\"button\" value=\"Home\" onclick=\"location.href='/baseballPlayer/playerList'\">\n"
				+ "            </div>\n"
				+ "\n"
				+ "            <form action=\"/baseballPlayer/updatePlayer\" method=\"post\">\n"
				+ "                <input type=\"hidden\" name=\"bNum\" value=\"\">\n"
				+ "                <table>\n"
				+ "                    <tr>\n"
				+ "                        <th>백넘버</th>\n"
				+ "                        <td>\n"
				+ "                            <input type=\"number\" name=\"bNum\" placeholder=\"백넘버\" required disabled value=\"123\">\n"
				+ "                        </td>\n"
				+ "                        </tr>\n"
				+ "                        <tr>\n"
				+ "                        <th>이름</th>\n"
				+ "                        <td>\n"
				+ "                            <input type=\"text\" name=\"name\" placeholder=\"이름\" maxlength=\"4\" required value=\"\">\n"
				+ "                        </td>\n"
				+ "                        </tr>\n"
				+ "                        <tr>\n"
				+ "                        <th>포지션</th>\n"
				+ "                        <td>\n"
				+ "                            <input type=\"radio\" name=\"position\" value=\"투수\" checked>투수\n"
				+ "                            <input type=\"radio\" name=\"position\" value=\"포수\">포수\n"
				+ "                            <input type=\"radio\" name=\"position\" value=\"내야수\">내야수\n"
				+ "                            <input type=\"radio\" name=\"position\" value=\"외야수\">외야수\n"
				+ "                        </td>\n"
				+ "                        </tr>\n"
				+ "                        <tr>\n"
				+ "                        <th>생년월일</th>\n"
				+ "                        <td>\n"
				+ "                            <input type=\"date\" name=\"birthDate\" placeholder=\"생년월일\" required value=\"\">\n"
				+ "                        </td>\n"
				+ "                        </tr>\n"
				+ "                        <tr>\n"
				+ "                        <th>신장</th>\n"
				+ "                        <td>\n"
				+ "                            <input type=\"number\" name=\"height\" placeholder=\"신장(cm)\" max=\"250\" required value=\"\">");
		sb.append("              </td>\r\n" + "            </tr>\r\n" + "            <tr>\r\n"
				+ "              <td colspan=\"2\">\r\n"
				+ "                <input type=\"submit\" value=\"선수 수정\" />\r\n" + "              </td>\r\n"
				+ "            </tr>\r\n" + "          </table>\r\n" + "        </form>\r\n" + "      </div>\r\n"
				+ "    </div>\r\n" + "  </body>\r\n" + "</html>");
		return sb;
	}
}
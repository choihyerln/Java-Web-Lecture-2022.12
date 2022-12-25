package baseballPlayer;
/** 선수 리스트 - home */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/baseballPlayer/playerList")
public class PlayerList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PlayerDAO dao = new PlayerDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		List <Player> list = dao.getPlayerList();
		/** Print */
		out.print(html(list));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	private StringBuilder html(List<Player> list) {
		StringBuilder sb = new StringBuilder();
		sb.append("<!DOCTYPE html>\n"
				+ "<html lang=\"ko\">\n"
				+ "<head>\n"
				+ "    <meta charset=\"UTF-8\">\n"
				+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
				+ "    <title>baseballPlayer Home</title>\n"
				+ "    <style>\n"
				+ "        * {\n"
				+ "          font-family: -apple-system, BlinkMacSystemFont, 'Apple SD Gothic Neo',\n"
				+ "            'Pretendard Variable', Pretendard, Roboto, 'Noto Sans KR', 'Segoe UI',\n"
				+ "            'Malgun Gothic', 'Apple Color Emoji', 'Segoe UI Emoji',\n"
				+ "            'Segoe UI Symbol', sans-serif;\n"
				+ "          color: #333;\n"
				+ "        }\n"
				+ "        h1 {\n"
				+ "          margin: 0;\n"
				+ "        }\n"
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
				+ "        }\n"
				+ "  \n"
				+ "        body {\n"
				+ "          margin-top: 80px;\n"
				+ "          margin-bottom: 100px;\n"
				+ "        }\n"
				+ "        tr,\n"
				+ "        td,\n"
				+ "        th {\n"
				+ "          padding: 25px;\n"
				+ "          border-bottom: solid 1px #eee;\n"
				+ "          text-align: center;\n"
				+ "        }\n"
				+ "        table {\n"
				+ "          margin: 0 auto;\n"
				+ "          border-collapse: collapse;\n"
				+ "        }\n"
				+ "        input[type='text'] {\n"
				+ "          font-size: 14px;\n"
				+ "          border: none;\n"
				+ "          background-color: rgba(250, 235, 215, 0.493);\n"
				+ "          height: 30px;\n"
				+ "          padding-inline: 15px;\n"
				+ "        }\n"
				+ "        button {\n"
				+ "          height: 28px;\n"
				+ "          font-weight: 600;\n"
				+ "          padding-inline: 10px;\n"
				+ "        }\n"
				+ "        input[type='button'] {\n"
				+ "          font-size: 15px;\n"
				+ "          height: 41px;\n"
				+ "          padding-inline: 28px;\n"
				+ "        }\n"
				+ "        .mainbtn {\n"
				+ "          background-color: #4880ee;\n"
				+ "          color: white;\n"
				+ "          border: none;\n"
				+ "          border-radius: 5px;\n"
				+ "          cursor: pointer;\n"
				+ "        }\n"
				+ "        .subbtn {\n"
				+ "          background-color: #e8f0fd;\n"
				+ "          color: #4880ee;\n"
				+ "          border: none;\n"
				+ "          border-radius: 5px;\n"
				+ "          cursor: pointer;\n"
				+ "        }\n"
				+ "      </style>\n"
				+ "</head>\n"
				+ "<body>\n"
				+ "    <div class=\"contain-01\">\n"
				+ "        <div class=\"contain-02\">\n"
				+ "            <div class=\"title\">\n"
				+ "                <h1>선수 리스트</h1>\n"
				+ "                <div>\n"
				+ "                    <input class=\"mainbtn\" type=\"button\" value=\"선수등록\" onclick=\"location.href='/baseballPlayer/regPlayer.html'\">\n"
				+ "                </div>\n"
				+ "            </div>\n"
				+ "            <table>\n"
				+ "                <tr style=\"background-color: #e8f0fd42;\">\n"
				+ "                    <th>백넘버</th>\n"
				+ "                    <th>이름</th>\n"
				+ "                    <th>포지션</th>\n"
				+ "                    <th>생년월일</th>\n"
				+ "                    <th>키</th>\n"
				+ "                    <th>선수 정보 수정 / 방출</th>\n"
				+ "                </tr>");
		
		for(Player x : list)
			sb.append("<tr>").append("<td>").append(x.getbNum()).append("</td>")
			.append("<td>").append(x.getName()).append("</td>")
			.append("<td>").append(x.getPosition()).append("</td>")
			.append("<td>").append(x.getBirthDate()).append("</td>")
			.append("<td>").append(x.getHeight()).append("</td>")
			.append("<td>\r\n"
					+ "              <button\r\n"
					+ "                class=\"subbtn\"\r\n"
					+ "                type=\"button\"\r\n"
					+ "                onclick=\"location.href='/baseballPlayer/updatePlayer?bNum=")
			.append(x.getbNum())
			.append("'\"\r\n"
					+ "              >\r\n"
					+ "                수정\r\n"
					+ "              </button>\r\n"
					+ "              <button\r\n"
					+ "                class=\"subbtn\"\r\n"
					+ "                type=\"button\"\r\n"
					+ "                onclick=\"location.href = '/baseballPlayer/deletePlayer?bNum=")
			.append(x.getbNum())
			.append("'\"\r\n"
					+ "              >\r\n"
					+ "                방출\r\n"
					+ "              </button>\r\n"
					+ "            </td>\r\n"
					+ "          </tr>");
		sb.append("        </table>\r\n"
				+ "      </div>\r\n"
				+ "    </div>\r\n"
				+ "  </body>\r\n"
				+ "</html>");
		
		return sb;
	}

}
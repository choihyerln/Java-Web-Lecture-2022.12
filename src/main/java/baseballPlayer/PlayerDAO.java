package baseballPlayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class PlayerDAO {
	private static Connection conn;
	private PreparedStatement pStmt;
	private ResultSet rs;
	
	public static void myGetConn() {
		try {
			Context initContext = new InitialContext();
			DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/world");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* 선수 목록 */
	public List<Player> getPlayerList() {
		String sql = "SELECT * FROM baseballPlayer WHERE isDeleted=0 ORDER BY field(position, '투수', '포수', '내야수', '외야수');";
		List<Player> list = new ArrayList<>();
		
		myGetConn();
		try {
			pStmt = conn.prepareStatement(sql);
			rs = pStmt.executeQuery();
			
			while (rs.next()) {
				Player p = new Player();
				p.setbNum(rs.getInt(1));
				p.setName(rs.getString(2));
				p.setPosition(rs.getString(3));
				p.setBirthDate(LocalDate.parse(rs.getString(4)));
				p.setHeight(rs.getInt(5));
				p.setIsDeleted(rs.getInt(6));
				list.add(p);
			}
			rs.close(); pStmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/* 선수 1명 목록 */
	public Player getPlayer(int bNum) {
		String sql = "SELECT * FROM baseballPlayer WHERE bNum = ?;";
		Player p = new Player();
		
		myGetConn();
		try {
			pStmt = conn.prepareStatement(sql);
			rs = pStmt.executeQuery();
			
			while(rs.next()) {
				p.setbNum(rs.getInt(bNum));
				p.setName(rs.getString(2));
				p.setPosition(rs.getString(3));
				p.setBirthDate(LocalDate.parse(rs.getString(4)));
				p.setHeight(rs.getInt(5));
				p.setIsDeleted(rs.getInt(6));
			}
			rs.close(); pStmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}
	
	/* 선수 등록 */
	public void insertPlayer(Player p) {
		String sql = "INSERT INTO baseballPlayer VALUES(?, ?, ?, ?, ?);";
		
		myGetConn();
		try {
			pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, p.getbNum());
			pStmt.setString(2, p.getName());
			pStmt.setString(3, p.getPosition());
			pStmt.setString(4, p.getBirthDate().toString());
			pStmt.setInt(5, p.getHeight());
			
			pStmt.executeUpdate();
			pStmt.close(); conn.close();
			System.out.println("[선수 등록 완료]");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/* 선수 정보 수정 */
	public void updatePlayer(Player p) {
		String sql = "UPDATE baseballPlayer SET name=?, position=?, birthDate=?, height=? WHERE bNum=?;";
		
		myGetConn();
		try {
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, p.getName());
			pStmt.setString(2, p.getPosition());
			pStmt.setString(3, p.getBirthDate().toString());
			pStmt.setInt(4, p.getHeight());
			pStmt.setInt(5, p.getbNum());
			
			pStmt.executeUpdate();
			pStmt.close(); conn.close();
			System.out.println("[선수 수정 완료]");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/* 선수 방출 */
	public void deletePlayer(int bNum) {
		String sql = "UPDATE baseballPlayer SET isDeleted=1 WHERE bNum=?;";
		
		myGetConn();
		try {
			pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, bNum);
			
			// Delete 대신에 isDeleted 필드를 1로 변경
			pStmt.executeUpdate();
			pStmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
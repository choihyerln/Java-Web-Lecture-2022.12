package baseballPlayer;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


/**
 * DAO(Data Access Object)
 */
public class DAO {
	private String host;
	private String user;
	private String password;
	private String database;
	private String port;
	
	/* DB접속 정보 가져오기 */
	DAO() {
		try {
			InputStream is = new FileInputStream("/Users/choihyerin/eclipse-workspace/mysql.properties");
			Properties props = new Properties();
			props.load(is);
			is.close();
			
			host = props.getProperty("host");
			user = props.getProperty("user");
			password = props.getProperty("password");
			database = props.getProperty("database");
			port = props.getProperty("port", "3306");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* DB 접속 */
	public Connection myGetConnection() {
		Connection conn = null;
		try {
			String connStr = "jdbc:mysql://" + host + ":" + port + "/" + database;
			conn = DriverManager.getConnection(connStr, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/* 선수 전체 목록 */
	public List<Player> getPlayers() {
		Connection conn = myGetConnection();
		List<Player> list = new ArrayList<>();
		String sql = "SELECT * FROM baseballPlayer WHERE isDeleted=0;";
		try {
			Statement stmt = conn.createStatement();
			
			// Select 실행
			ResultSet rs = stmt.executeQuery(sql);
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
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}
	
	/* 선수 1명 목록 */
	public Player getPlayer(int bNum) {
		Connection conn = myGetConnection();
		String sql = "SELECT * FROM baseballPlayer WHERE bNum=?;";
		Player p = new Player();
		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, bNum);
			
			// Select 실행
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				p.setbNum(rs.getInt(1));
				p.setName(rs.getString(2));
				p.setPosition(rs.getString(3));
				p.setBirthDate(LocalDate.parse(rs.getString(4)));
				p.setHeight(rs.getInt(5));
				p.setIsDeleted(rs.getInt(6));
			}
			rs.close();
			pStmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}
	
	/* 선수 등록 */
	public void insertPlayer(Player p) {
		Connection conn = myGetConnection();
		String sql = "INSERT INTO baseballPlayer(bNum, name, position, birthDate, height) VALUES(?, ?, ?, ?, ?);";
		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, p.getbNum());
			pStmt.setString(2, p.getName());
			pStmt.setString(3, p.getPosition());
			pStmt.setString(4, p.getBirthDate().toString());
			pStmt.setInt(5, p.getHeight());

			pStmt.executeUpdate();
			pStmt.close();
			conn.close();
			System.out.println("[선수 등록 완료]");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/* 선수 정보 수정 */
	public void updatePlayer(Player p) {
		Connection conn = myGetConnection();
		String sql = "UPDATE baseballPlayer SET name=?, position=?, birthDate=?, height=? WHERE bNum=?;";
		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, p.getName());
			pStmt.setString(2, p.getPosition().toString());
			pStmt.setString(3, p.getBirthDate().toString());
			pStmt.setInt(4, p.getHeight());
			pStmt.setInt(5, p.getIsDeleted());
			pStmt.setInt(6, p.getbNum()); 	// ? 순서대로 번호가 매겨짐(1번부터)
			
			// Update 실행
			pStmt.executeUpdate();
			pStmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* 선수 방출 */
	public void deletePlayer(int bNum) {
		Connection conn = myGetConnection();
		String sql = "UPDATE baseballPlayer SET isDeleted=1 WHERE bNum=?;";
		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, bNum);
			
			// Delete 대신에 isDeleted 필드를 1로 변경
			pStmt.executeUpdate();
			pStmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Board;
import dto.Member;

public class BoardDao {

	Connection getConnection() {
		Connection con = null;
		String url = "jdbc:oracle:thin:@//localhost:1521/xe";
		String userid = "JSP_BOARD";
		String userpw = "1111";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection(url, userid, userpw);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	public int selectMaxBno() {
		System.out.println("dao - selectMaxDao 호출");
		Connection con = getConnection();
		if(con == null) {
			return -1;
		}
		String sql = "SELECT NVL(MAX(BNO),0) FROM BOARDS";
		int result = 0;
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			while(rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return result;
	}

	public int insertBoard(Board board) {
		System.out.println("dao - insertDao 호출");
		Connection con = getConnection();
		if(con == null) {
			return 0;
		}
		String sql ="INSERT INTO BOARDS(BNO,BWRITER,BTITLE,BCONTENTS,BHITS, BSTATE) VALUES(?,?,?,?,0,'1')";
		int result = 0;
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board.getBno());
			pstmt.setString(2, board.getBwriter());
			pstmt.setString(3, board.getBtitle());
			pstmt.setString(4, board.getBcontents());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<Board> selectBoardList() {
		System.out.println("dao - selectBiardList() 호출");
		Connection con = getConnection();
		if(con == null) {
			return new ArrayList<Board>();
		}
		String sql = "SELECT * FROM BOARDS WHERE BSTATE = '1' ORDER BY BNO DESC";
		ArrayList<Board> boardR = new ArrayList<Board>();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Board b = new Board();
				b.setBno(rs.getInt("BNO"));
				b.setBwriter(rs.getString("BWRITER"));
				b.setBtitle(rs.getString("BTITLE"));
				b.setBcontents(rs.getString("BCONTENTS"));
				b.setBhits(rs.getInt("BHITS"));
				b.setBstate(rs.getString("BSTATE"));
				boardR.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return boardR;
	}

	public Board getBoard(int viewBno) {
		System.out.println("dao - selectBiardList() 호출");
		Connection con = getConnection();
		if(con == null) {
			return new Board();
		}
		String sql = "SELECT * FROM BOARDS WHERE BNO = ?";
		Board boardResult = new Board();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, viewBno);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				boardResult = new Board();
				boardResult.setBno(rs.getInt("BNO"));
				boardResult.setBwriter(rs.getString("BWRITER"));
				boardResult.setBtitle(rs.getString("BTITLE"));
				boardResult.setBcontents(rs.getString("BCONTENTS"));
				boardResult.setBhits(rs.getInt("BHITS"));
				boardResult.setBstate(rs.getString("BSTATE"));
			}
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return boardResult;
	}

	public int updateBoardHits(int viewBno) {
		Connection con = getConnection();
		if(con == null) {
			return 0;
		}
		
		String sql = "UPDATE BOARDS SET BHITS = BHITS +1000 WHERE BNO = ?";
		int result = 0;
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, viewBno);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	
	public int updateBstate(int delBno) {
		Connection con = getConnection();
		if(con == null) {
			return 0;
		}
		
		String sql = "UPDATE BOARDS SET BHITS = '0' WHERE BNO = ?";
		int result = 0;
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, delBno);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Board> selectBoardList(String searchTitle) {
		System.out.println("dao - selectBiardList() 호출");
		Connection con = getConnection();
		if(con == null) {
			return new ArrayList<Board>();
		}
//		String sql = "SELECT * FROM BOARDS WHERE BSTATE = '1' AND BTITLE LIKE '%'||?||'%'";
		String sql = "SELECT * FROM BOARDS WHERE BSTATE = '1' AND BTITLE LIKE '%'+searchTitle+'%'  ORDER BY BNO DESC";
		ArrayList<Board> boardList = new ArrayList<Board>();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Board b = new Board();
				b.setBno(rs.getInt("BNO"));
				b.setBwriter(rs.getString("BWRITER"));
				b.setBtitle(rs.getString("BTITLE"));
				b.setBcontents(rs.getString("BCONTENTS"));
				b.setBhits(rs.getInt("BHITS"));
				b.setBstate(rs.getString("BSTATE"));
				boardList.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return boardList;
	}

	

	

}

package kr.co.itcen.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import kr.co.itcen.mysite.vo.BoardVo;

@Repository
public class BoardDao {

	/////insert/////

	public Boolean insert(BoardVo boardVo) {
		Boolean result = false;
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			connection = getConnection();

			
			boardVo.setOrderNumber(1);
			
			String sql1 = "insert into board values(null,?,?,0,now(),(select ifnull(max(g_no) + 1, 1) from board as b),?,?,?,1)";
			pstmt = connection.prepareStatement(sql1);
			
			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getContents());
			pstmt.setInt(3, boardVo.getOrderNumber());
			pstmt.setInt(4, boardVo.getDepth());
			pstmt.setLong(5, boardVo.getUserNo());
			
			int count = pstmt.executeUpdate();
			result = (count == 1);

			stmt = connection.createStatement();
			
		

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	
	

	public Boolean replyInsert(BoardVo boardVo) {
		Boolean result = false;
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		
		// insert할 글(boardVo) 데이터 설정
		//o_no, g_no, depth 등 설정
		BoardVo parentVo = this.get(boardVo.getNo());
		
		boardVo.setGroupNumber(parentVo.getGroupNumber());
		boardVo.setOrderNumber(parentVo.getOrderNumber() + 1);
		boardVo.setDepth(parentVo.getDepth() + 1);


		try {
			connection = getConnection();

			String sql1 = "update board " + 
					"set o_no = o_no + 1 " + 
					"where g_no =? and o_no >= ?";
			pstmt = connection.prepareStatement(sql1);
			
			pstmt.setLong(1, boardVo.getGroupNumber());
			pstmt.setLong(2, boardVo.getOrderNumber());
		
			pstmt.executeUpdate();
			
			
			String sql2 = "insert into board(no,title,contents,hit,reg_date,g_no,o_no,depth,user_no,status) value(null,?,?,0,now(),?,?,?,?,1)";
			pstmt = connection.prepareStatement(sql2);
			
			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getContents());
			pstmt.setInt(3, boardVo.getGroupNumber());
			pstmt.setInt(4, boardVo.getOrderNumber());
			pstmt.setInt(5, boardVo.getDepth());
			pstmt.setLong(6, boardVo.getUserNo());
			
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	
	/////select 게시글 제목 클릭해서 View할 내용 조회하기/////
	public BoardVo get(Long no) {
		
		BoardVo vo = null;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = getConnection();
				
			
			String sql1 = "update board " + 
					"set hit = hit+1 " + 
					"where no = ?;";
			pstmt = connection.prepareStatement(sql1);
			pstmt.setLong(1, no);
			
			pstmt.executeUpdate();
			
			String sql2 = "select title,contents,no,user_no,g_no,o_no,depth from board where no = ?";
			pstmt = connection.prepareStatement(sql2);
			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String title = rs.getString(1);
				String contents = rs.getString(2);
				Long no1 = rs.getLong(3);
				Long userNo1 = rs.getLong(4);
				int groupNo = rs.getInt(5);
				int orderNo = rs.getInt(6);
				int depth = rs.getInt(7);
				
				vo = new BoardVo();
				
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setNo(no1);
				vo.setUserNo(userNo1);
				vo.setGroupNumber(groupNo);
				vo.setOrderNumber(orderNo);
				vo.setDepth(depth);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return vo;
	}
	

	/////update 수정하기/////

	public void update (String title, String contents, Long no, Long userNo){

		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			
			connection = getConnection();

			String sql = "update board set title =?, contents =? where no = ? and user_no = ?";

			pstmt = connection.prepareStatement(sql);
	
			
			pstmt.setString(1, title);
			pstmt.setString(2, contents);
			pstmt.setLong(3, no);
			pstmt.setLong(4, userNo);
			
			pstmt.executeUpdate();	
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
			
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	/////delete 삭제하기 /////
	
	public void delete(Long no, Long userNo) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = getConnection();
			
			String sql = "update board set status=0 where no=?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, no );
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private Connection getConnection() throws SQLException {
		Connection connection = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");

			String url = "jdbc:mariadb://192.168.1.78:3306/webdb?characterEncoding=utf8";
			connection = DriverManager.getConnection(url, "webdb", "webdb");

		} catch (ClassNotFoundException e) {
			System.out.println("Fail to Loading Driver:" + e);
		}

		return connection;
	}

}

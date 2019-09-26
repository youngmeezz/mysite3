package kr.co.itcen.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.mysite.vo.GuestBookVo;

@Repository
public class GuestBookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired 
	private DataSource dataSource;
	
	public Boolean insert(GuestBookVo vo) {
		Boolean result = false;
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			connection = dataSource.getConnection();

			String sql = "insert into guestbook values(null,?,?,?, now())";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, vo.getText());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getPassword());

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


	
	public void delete(GuestBookVo vo) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = dataSource.getConnection();
			
			String sql = "select password from guestbook where no = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, vo.getNo());
			rs = pstmt.executeQuery();
			
			String pass = null;
			
			if(rs.next()) {
				pass = rs.getString(1);
			}
			
			if(pass.equals(vo.getPassword())) {
				sql = "delete from guestbook where no = ?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setLong(1, vo.getNo());
				
				pstmt.executeUpdate();
			}
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
	
	public List<GuestBookVo> getList() {
//		List<GuestBookVo> result = new ArrayList<GuestBookVo>();
		List<GuestBookVo> result = sqlSession.selectList("guestbook.getList");
		return result;
	}
}
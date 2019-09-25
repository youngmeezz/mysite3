package kr.co.itcen.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.mysite.vo.GuestBookVo;

@Repository
public class GuestBookDao {
	
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

//	private Connection getConnection() throws SQLException {
//		Connection connection = null;
//
//		try {
//			Class.forName("org.mariadb.jdbc.Driver");
//
//			String url = "jdbc:mariadb://192.168.1.78:3306/webdb?characterEncoding=utf8";
//			connection = DriverManager.getConnection(url, "webdb", "webdb");
//
//		} catch (ClassNotFoundException e) {
//			System.out.println("Fail to Loading Driver:" + e);
//		}
//
//		return connection;
//	}

	
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
		List<GuestBookVo> result = new ArrayList<GuestBookVo>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = dataSource.getConnection();

			String sql = "select no,ifnull(content,''),writer,date_format(registerdate,'%Y-%m-%d %h:%i:%s') from guestbook order by registerdate desc";
			pstmt = connection.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long no = rs.getLong(1);
				String content = rs.getString(2);
				String writer = rs.getString(3);
				String registerdate = rs.getString(4);

				GuestBookVo vo = new GuestBookVo();
				vo.setNo(no);
				vo.setText(content);
				vo.setWriter(writer);
				vo.setRegisterdate(registerdate);

				result.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
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
		return result;
	}
}


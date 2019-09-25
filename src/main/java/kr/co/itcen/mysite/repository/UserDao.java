package kr.co.itcen.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.mysite.exception.UserDaoException;
import kr.co.itcen.mysite.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSession sqlSession;
	
	public UserVo update (UserVo vo){
		
		// TODO Auto-generated method stub
		UserVo result = null;
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			
			connection = dataSource.getConnection();

			String sql = "update user set name = ?, password = ?, gender = ? where no = ?";

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getGender());
			pstmt.setLong(4, vo.getNo());
			
			pstmt.executeUpdate();	
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// 자원정리해주기 Connection
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	public Boolean insert(UserVo vo) throws UserDaoException {
		
		int count = sqlSession.insert("user.insert", vo);
		return count == 1;
	}
	
	public UserVo get(String email,String password) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("email", email);
		map.put("password", password);
		
		UserVo result = sqlSession.selectOne("user.getByEmailAndPassword2",map);
		return result;
	}
	
	public UserVo get(UserVo vo) {
		UserVo result = sqlSession.selectOne("user.getByEmailAndPassword1",vo);
		return result;
	}
	
	public UserVo getByNo(UserVo vo) {
		UserVo result = null;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		

		try {
			connection = dataSource.getConnection();

			String sql = "select no,name,gender,email from user where no = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, vo.getNo());
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String gender = rs.getString(3);
				String email = rs.getString("email");
				
				result = new UserVo();
				result.setNo(no);
				result.setName(name);
				result.setGender(gender);
				result.setEmail(email);
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

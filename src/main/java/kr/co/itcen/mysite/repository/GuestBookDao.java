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

import kr.co.itcen.mysite.exception.GuestBookDaoException;
import kr.co.itcen.mysite.exception.UserDaoException;
import kr.co.itcen.mysite.vo.GuestBookVo;
import kr.co.itcen.mysite.vo.UserVo;

@Repository
public class GuestBookDao {
	
	@Autowired
	private SqlSession sqlSession;

	//방명록 글쓸때 insert
	public Boolean insert(GuestBookVo vo) throws GuestBookDaoException {
		
		int count = sqlSession.insert("guestbook.insert", vo);
		return count == 1;
	}
		

//		//번호로 검색해서 가져올때의 get 오버로드1
//		public UserVo get(Long no) {
//			return sqlSession.selectOne("user.getByNo", no);
//		}
//		

	//방명록 글 삭제할 때 delete
	public Boolean delete(GuestBookVo vo) throws GuestBookDaoException{
		
		int count = sqlSession.delete("guestbook.delete", vo);
		return count == 1;
	}
	
	//방명록 글 조회할 때 getList
	public List<GuestBookVo> getList() {
		List<GuestBookVo> result = sqlSession.selectList("guestbook.getList");
		return result;
	}
}
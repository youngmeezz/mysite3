package kr.co.itcen.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.mysite.exception.GuestBookDaoException;
import kr.co.itcen.mysite.vo.GuestBookVo;

@Repository
public class GuestBookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//방명록 글쓸때 insert
	public Boolean insert(GuestBookVo vo) throws GuestBookDaoException {
		
		int count = sqlSession.insert("guestbook.insert", vo);
		return count == 1;
	}
		

	//방명록 글 삭제할 때 delete
	public Boolean delete(GuestBookVo vo) throws GuestBookDaoException{
		
		int count = sqlSession.delete("guestbook.delete", vo);
		return count == 1;
	}
	
	//방명록 글 조회할 때 getList
	public List<GuestBookVo> getList() throws GuestBookDaoException{
		List<GuestBookVo> result = sqlSession.selectList("guestbook.getList");
		return result;
	}
}
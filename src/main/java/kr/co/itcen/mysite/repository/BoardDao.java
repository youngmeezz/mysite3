package kr.co.itcen.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.mysite.exception.BoardDaoException;
import kr.co.itcen.mysite.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;

	/////select 게시판 첫 조회하기User랑 Board랑 조인한 부분 (성공) 검색 기능 빼고/////
	public List<BoardVo> getList() throws BoardDaoException{
		
		List<BoardVo> result = sqlSession.selectList("board.getList");
		return result;
		
	}
	
	/////insert 글쓰기(성공)/////
	public Boolean insert(BoardVo boardVo) {

		int count = sqlSession.insert("board.insert", boardVo);
		return count == 1;
		
	}
	
	
	/////select 게시글 제목 클릭해서 View할 내용 조회하기/////
	public BoardVo get(Long no, Long userNo) {
		
		return sqlSession.selectOne("board.getByNo", no);
		
	}
	
	
	//// replyInsert 답글쓰기  업데이트 부분 어떻게 받아와야할지 모르겠???????????????????????///////
	public Boolean replyInsert(BoardVo boardVo) {
		
		int count = sqlSession.insert("board.replyInsert", boardVo);
		return count == 1;
	}
	
	
	

	

	/////update 수정하기/////

//	public Boolean update(Long no, Long userNo){
//		
//		Map<String,Long> map = new HashMap<String,Long>();
//		map.put("no", no);
//		map.put("userNo", userNo);
//		
//		BoardVo result = sqlSession.update("board.update",map);
//		return result;
//	}

	
	/////delete 삭제하기 /////
//	
//	public BoardVo delete(Long no, Long userNo) {
//
//		Map<Long,Long> map = new HashMap<Long,Long>();
//		map.put(no, no);
//		map.put(userNo, userNo);
//		
//		BoardVo result = sqlSession.delete("board.deleteByNoAnduserNo",map);
//		return result;
//	}
}
	

	

	
package kr.co.itcen.mysite.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.mysite.vo.BoardVo;

@Repository
public class BoardDao {

	
	@Autowired
	private SqlSession sqlSession;

	/////insert 글쓰기/////

	public Boolean insert(BoardVo boardVo) {

		int count = sqlSession.insert("board.insert", boardVo);
		return count == 1;
		
	}
	
	//// replyInsert 답글쓰기  업데이트 부분 어떻게 받아와야할지 모르겠???????????????????????///////
	public Boolean replyInsert(BoardVo boardVo) {
		
		int count = sqlSession.insert("board.replyInsert", boardVo);
		return count == 1;
	}
	
	
	
	/////select 게시글 제목 클릭해서 View할 내용 조회하기/////
	public BoardVo get(Long no) {
		
		return sqlSession.selectOne("board.getByNo", no);
		
	}
	

	/////update 수정하기/////

	public Boolean update(BoardVo boardVo){
		int count = sqlSession.update("board.update",boardVo);
		return count == 1;
	}

	
	/////delete 삭제하기 /////
	
	public BoardVo delete(Long no, Long userNo) {

		Map<Long,Long> map = new HashMap<Long,Long>();
		map.put(no, no);
		map.put(userNo, userNo);
		
		BoardVo result = sqlSession.selectOne("board.deleteByNoAnduserNo",map);
		return result;
	}
}
	

	

	
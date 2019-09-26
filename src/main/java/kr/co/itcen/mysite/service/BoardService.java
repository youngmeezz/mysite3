package kr.co.itcen.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.mysite.repository.BoardDao;
import kr.co.itcen.mysite.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	public List<BoardVo> getList() {
		return boardDao.getList();
	}

	public void insert(BoardVo vo) {
		boardDao.insert(vo);
	}
	
	public BoardVo get(Long no, Long userNo){
		return boardDao.get(no,userNo);
	}

	
//	public void update(Long no, Long userNo) {
//		boardDao.update(no, userNo);
//	}
//	
//	public void delete(Long no, Long userNo) {
//		boardDao.delete(no, userNo);
//	}
}

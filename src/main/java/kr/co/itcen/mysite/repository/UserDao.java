package kr.co.itcen.mysite.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.mysite.exception.UserDaoException;
import kr.co.itcen.mysite.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//회원 가입 할때 insert
	public Boolean insert(UserVo vo) throws UserDaoException {
		
		int count = sqlSession.insert("user.insert", vo);
		return count == 1;
	}
	
	//회원 정보 수정할때 update
	public Boolean update(UserVo vo) {
		int count = sqlSession.update("user.update",vo);
		return count == 1;
	}
	
	//번호로 검색해서 가져올때의 get 오버로드1
	public UserVo get(Long no) {
		return sqlSession.selectOne("user.getByNo", no);
	}
	
	//UserVo vo의 파라미터를 가지고 이메일과 패스워드로 검색해서 가져올때의 get 오버로드2
	public UserVo get(UserVo vo) {
		UserVo result = sqlSession.selectOne("user.getByEmailAndPassword1",vo);
		return result;
	}
	
	//email, password의 파라미터를 가지고 이메일과 패스워드로 검색해서 가져올때의 get 오버로드3
	public UserVo get(String email,String password) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("email", email);
		map.put("password", password);
		
		UserVo result = sqlSession.selectOne("user.getByEmailAndPassword2",map);
		return result;
	}
	
}

package kr.co.itcen.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.mysite.exception.UserDaoException;
import kr.co.itcen.mysite.repository.UserDao;
import kr.co.itcen.mysite.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;

	public void join(UserVo vo) {
		// TODO Auto-generated method stub
			userDao.insert(vo);
	}

	public UserVo getUser(UserVo vo) {
		// TODO Auto-generated method stub
		return userDao.get(vo);
	}

	public UserVo update(UserVo vo) {
		// TODO Auto-generated method stub
		return userDao.update(vo);
	}
	
	public UserVo getUserByNo(UserVo vo) {
		return userDao.getByNo(vo);
	}
}

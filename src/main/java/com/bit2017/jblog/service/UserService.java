package com.bit2017.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit2017.jblog.repository.UserDao;
import com.bit2017.jblog.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public boolean join(UserVo userVo) {
		boolean result = userDao.insert(userVo);
		return result;
	}

	public UserVo getUser(String userId, String password) {
		UserVo userVo = userDao.get(userId, password);
		return userVo;
	}

	public boolean exists(String userId) {
		UserVo userVo = userDao.get(userId);
		return (userVo != null);
	}

}

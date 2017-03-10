package com.bit2017.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit2017.jblog.repository.BlogDao;
import com.bit2017.jblog.repository.CategoryDao;
import com.bit2017.jblog.repository.UserDao;
import com.bit2017.jblog.vo.BlogVo;
import com.bit2017.jblog.vo.CategoryVo;
import com.bit2017.jblog.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CategoryDao categoryDao;
	
	@Transactional
	public boolean join(UserVo userVo) {
		
		BlogVo blogVo = new BlogVo();
		blogVo.setBlogId(userVo.getUserId());
		blogVo.setLogo("비트");
		blogVo.setTitle(userVo.getName() + "의 이야기");
		
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setCname("블로그 소개");
		categoryVo.setDescription("안녕하세요 " + userVo.getName() + " 의 블로그입니다.");
		categoryVo.setBlogId(userVo.getUserId());
		
		boolean result1 = userDao.insert(userVo);
		boolean result2 = blogDao.insert(blogVo);
		boolean result3 = categoryDao.insert(categoryVo);
		return result1&&result2&&result3;
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

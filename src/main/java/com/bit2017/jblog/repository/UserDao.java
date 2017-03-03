package com.bit2017.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit2017.jblog.vo.UserVo;

@Repository
public class UserDao {
	@Autowired
	private SqlSession sqlSession;

	public boolean insert(UserVo userVo) {
		int count = sqlSession.insert("user.insert", userVo);
		return (count == 1);
	}

	public UserVo get(String userId) {
		UserVo userVo = sqlSession.selectOne("user.getByUserID", userId);
		return userVo;
	}
	
	public UserVo get(String userId, String password) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("password", password);
		
		UserVo userVo = sqlSession.selectOne("user.getByUserIdAndPassword",map);
		return userVo;
	}

}

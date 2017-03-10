package com.bit2017.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit2017.jblog.vo.CategoryVo;

@Repository
public class CategoryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<CategoryVo> getList(String blogId) {
		return sqlSession.selectList("category.getList", blogId);
	}
	
	public boolean insert(CategoryVo categoryVo) {
		int count = sqlSession.insert("category.insert", categoryVo);
		return (count == 1);
	}

	public CategoryVo getList(Long cno) {
		return sqlSession.selectOne( "category.getByNo", cno );
	}

	public boolean delete(CategoryVo categoryVo) {
		int count = sqlSession.delete( "category.delete", categoryVo );
		return (count == 1);
	}	


}

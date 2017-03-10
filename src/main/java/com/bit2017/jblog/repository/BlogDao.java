package com.bit2017.jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit2017.jblog.vo.BlogVo;
import com.bit2017.jblog.vo.PostVo;

@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;

	public List<BlogVo> getList() {
		return sqlSession.selectList("blog.getList");
	}
	
	public BlogVo getInfo(String blogId) {
		return sqlSession.selectOne("blog.getInfo", blogId);
	}
	
	public boolean insert(BlogVo blogVo) {
		int count = sqlSession.insert("blog.insert", blogVo);
		return (count == 1);
	}

	public int write(PostVo postVo) {
		return sqlSession.insert("blog.write", postVo);
	}

	public boolean update(BlogVo blogVo) {
		int count = sqlSession.update("blog.update", blogVo);
		return (count ==1 );
	}

	public List<PostVo> postList() {
		return sqlSession.selectList("blog.postList");
	}

	public List<PostVo> cateListView(Long cno) {
		return sqlSession.selectList("blog.cateListView", cno);
	}

	public PostVo view(Long pvo) {
		return sqlSession.selectOne("blog.view", pvo);
	}

	public Long maxPost(String blogId) {
		return sqlSession.selectOne("blog.maxPost", blogId);
	}

	public Long catemaxPost(Map<String, Object> map) {
		return sqlSession.selectOne("blog.catemaxPost", map);
	}
	

	
	

}

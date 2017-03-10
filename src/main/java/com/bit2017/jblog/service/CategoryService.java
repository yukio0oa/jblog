package com.bit2017.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit2017.jblog.repository.CategoryDao;
import com.bit2017.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	public List<CategoryVo> getTitleList(String blogId) {
		System.out.println("cate"+blogId);
		return categoryDao.getList(blogId);
	}

	public boolean addMessage(CategoryVo vo) {
		return categoryDao.insert(vo);
		
	}

	public CategoryVo getMessage(Long cno) {
		return categoryDao.getList(cno);
	}

	public boolean removeMessage(CategoryVo vo) {
		return categoryDao.delete(vo);
	}

}

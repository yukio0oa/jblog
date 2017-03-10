package com.bit2017.jblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit2017.jblog.dto.JSONResult;
import com.bit2017.jblog.service.BlogService;
import com.bit2017.jblog.service.CategoryService;
import com.bit2017.jblog.vo.BlogVo;
import com.bit2017.jblog.vo.CategoryVo;
import com.bit2017.jblog.vo.UserVo;
import com.bit2017.security.Auth;
import com.bit2017.security.AuthUser;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private BlogService blogService;
	
	@Auth
	@RequestMapping(value="/{userId}/admin/category")
	public String category(@AuthUser UserVo authUser,
						   @RequestParam(value="blogId",required=true, defaultValue="") String blogId,
						   @PathVariable("userId") String userId,
						   Model model) {
		blogId = authUser.getUserId();
		BlogVo bvo = blogService.getInfo(blogId);
		List<CategoryVo> list = categoryService.getTitleList(blogId);
		model.addAttribute("bvo", bvo);
		model.addAttribute("list", list);
		return "blog/blog-admin-category";
	}
	
	@ResponseBody
	@RequestMapping( "/add" )
	public JSONResult add( @ModelAttribute CategoryVo categoryVo ){
		categoryService.addMessage( categoryVo );
		categoryVo = categoryService.getMessage( categoryVo.getCno() );
		return JSONResult.success( categoryVo );
	}
	
	@ResponseBody
	@RequestMapping( "/delete" )
	public JSONResult delete( @ModelAttribute CategoryVo categoryVo ){
		boolean result = categoryService.removeMessage( categoryVo );
		return JSONResult.success( result ? categoryVo.getCno() : -1 );
	}


}

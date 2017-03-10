package com.bit2017.jblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bit2017.jblog.service.BlogService;
import com.bit2017.jblog.service.CategoryService;
import com.bit2017.jblog.vo.BlogVo;
import com.bit2017.jblog.vo.CategoryVo;
import com.bit2017.jblog.vo.PostVo;
import com.bit2017.jblog.vo.UserVo;
import com.bit2017.security.Auth;
import com.bit2017.security.AuthUser;

@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("/{userId}")
	public String myblog(@PathVariable("userId") String userId,
						 @RequestParam(value="blogId",required=true, defaultValue="") String blogId,
						 @RequestParam(value="cno",required=true, defaultValue="") Long cno,
						 @RequestParam(value="pno",required=true, defaultValue="") Long pno,
						 @ModelAttribute PostVo postVo,
						 @AuthUser UserVo authUser,
						 Model model) {
		blogId = authUser.getUserId();
		BlogVo bvo = blogService.getInfo(blogId);
		List<CategoryVo> clist = categoryService.getTitleList(blogId);
		List<PostVo> list = blogService.cateListView(cno);
		PostVo pvo = blogService.postView(blogId, pno, cno);
		model.addAttribute("bvo", bvo);
		model.addAttribute("clist", clist);
		model.addAttribute("list", list);
		model.addAttribute("pvo", pvo);
		model.addAttribute("cno", cno);
		
		return "blog/blog-main";
	}
	
/*	@RequestMapping("/{userId}/view")
	public String view(@PathVariable("userId") String userId,
					   @ModelAttribute CategoryVo categoryVo,
					   Model model){
		PostVo postVo = blogService.view(userId);
		model.addAttribute("postVo", postVo);
		return "redirect:/{userId}";
	}*/
	
	@Auth
	@RequestMapping("/{userId}/admin/basic")
	public String basic(@RequestParam(value="blogId",required=true, defaultValue="") String blogId,
						@AuthUser UserVo authUser,
						Model model) {
		blogId = authUser.getUserId();
		BlogVo bvo = blogService.getInfo(blogId);
		//List<BlogVo> list = blogService.getList();
		model.addAttribute("bvo", bvo);
		//model.addAttribute("list", list);
		model.addAttribute("baseURL", blogService.BASE_URL);
		return "blog/blog-admin-basic";
	}
	
	@Auth
	@RequestMapping("/{userId}/admin/basic/upload")
	public String upload(@PathVariable("userId") String userId,
						 @AuthUser UserVo authUser,
						 @ModelAttribute BlogVo blogVo,
						 @RequestParam(value="blogId",required=true, defaultValue="") String blogId,
						 @RequestParam("file") MultipartFile multipartFile) {
		blogVo.setBlogId(authUser.getUserId());
		blogService.restore(blogVo, multipartFile);
		return "redirect:/{userId}/admin/basic";
	}
	
	@Auth
	@RequestMapping(value="/{userId}/admin/write", method=RequestMethod.GET)
	public String writeform(@RequestParam(value="blogId",required=true, defaultValue="") String blogId,
							@AuthUser UserVo authUser,
							@PathVariable("userId") String userId,
							Model model) {
		blogId = authUser.getUserId();
		BlogVo bvo = blogService.getInfo(blogId);
		List<CategoryVo> list = categoryService.getTitleList(userId);
		model.addAttribute("bvo", bvo);
		model.addAttribute("list", list);
		return "blog/blog-admin-write";
	}
	
	@Auth
	@RequestMapping(value="/{userId}/admin/write", method=RequestMethod.POST)
	public String write(@AuthUser UserVo authUser,
						@ModelAttribute PostVo postVo,
						@RequestParam(value="cno",required=true, defaultValue="") Long cno,
						@ModelAttribute CategoryVo categoryVo) {
		cno = categoryVo.getCno();
		blogService.write(postVo);
		return "redirect:/{userId}";
	}

}

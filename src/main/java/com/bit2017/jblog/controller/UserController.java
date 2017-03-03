package com.bit2017.jblog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit2017.jblog.dto.JSONResult;
import com.bit2017.jblog.service.UserService;
import com.bit2017.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/checkemail")
	public JSONResult checkEmail(@RequestParam(value="userId", required=true, defaultValue="") String userId) {
		boolean isExists = userService.exists(userId);
		return JSONResult.success(isExists ? "exist" : "not exist");
	}
	
	@RequestMapping("/joinform")
	public String joinform(@ModelAttribute UserVo userVo) {
		return "user/join";
	}
	
	@RequestMapping("/join")
	public String join(@ModelAttribute @Valid UserVo userVo,
					   BindingResult result,
					   Model model){
		if(result.hasErrors() == true) {
			List<ObjectError> list = result.getAllErrors();
			for(ObjectError error : list) {
				System.out.println(error);
			}
			model.addAllAttributes(result.getModel());
			return "/user/join";
		}
		userService.join(userVo);
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping("/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}
	
	@RequestMapping("/loginform")
	public String loginfrom() {
		return "user/login";
	}
	

}

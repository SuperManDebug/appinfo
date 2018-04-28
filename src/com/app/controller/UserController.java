package com.app.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.pojo.BackendUser;
import com.app.service.UserService;

@Controller
@RequestMapping("/managers")
public class UserController {
	
	@Resource
	public UserService userService;
	
	@RequestMapping(value="/login")
	public String login(){
		return "backendlogin";
	}
	
	@RequestMapping(value="/logout")
	public String login(HttpSession session){
		session.invalidate();
		return "redirect:/index.jsp";
	}
	
	@RequestMapping(value="/checkLogin",method = RequestMethod.POST)
	public String checkLongin(String usercode,String userpassword,HttpSession session,Model model){
		BackendUser backendUser = userService.getUser(usercode, userpassword);
		if(backendUser!=null){
			session.setAttribute("backendUser", backendUser);
			return "backend/main";
		}
		model.addAttribute("error", "用户名或密码不正确");
		return "backendlogin";
	}

}

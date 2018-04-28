package com.app.controller;

import com.app.pojo.DevUser;
import com.app.service.DevUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2018/4/21.
 */
@Controller
@RequestMapping("/dev")
public class DevController {

    @Resource
    private DevUserService devUserService;

    @RequestMapping("/login")
    public String login(){
        return "devlogin";
    }

    @RequestMapping(value = "/checkLogin",method = RequestMethod.POST)
    public String checkLogin(String devcode, String devpassword, HttpSession session){
        DevUser devUser = devUserService.getDevUser(devcode,devpassword);
        if (devUser!=null){
            session.setAttribute("devUser",devUser);
        }
        return  "developer/main";
    }

    @RequestMapping(value="/logout")
    public String login(HttpSession session){
        session.invalidate();
        return "redirect:/index.jsp";
    }

}

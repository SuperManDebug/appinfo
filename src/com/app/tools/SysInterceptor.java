package com.app.tools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.app.pojo.BackendUser;
import com.app.pojo.DevUser;

public class SysInterceptor extends HandlerInterceptorAdapter {
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		BackendUser backendUser = (BackendUser) session.getAttribute("backendUser");
		DevUser devUser = (DevUser) session.getAttribute("devUser");
		if (devUser == null) {
			response.sendRedirect(request.getContextPath() +"/jsp/devlogin.jsp");
			return false;
		}
		if(backendUser ==null){
			response.sendRedirect(request.getContextPath()+"/manager/login");
			return false;
		}
		return true;
	}
}

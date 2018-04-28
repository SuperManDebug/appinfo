package com.app.service;

import com.app.pojo.BackendUser;

public interface UserService {
	
	/**
	 * 登录验证
	 * @param usercode
	 * @param userpassword
	 * @return
	 */
	BackendUser getUser(String usercode,String userpassword);
}

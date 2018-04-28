package com.app.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.dao.BackendUserDao;
import com.app.pojo.BackendUser;
import com.app.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource
	private BackendUserDao backendUserDao;
	
	public BackendUser getUser(String usercode, String userpassword) {
		return backendUserDao.selUser(usercode,userpassword);
	}
}

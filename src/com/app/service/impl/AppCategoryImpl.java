package com.app.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.dao.AppCategoryDao;
import com.app.pojo.AppCategory;
import com.app.service.AppCategoryService;

@Service("AppCategoryService")
public class AppCategoryImpl implements AppCategoryService{
	
	@Resource
	private AppCategoryDao appCategoryDao;
	
	public List<AppCategory> getAppLeveOneList() {
		return appCategoryDao.selAppList();
	}
	
	public List<AppCategory> getAppLeveTwoList(Long parentid) {
		return appCategoryDao.selLeven2List(parentid);
	}
}

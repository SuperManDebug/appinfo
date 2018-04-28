package com.app.service;

import java.util.List;

import com.app.pojo.AppCategory;

public interface AppCategoryService {
	
	/**
	 *	获得一级分类
	 */
	List<AppCategory> getAppLeveOneList();
	
	/**
	 *	获得二级分类
	 */
	List<AppCategory> getAppLeveTwoList(Long parentid);
}

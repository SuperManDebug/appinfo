package com.app.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.dao.AppInfoDao;
import com.app.pojo.AppInfo;
import com.app.service.AppInfoService;

@Service("AppInfoService")
public class AppInfoImpl implements AppInfoService {

	@Resource
	private AppInfoDao appInfoDao;

	public List<AppInfo> getAppInfoList(AppInfo appInfo,Integer currentPage,Integer pageSize) {
		return appInfoDao.selAppInfoList(appInfo,currentPage,pageSize);
	}

	public Integer getTotalCount(AppInfo appInfo) {
		return appInfoDao.totalCount(appInfo);
	}

	public AppInfo getAppInfo(Long aid){
		return appInfoDao.findAppInfo(aid);
	}

	public Integer upAppType(Long aid,String status) {
		return appInfoDao.upAppType(aid,status);
	}

	@Override
	public Integer upAppInfoVid(Long id) {
		return appInfoDao.upAppInfoVid(id);
	}

	@Override
	public AppInfo getCheckName(String apkname) {
		return appInfoDao.selCheckName(apkname);
	}

	@Override
	public Integer addAppInfo(AppInfo appInfo) {
		return appInfoDao.insAppInfo(appInfo);
	}

	@Override
	public Integer delAppInfo(Long id) {
		return appInfoDao.delAppInfo(id);
	}

	@Override
	public Integer upAppInfoLogo(Long id) {
		return appInfoDao.upAppInfoLogo(id);
	}

	@Override
	public Integer upAppInfo(AppInfo appInfo) {
		return appInfoDao.upAppInfo(appInfo);
	}
}

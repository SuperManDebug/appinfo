package com.app.service;

import java.util.List;

import com.app.pojo.AppInfo;

public interface AppInfoService {
	/**
	 * 获得所有App信息
	 * @return
	 */
	List<AppInfo> getAppInfoList(AppInfo appInfo,Integer currentPage,Integer pageSize);
	
	/**
	 * 总条数
	 * @param appInfo
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	Integer getTotalCount(AppInfo appInfo);

	/**
	 * 单个App详细信息
	 * @return
     */
	AppInfo getAppInfo(Long aid);

	/**
	 * 审核更新
	 * @return
	 */
	Integer upAppType(Long aid,String status);

	/**
	 * 更新版本id
	 * @param id
	 * @return
	 */
	Integer upAppInfoVid(Long id);

	/**
	 * 验证apkname是否存在
	 * @param apkname
	 * @return
	 */
	AppInfo getCheckName(String apkname);

	/**
	 * 添加App
	 * @return
	 */
	Integer addAppInfo(AppInfo appInfo);

	/**
	 * 删除App
	 * @param id
	 * @return
	 */
	Integer delAppInfo(Long id);

	/**
	 * 删除logo
	 * @return
	 */
	Integer upAppInfoLogo(Long id);

	/**
	 * 修改app
	 * @return
	 */
	Integer upAppInfo(AppInfo appInfo);
}

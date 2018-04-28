package com.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.app.pojo.AppInfo;

public interface AppInfoDao {
    AppInfo selectByPrimaryKey(Long id);
    
    /**
     * 获得所有app信息
     * @return
     */
    List<AppInfo> selAppInfoList(@Param("appInfo")AppInfo appInfo,@Param("currentPage")Integer currentPage,@Param("pageSize")Integer pageSize);
    
    /**
     * 查询总条数
     * @param appInfo
     * @param currentPage
     * @param pageSize
     * @return
     */
    Integer totalCount(AppInfo appInfo);

    /**
     * 单个详细信息
     * @return
     */
    AppInfo findAppInfo(@Param("aid") Long aid);

    /**
     * 更新App状态
     * @return
     */
    Integer upAppType(@Param("id")Long id,@Param("status")String status);

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
    AppInfo selCheckName(String apkname);

    /**
     * 添加App
     * @return
     */
    Integer insAppInfo(AppInfo appInfo);

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
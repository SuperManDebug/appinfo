package com.app.dao;

import com.app.pojo.AppVersion;

import java.util.List;

public interface AppVersionDao {
    AppVersion selectByPrimaryKey(Long id);

    /**
     * app版本历史信息
     * @return
     */
    List<AppVersion> selAppVersionList(Long id);

    /**
     * 查看版本详细信息
     * @return
     */
    AppVersion selAppVersionById(Long id);

    /**
     * 新增app版本
     * @param appVersion
     * @return
     */
    Integer insAppVersion(AppVersion appVersion);

    /**
     * 修改版本信息
     * @param appVersion
     * @return
     */
    Integer upAppVersion(AppVersion appVersion);

    /**
     * 删除apk路径
     * @return
     */
    Integer upAppVersionloc(Long id);

    /**
     * 删除历史版本
     * @return
     */
    Integer delAppVersion(Long aid);

}
package com.app.service;

import com.app.pojo.AppVersion;

import java.util.List;

/**
 * Created by Administrator on 2018/4/21.
 */
public interface AppVersionService {

    /**
     * 根据id查询APP版本信息
     * @return
     */
    AppVersion getAppVersionById(Long id);

    /**
     * APP版本历史信息
     * @param id
     * @return
     */
    List<AppVersion> getAppVersionList(Long id);

    /**
     * 查看版本详细信息
     * @return
     */
    AppVersion selAppVersionById(Long aid);

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

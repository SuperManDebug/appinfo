package com.app.service.impl;

import com.app.dao.AppVersionDao;
import com.app.pojo.AppVersion;
import com.app.service.AppVersionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2018/4/21.
 */
@Service("AppVersionService")
public class AppVersionImpl implements AppVersionService {

    @Resource
    private AppVersionDao appVersionDao;

    @Override
    public AppVersion getAppVersionById(Long id) {
        return appVersionDao.selectByPrimaryKey(id);
    }

    public List<AppVersion> getAppVersionList(Long id){
        return appVersionDao.selAppVersionList(id);
    }

    @Override
    public Integer insAppVersion(AppVersion appVersion) {
        return appVersionDao.insAppVersion(appVersion);
    }

    @Override
    public AppVersion selAppVersionById(Long aid) {
        return appVersionDao.selAppVersionById(aid);
    }

    @Override
    public Integer upAppVersion(AppVersion appVersion) {
        return appVersionDao.upAppVersion(appVersion);
    }

    @Override
    public Integer upAppVersionloc(Long id) {
        return appVersionDao.upAppVersionloc(id);
    }

    @Override
    public Integer delAppVersion(Long aid) {
        return appVersionDao.delAppVersion(aid);
    }
}


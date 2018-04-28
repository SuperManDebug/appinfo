package com.app.service.impl;

import com.app.dao.DevUserDao;
import com.app.pojo.DevUser;
import com.app.service.DevUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2018/4/21.
 */
@Service("DevUserService")
public class DevUserImpl implements DevUserService {

    @Resource
    private DevUserDao devUserDao;

    public DevUser getDevUser(String devcode, String devpassword) {
        return devUserDao.selDevUser(devcode,devpassword);
    }
}

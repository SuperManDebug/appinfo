package com.app.service;

import com.app.pojo.DevUser;

/**
 * Created by Administrator on 2018/4/21.
 */
public interface DevUserService {

    /**
     * 登录验证
     * @return
     */
    DevUser getDevUser(String devcode,String devpassword);
}

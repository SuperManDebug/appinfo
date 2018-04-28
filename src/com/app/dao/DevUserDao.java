package com.app.dao;

import com.app.pojo.DevUser;
import org.apache.ibatis.annotations.Param;

public interface DevUserDao {
    DevUser selectByPrimaryKey(Long id);

    /**
     * 登录验证
     * @return
     */
    DevUser selDevUser(@Param("devcode") String devcode, @Param("devpassword")String devpassword);
}
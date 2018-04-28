package com.app.dao;

import org.apache.ibatis.annotations.Param;

import com.app.pojo.BackendUser;

public interface BackendUserDao {
    BackendUser selectByPrimaryKey(Long id);
    
    /**
     * 登录验证
     * @param usercode
     * @param userpassword
     * @return
     */
	BackendUser selUser(@Param("usercode")String usercode,@Param("userpassword") String userpassword);
}
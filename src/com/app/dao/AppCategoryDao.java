package com.app.dao;

import java.util.List;

import com.app.pojo.AppCategory;
import org.apache.ibatis.annotations.Param;

public interface AppCategoryDao {
    AppCategory selectByPrimaryKey(Long id);
    
    /**
     * 查询一级分类
     * @return
     */
    List<AppCategory> selAppList();
    
    /**
     * 根据一级查询二级
     * @return
     */
    List<AppCategory> selLeven2List(@Param("parentid") Long parentid);
}
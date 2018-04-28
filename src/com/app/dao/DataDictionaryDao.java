package com.app.dao;

import java.util.List;

import com.app.pojo.DataDictionary;

public interface DataDictionaryDao {
    DataDictionary selectByPrimaryKey(Long id);
    
    /**
     * 获得所有app平台信息
     * @return
     */
    List<DataDictionary> selDataDictionaries(String typecode);

}
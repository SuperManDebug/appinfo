package com.app.service;

import java.util.List;

import com.app.pojo.DataDictionary;

public interface DataDictionaryService {
	
	/**
	 * 获得APP平台信息
	 * @return
	 */
	List<DataDictionary> getDataDictionaryList(String typecode);
}

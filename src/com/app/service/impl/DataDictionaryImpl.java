package com.app.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.dao.DataDictionaryDao;
import com.app.pojo.DataDictionary;
import com.app.service.DataDictionaryService;

@Service("DataDictionaryService")
public class DataDictionaryImpl implements DataDictionaryService {
	
	@Resource
	private DataDictionaryDao dataDictionaryDao;

	public List<DataDictionary> getDataDictionaryList(String typecode) {
		return dataDictionaryDao.selDataDictionaries(typecode);
	}

}

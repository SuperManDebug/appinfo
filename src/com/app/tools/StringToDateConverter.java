package com.app.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class StringToDateConverter implements Converter<String,Date> {

	private String datepattern;
	
	public StringToDateConverter(String datepattern) {
		super();
		this.datepattern = datepattern;
	}

	public Date convert(String s) {
		Date date = null;
		try {
			date  = new SimpleDateFormat(datepattern).parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

}

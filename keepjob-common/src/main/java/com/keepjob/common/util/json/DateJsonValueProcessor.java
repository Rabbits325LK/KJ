package com.keepjob.common.util.json;

import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import com.keepjob.common.util.DateUtils;

public class DateJsonValueProcessor implements JsonValueProcessor {

	public Object processArrayValue(Object value, JsonConfig config) {
		return this.process(value);
	}

	public Object processObjectValue(String key, Object value, JsonConfig config) {
		return this.process(value);
	}

	private Object process(Object value){
		return DateUtils.date2string((Date)value, DateUtils.DATE_FORMAT);
	}
}

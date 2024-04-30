package com.ian.media.util;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * JSON日期类型处理器
 * User: ZhangKe
 * Date: 12-8-4
 * Time: 下午3:21
 * Version: 1.0
 */
public class JSONDateProcessor implements JsonValueProcessor {
    private String datePattern = "yyyy-MM-dd";

    public JSONDateProcessor() {
    }

    public JSONDateProcessor(String datePattern) {
        this.datePattern = datePattern;
    }

    private Object process(Object value) {
        SimpleDateFormat format = new SimpleDateFormat(datePattern, Locale.UK);
        if (value instanceof Date) {
            return format.format((Date) value);
        } else if (value instanceof Timestamp) {
            return format.format((Timestamp) value);

        }
        return value == null ? "" : value.toString();
    }

    public Object processArrayValue(Object value, JsonConfig jsonConfig) {
        return process(value);
    }

   
    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
        return process(value);
    }

    public String getDatePattern() {
        return datePattern;
    }

    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }
}

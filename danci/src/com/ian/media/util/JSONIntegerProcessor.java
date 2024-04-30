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
public class JSONIntegerProcessor implements JsonValueProcessor {
    public JSONIntegerProcessor() {
    }

    private Object process(Object value) {
        if (value instanceof Integer) {
                if(null==value)
                    return new Integer(-1);
        }
        return value == null ? "" : value.toString();
    }

    public Object processArrayValue(Object value, JsonConfig jsonConfig) {
        return process(value);
    }

   
    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
        return process(value);
    }
}

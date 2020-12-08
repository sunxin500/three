package com.definesys.rt.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;

/**
 * json格式转换类
 */
public class JsonUtil {

    /**
     * 转换json格式字符串
     * @param object
     * @return
     */
    public static String getJsonString(Object object){
        ObjectMapper mapper = new ObjectMapper();
        try {
            String str = mapper.writeValueAsString(object);
            return str;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将时间转换成指定格式字符串
     * @param String 时间格式参数
     * @param date 时间参数
     * @return
     */
    public static String getJsonDate(Object date,String String){
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS,false);
            SimpleDateFormat format = new SimpleDateFormat(String);
            mapper.setDateFormat(format);
            return mapper.writeValueAsString(date);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 将时间转成"yyyy-MM-dd HH:mm:ss"格式的字符串
     * @param date 时间参数
     * @return
     */
    public static String getJsonDate(Object date){
        return getJsonDate(date,"yyyy-MM-dd HH:mm:ss");
    }

}

package com.definesys.rt.util;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 */
public class StringUtil {


    /**
     * 字符串拼接
     * @param str1
     * @param str2
     * @return
     */
    public static String strAppend(String str1,String str2){
        return new StringBuilder(str1).append(str2).toString();
    }

    /**
     * 通过分隔符分割字符串保存list集合
     * @param str
     * @return
     */
    public static List<String> splitString(String str,String regex){
        if (!isEmpty(str)){
            String[] split = str.split(regex);
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < split.length; i++) {
                list.add(split[i]);
            }
            return list;
        }else {
            return null;
        }
    }

    /**
     * 常见特殊字符过滤
     *
     * @param str
     * @return
     */
    public static String filtration(String str) {
        String regEx = "[`~!@#$%^&*()+=|{}:;\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？']";
        str = Pattern.compile(regEx).matcher(str).replaceAll("").trim();
        return str;
    }

    /**
     * 字符串判空
     * @param param
     * @return
     */
    public static boolean isEmpty(String param){
        return param == null || param.length() == 0;
    }


    /**
     * 可变参数判空字符串
     * @param params
     * @return
     */
    public static boolean isEmpty(String... params){
        for (String str : params) {
            if (str == null || str.length() == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取完整工号
     *
     * @param employeeCode 未处理过的工号
     * @return
     */
    public static String getWholeEmployeeCode(String employeeCode) {
        if(StringUtils.isEmpty(employeeCode)){
            return null;
        }
        employeeCode = employeeCode.replaceAll("[a-zA-Z]", "");
        return String.format("%08d", Long.parseLong(employeeCode));
    }



    @Test
    public void test(){
        boolean empty = isEmpty("","a");
        boolean empty1 = org.apache.commons.lang3.StringUtils.isEmpty("1");
        System.out.println(empty);
    }




}

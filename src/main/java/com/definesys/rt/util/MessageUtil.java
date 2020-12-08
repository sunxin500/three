package com.definesys.rt.util;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 消息响应工具类
 */
public class MessageUtil {

    /**
     * 响应成功且携带数据（一个）
     * @param e 响应数据
     * @param <E> 数据类型
     * @return
     */
    public static <E>Message<E> success(E e){
        return new Message<>(200,"success",e,new Date().getTime());
    }

    /**
     * 响应成功且携带数据(多个)
     * @param list 响应数据
     * @param <E> 数据类型
     * @return
     */
    public static <E>Message<List<E>> success(List<E> list){
        return new Message<>(200,"success",list,new Date().getTime());
    }

//    /**
//     * 响应成功且携带数据(多个)
//     * @param list 响应数据
//     * @param <E> 数据类型
//     * @return
//     */
//    public static <E>Message<List<Map<E,E>>> success(List<Map<E,E>> list){
//        return new Message<>(200,"success",list,new Date().getTime());
//    }

    /**
     * 响应成功且携带数据(多个)
     * @param map 响应数据
     * @param <E> 数据类型
     * @return
     */
    public static <E>Message<Map<E,E>> success(Map<E,E> map){
        return new Message<>(200,"success",map,new Date().getTime());
    }


    /**
     * 响应成功但不携带数据
     * @param <E>
     * @return
     */
    public static <E>Message<E> success(){
        return new Message<>(200,"success",null,new Date().getTime());
    }

    /**
     * 响应失败
     * @param code 状态码
     * @param massage 错误信息
     * @param <E>
     * @return
     */
    public static <E>Message<E> error(Integer code,String massage){
        return new Message<>(code,massage,null,new Date().getTime());
    }
}

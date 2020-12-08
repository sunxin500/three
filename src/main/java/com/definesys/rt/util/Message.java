package com.definesys.rt.util;


/**
 * 消息响应类
 */
public class Message<T> {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 响应回去的信息
     */
    private String massage;

    /**
     * 响应回去的数据
     */
    private T data;

    /**
     * 响应回去的时间戳
     */
    private long time;

    public Message() {
        super();
    }

    public Message(Integer code, String massage, T data, long time) {
        this.code = code;
        this.massage = massage;
        this.data = data;
        this.time = time;
    }



    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Message{" +
                "code=" + code +
                ", massage='" + massage + '\'' +
                ", data=" + data +
                ", time=" + time +
                '}';
    }
}

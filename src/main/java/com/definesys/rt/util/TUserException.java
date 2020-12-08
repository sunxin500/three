package com.definesys.rt.util;

/**
 * 自定义异常类
 */
public class TUserException extends RuntimeException {

    private Integer code;

    public TUserException(Integer code){
        super();
        this.code = code;
    }

    public TUserException(Integer code,String massage){
        super(massage);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}

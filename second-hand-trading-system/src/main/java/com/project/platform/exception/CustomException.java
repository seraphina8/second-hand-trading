package com.project.platform.exception;

import lombok.Getter;

/**
 * 自定义异常
 */
@Getter
public class CustomException extends RuntimeException{

    private Integer code;
    public CustomException(Integer code,String msg){
        super(msg);
        this.code = code;
    }
    public CustomException(String msg){
        super(msg);
        this.code=500;
    }

}

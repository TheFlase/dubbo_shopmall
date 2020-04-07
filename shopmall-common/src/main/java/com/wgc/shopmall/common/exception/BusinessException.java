package com.wgc.shopmall.common.exception;

/**
 * @Author wgc
 * @Description
 * @Date 3/27/2020
 **/
public class BusinessException extends RuntimeException{
    private String businessCode;

    public BusinessException(String message, String businessCode) {
        super(message);
        this.businessCode = businessCode;
    }
    public BusinessException(String message) {
        super(message);
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

}

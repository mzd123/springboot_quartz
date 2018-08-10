package com.mzd.my_boot_quartz.bean;


public class Result {
    private String code;
    private String errormessage;
    private Object obj;

    public Result(String code, String errormessage, Object obj) {
        this.code = code;
        this.errormessage = errormessage;
        this.obj = obj;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrormessage() {
        return errormessage;
    }

    public void setErrormessage(String errormessage) {
        this.errormessage = errormessage;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public Result() {
    }
}
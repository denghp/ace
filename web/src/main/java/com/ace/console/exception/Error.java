package com.ace.console.exception;

/**
 * Created with ace.
 * User: denghp
 * Date: 10/27/13
 * Time: 11:04 PM
 */
public class Error {

    private int code;
    private String msg;

    public Error() {
        super();
    }

    public Error(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

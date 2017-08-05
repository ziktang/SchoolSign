package com.schoolsign.common.retrofit;

import static android.R.attr.data;

/**
 * Created by tctctc on 2017/6/21.
 * Function:
 */

public class HttpResult<T> {
    private int code;
    private String message;
    private T result;

    @Override
    public String toString() {
        String r = "code:"+code+"/n message:"+message+"/n ";
        if(result!=null){
            r = r + result.toString();
        }
        return r;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public T getResult() {
        return result;
    }
}

package com.deepblue.middleware.web.rest.vm;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by enchen on 2017/7/3.
 */
@Setter
@Getter
@NoArgsConstructor
public class WebResult implements Serializable {

    private String resultCode;

    private String message;

    private Object data;

    public WebResult(String resultCode, String message, Object data) {
        this.resultCode = resultCode;
        this.message = message;
        this.data = data;
    }

    public static WebResult ok(String message, Object data) {
        return new WebResult(ResultCode.SUCCESS, message, data);
    }

    public static WebResult ok(Object data) {
        return new WebResult(ResultCode.SUCCESS, "SUCCESS", data);
    }

    public static WebResult ok() {
        return new WebResult(ResultCode.SUCCESS, "SUCCESS", null);
    }

}

package com.deepblue.middleware.exception;


import com.deepblue.middleware.config.Constants;

import java.util.Locale;

/**
 * Created by enchen on 5/14/17.
 */
public class SystemException extends RuntimeException {

    private String code;

    private String errorMessage;

    private Locale locale = Constants.DEFAULT_LOCALE;

    public SystemException() {

    }

    public SystemException(String code, String errorMessage) {
        super(errorMessage);
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public SystemException(String code, String errorMessage, Locale locale) {
        super(errorMessage);
        this.code = code;
        this.errorMessage = errorMessage;
        this.locale = locale;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public String toString() {
        return "SystemException{" +
                "code=" + code +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}

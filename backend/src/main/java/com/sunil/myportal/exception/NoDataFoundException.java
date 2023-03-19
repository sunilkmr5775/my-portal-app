package com.sunil.myportal.exception;

public class NoDataFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NoDataFoundException(String msg) {

        super(msg);
    }

    public NoDataFoundException(String msg, Throwable cause) {

        super(msg, cause);
    }

}
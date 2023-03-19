package com.sunil.myportal.exception;

public class InvalidDatabaseConnectionException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidDatabaseConnectionException(String msg) {

        super(msg);
    }

    public InvalidDatabaseConnectionException(String msg, Throwable cause) {

        super(msg, cause);
    }

}
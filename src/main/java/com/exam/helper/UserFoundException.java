package com.exam.helper;

public class UserFoundException extends Exception{
    public UserFoundException(String msg) {
        super(msg);
    }

    public UserFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

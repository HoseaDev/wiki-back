package com.hosea.wiki.exception;

public class ConditionalException extends RuntimeException {


    private int code;

    public ConditionalException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


}

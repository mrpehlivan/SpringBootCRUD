package com.mrpehlivan.SpringBootCRUD.exception;


public class MissingArgumentException extends BaseException {

    private final String status;
    private final String code;
    private final String message;

    public MissingArgumentException(String status, String code, String message) {
        super(message);
        this.status = status;
        this.code = code;
        this.message = message;
    }

    @Override
    public String status() {
        return status;
    }

    @Override
    public String errorCode() {
        return code;
    }

    @Override
    public String errorMessage() {
        return message;
    }
}

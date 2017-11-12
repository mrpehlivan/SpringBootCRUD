package com.mrpehlivan.SpringBootCRUD.exception;


public abstract class BaseException extends RuntimeException implements CRUDException {
    public BaseException(String message) {
        super(message);
    }

}

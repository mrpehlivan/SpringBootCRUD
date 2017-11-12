package com.mrpehlivan.SpringBootCRUD.exception;


public interface CRUDException {
    String status();

    String errorCode();

    String errorMessage();
}

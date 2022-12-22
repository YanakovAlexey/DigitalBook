package com.example.application.backEnd.service;

public class ResponseException extends Exception {
    public String error;
    public String message;
    public int code;

    public ResponseException(String error, String message, int code) {
        this.error = error;
        this.message = message;
        this.code = code;

    }

}

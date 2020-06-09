package com.larrykim.jpastudy.exception;

public class NotEnoughStockException extends RuntimeException{
    public NotEnoughStockException() {
    }

    public NotEnoughStockException(String message) {
        super(message);
    }
}
package ru.bookstore.exception;

public class InternalServerException extends RuntimeException {
    public InternalServerException() {
        super("Internal server exception!");
    }
}

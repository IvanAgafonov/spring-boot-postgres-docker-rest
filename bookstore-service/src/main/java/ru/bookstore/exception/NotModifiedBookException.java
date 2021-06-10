package ru.bookstore.exception;

public class NotModifiedBookException extends RuntimeException{
    public NotModifiedBookException() {super("Book not modified!");}
    public NotModifiedBookException(long id) {
        super(String.format("Book with ID: %d - not modified!", id));
    }
}

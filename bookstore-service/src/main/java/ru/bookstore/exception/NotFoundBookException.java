package ru.bookstore.exception;

public class NotFoundBookException extends RuntimeException{
    public NotFoundBookException() {super("Book not found!");}
    public NotFoundBookException(long id) {
        super(String.format("Book with ID: %d - not found!", id));
    }
}

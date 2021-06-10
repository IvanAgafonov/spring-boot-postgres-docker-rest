package ru.bookstore.exception;

public class ServiceUnavailableException extends RuntimeException{
    public ServiceUnavailableException() {
        super("Service unavailable!");
    }
    public ServiceUnavailableException(String serviceName) {
        super(String.format("Service %s unavailable!", serviceName));
    }
}

package ru.bookstore.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.bookstore.exception.InternalServerException;
import ru.bookstore.exception.NotFoundBookException;
import ru.bookstore.exception.NotModifiedBookException;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(InternalServerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleInternalServerException(HttpServletRequest request,
                                                                          InternalServerException ex
    ) {
        log.info("InternalServerException URL={}, message {}", request.getRequestURL(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundBookException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleNotFoundBookException(HttpServletRequest request,
                                                                 NotFoundBookException ex
    ) {
        log.info("NotFoundBookException URL={}, message {}", request.getRequestURL(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NotModifiedBookException.class)
    @ResponseStatus(HttpStatus.NOT_MODIFIED)
    public ResponseEntity<Object> handleNotModifiedBookException(HttpServletRequest request,
                                                                    NotModifiedBookException ex
    ) {
        log.info("NotModifiedBookException URL={}, message {}", request.getRequestURL(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(ex.getMessage());
    }
}

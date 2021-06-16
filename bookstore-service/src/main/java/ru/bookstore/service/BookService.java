package ru.bookstore.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import ru.bookstore.exception.InternalServerException;
import ru.bookstore.exception.NotFoundBookException;
import ru.bookstore.exception.NotModifiedBookException;
import ru.bookstore.model.Book;
import ru.bookstore.entity.BookEntity;
import ru.bookstore.repository.CustomizedBookCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final CustomizedBookCrudRepository bookRepository;

    public Book getBook(long id) {
        BookEntity book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            throw new NotFoundBookException(id);
        }
        return book.getBookJson();
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookEntity::getBookJson)
                .collect(Collectors.toList());
    }

    public URI addBookWithRandomGenre(Book book) {
        long idCreated;
        try {
            book.setGenre(Book.GenreEnum.values()[new Random().nextInt(Book.GenreEnum.values().length)]);
            idCreated = bookRepository.save(new BookEntity(0, book)).getId();
        } catch (IllegalArgumentException | NullPointerException e) {
            log.error(e.getMessage(), e);
            throw new InternalServerException();
        }
        return URI.create(String.format("/books/%d", idCreated));
    }

    public void editBook(long id, Book book) {
        getBook(id);
        try {
            bookRepository.save(new BookEntity(id, book));
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage(), e);
            throw new NotModifiedBookException();
        }
    }

    public void deleteBook(long id) {
        getBook(id);
        try {
            bookRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            bookRepository.delete(new BookEntity(id, null));
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage(), e);
            throw new NotModifiedBookException();
        }
    }
}

package org.openapitools.service;

import org.openapitools.model.Book;
import org.openapitools.model.BookEntity;
import org.openapitools.repository.CustomizedBookCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    CustomizedBookCrudRepository bookRepository;

    public Book getBook(long id) {
        BookEntity book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            return book.getBook();
        }
        return null;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookEntity::getBook)
                .collect(Collectors.toList());
    }

    public boolean addBookWithRandomGenre(Book book) {
        try {
            book.setGenre(Book.GenreEnum.values()[new Random().nextInt(Book.GenreEnum.values().length)]);
            bookRepository.save(new BookEntity(0, book));
        } catch (IllegalArgumentException exception) {
            return false;
        }
        return true;
    }

    public boolean editBook(long id, Book book) {
        try {
            bookRepository.save(new BookEntity(id, book));
        } catch (IllegalArgumentException exception) {
            return false;
        }
        return true;
    }

    public boolean deleteBook(long id) {
        try {
            bookRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            bookRepository.delete(new BookEntity(id, null));
        } catch (IllegalArgumentException exception) {
            return false;
        }
        return true;
    }
}

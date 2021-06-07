package ru.bookstore.model.repository.representation;

import ru.bookstore.model.entity.BookEntity;
import ru.bookstore.model.pojo.Book;

import java.util.List;

public interface BookRepository {
    List<Book> getAll();
    Book get(long id);
    boolean add(Book book);
    boolean edit(long id, Book book);
    boolean delete(long id);
}

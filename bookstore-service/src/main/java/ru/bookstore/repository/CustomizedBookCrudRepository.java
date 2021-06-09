package ru.bookstore.repository;

import ru.bookstore.model.BookEntity;
import org.springframework.data.repository.CrudRepository;

public interface CustomizedBookCrudRepository
        extends CrudRepository<BookEntity, Long>, CustomizedBook {}

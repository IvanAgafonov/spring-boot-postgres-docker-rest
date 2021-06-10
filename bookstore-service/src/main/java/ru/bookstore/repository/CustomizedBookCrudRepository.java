package ru.bookstore.repository;

import ru.bookstore.entity.BookEntity;
import org.springframework.data.repository.CrudRepository;

public interface CustomizedBookCrudRepository
        extends CrudRepository<BookEntity, Long>, CustomizedBook {}

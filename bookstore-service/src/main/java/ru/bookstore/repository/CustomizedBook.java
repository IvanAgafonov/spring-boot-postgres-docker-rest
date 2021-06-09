package ru.bookstore.repository;

import ru.bookstore.model.BookEntity;

import java.util.List;

public interface CustomizedBook {
    List<BookEntity> findAll();
}

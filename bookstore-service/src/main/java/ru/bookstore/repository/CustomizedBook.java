package ru.bookstore.repository;

import ru.bookstore.entity.BookEntity;

import java.util.List;

public interface CustomizedBook {
    List<BookEntity> findAll();
}

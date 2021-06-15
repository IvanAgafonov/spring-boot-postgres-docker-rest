package ru.bookstore.repository;

import ru.bookstore.entity.BookEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomizedBookCrudRepository extends CrudRepository<BookEntity, Long> {
    List<BookEntity> findAll();
}

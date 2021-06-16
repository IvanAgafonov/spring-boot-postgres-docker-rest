package ru.bookstore.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bookstore.entity.BookEntity;

import java.util.List;

public interface CustomizedBookCrudRepository extends CrudRepository<BookEntity, Long> {
    List<BookEntity> findAll();
}

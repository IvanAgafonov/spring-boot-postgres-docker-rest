package org.openapitools.repository;

import org.openapitools.model.BookEntity;

import java.util.List;

public interface CustomizedBook {
    List<BookEntity> findAll();
}

package org.openapitools.repository;

import org.openapitools.model.BookEntity;
import org.springframework.data.repository.CrudRepository;

public interface CustomizedBookCrudRepository extends CrudRepository<BookEntity, Long>, CustomizedBook {}

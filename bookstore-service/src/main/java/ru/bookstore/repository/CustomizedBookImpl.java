package ru.bookstore.repository;

import ru.bookstore.entity.BookEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CustomizedBookImpl implements  CustomizedBook {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<BookEntity> findAll() {
        return em.createQuery("select BookEntity from BookEntity BookEntity", BookEntity.class)
                .getResultList();
    }
}


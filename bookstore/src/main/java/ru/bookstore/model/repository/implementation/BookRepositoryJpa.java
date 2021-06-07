package ru.bookstore.model.repository.implementation;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.bookstore.model.entity.BookEntity;
import ru.bookstore.model.pojo.Book;
import ru.bookstore.model.repository.representation.BookRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Qualifier("BookRepositoryJpa")
@ConditionalOnProperty(prefix = "data", name = "access", havingValue = "jpa")
@Transactional
@Repository
public class BookRepositoryJpa implements BookRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private String SELECT_ALL_QUERY = "SELECT BookEntity from BookEntity BookEntity";

    @Override
    public List<Book> getAll() {
        return ((List<BookEntity>) entityManager.createQuery(SELECT_ALL_QUERY).getResultList())
                .stream().map(BookEntity::getBook).collect(Collectors.toList());
    }

    @Override
    public Book get(long id) {
        BookEntity book = entityManager.find(BookEntity.class, id);
        if (book == null) {
            return null;
        }
        else {
            return book.getBook();  // TODO Optional?
        }
    }

    public BookEntity getEntity(long id) {
        return entityManager.find(BookEntity.class, id);
    }

    @Override
    public boolean add(Book book) {
        entityManager.persist(new BookEntity(0, book));
        entityManager.flush();
        return true;
    }

    @Override
    public boolean edit(long id, Book book) {
        BookEntity bookEntity = getEntity(id);
        if (bookEntity == null) {
            return false;
        }
        bookEntity = entityManager.merge(bookEntity.setBook(book));
        return bookEntity != null;
    }

    @Override
    public boolean delete(long id) {
        BookEntity bookEntity = getEntity(id);
        if (bookEntity != null) {
            entityManager.remove(bookEntity);
            return true;
        }
        return false;
    }
}

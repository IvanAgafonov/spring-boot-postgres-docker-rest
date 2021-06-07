package ru.bookstore.model.repository.implementation;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import ru.bookstore.model.entity.BookEntity;
import ru.bookstore.model.pojo.Book;
import ru.bookstore.model.repository.representation.BookRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Qualifier("BookRepositoryStub")
@ConditionalOnProperty(prefix = "data", name = "access", havingValue = "stub")
@Repository
public class BookRepositoryStub implements BookRepository {
    private List<BookEntity> books;
    private AtomicInteger lastId = new AtomicInteger();

    public BookRepositoryStub() {
        books = Collections.synchronizedList(new ArrayList<>());

        books.add(new BookEntity(1, new Book("Java learn", 230,
                BigDecimal.valueOf(300.0), LocalDate.now())));
        books.add(new BookEntity(2, new Book("Lisp learn", 340,
                BigDecimal.valueOf(200.2), LocalDate.now().minusYears(3))));
        books.add(new BookEntity(3, new Book("Html learn", 120,
                BigDecimal.valueOf(343.23), LocalDate.now().minusYears(2))));
        books.add(new BookEntity(4, new Book("Kotlin learn", 400,
                BigDecimal.valueOf(120.232323), LocalDate.now().minusDays(321))));
        lastId.set(4);
    }

    @Override
    public List<Book> getAll() {
        return books.stream().map(BookEntity::getBook).collect(Collectors.toList());
    }

    @Override
    public Book get(long id) {
        return books.stream().filter(x -> x.getId() == id).map(BookEntity::getBook)
                .findFirst().orElse(null);
    }

    @Override
    public boolean add(Book book) {
        return books.add(new BookEntity(lastId.incrementAndGet(), book));
    }

    @Override
    public boolean edit(long id, Book book) {
        int index = books.indexOf(new BookEntity(id, book));
        if (index == -1) {
            return false;
        }
        return books.set(index, new BookEntity(id, book)) != null;
    }

    @Override
    public boolean delete(long id) {
        BookEntity book = books.stream().filter(x -> x.getId() == id)
                .findFirst().orElse(null);
        if (book == null) {
            return false;
        }
        return books.remove(book);
    }
}

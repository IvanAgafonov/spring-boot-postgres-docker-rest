package ru.bookstore.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import ru.bookstore.model.Book;

import javax.persistence.*;
import java.util.Objects;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

@Entity
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Table(name="Books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Embedded
    private Book book;

    @Column(columnDefinition = "jsonb")
    @Type(type = "jsonb")
    private Book bookJson;

    public BookEntity() {}

    public BookEntity(long id, Book book) {
        this.id = id;
        this.book = book;
        this.bookJson = book;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public BookEntity setBook(Book book) {
        this.book = book;
        this.bookJson = book;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity that = (BookEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}


package ru.bookstore.model.pojo;

import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class Book {
    private String name;
    private int pages;
    private BigDecimal price;
    private LocalDate publishDate;

    public Book() {}

    public Book(String name, int pages, BigDecimal price, LocalDate publishYear) {
        this.name = name;
        this.pages = pages;
        this.price = price;
        this.publishDate = publishYear;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getPages() {
        return pages;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return pages == book.pages && Objects.equals(name, book.name) && Objects.equals(price, book.price) && Objects.equals(publishDate, book.publishDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, pages, price, publishDate);
    }
}

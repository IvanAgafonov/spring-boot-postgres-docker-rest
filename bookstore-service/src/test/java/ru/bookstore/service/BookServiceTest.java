package ru.bookstore.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.bookstore.entity.BookEntity;
import ru.bookstore.model.Book;
import ru.bookstore.repository.CustomizedBookCrudRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {
    private static final long BOOK_ID = 10;

    @Mock
    private CustomizedBookCrudRepository repository;

    @InjectMocks
    private BookService bookService;

    private final BookEntity bookEntity = new BookEntity();


    @Test
    public void testGetAllBooks() {
        when(repository.findAll()).thenReturn(List.of(new BookEntity(), new BookEntity()));
        assertEquals(2, bookService.getAllBooks().size());
    }

    @Test
    public void testGetBook() {
        when(repository.findById(BOOK_ID)).thenReturn(Optional.of(bookEntity));
        assertEquals(bookEntity.getBookJson(), bookService.getBook(BOOK_ID));
    }

    @Test
    public void testAddBookWithRandomGenre() {
        when(repository.save(any())).thenReturn(new BookEntity(BOOK_ID, new Book()));
        bookService.addBookWithRandomGenre(new Book());
        verify(repository, times(1)).save(any());
    }

    @Test
    public void testEditBook() {
        when(repository.findById(BOOK_ID)).thenReturn(Optional.of(bookEntity));
        bookService.editBook(BOOK_ID, new Book());
        verify(repository, times(1)).save(any());
    }

    @Test
    public void testEditBookFail() {
        try {
            bookService.editBook(BOOK_ID, new Book());
        }
        catch (Exception e) {}
        verify(repository, times(1)).findById(any());
        verify(repository, times(0)).save(any());
    }

    @Test
    public void testDeleteBook() {
        when(repository.findById(BOOK_ID)).thenReturn(Optional.of(bookEntity));
        bookService.deleteBook(BOOK_ID);
        verify(repository, times(1)).delete(any());
    }
}

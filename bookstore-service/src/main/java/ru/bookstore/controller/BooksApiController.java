package ru.bookstore.controller;

import lombok.RequiredArgsConstructor;
import ru.bookstore.api.BooksApi;
import ru.bookstore.model.Book;
import ru.bookstore.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;
import java.util.List;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-06-08T15:10:05.181404+03:00[Europe/Moscow]")
@Controller
@RequiredArgsConstructor
@RequestMapping("${openapi.bookstore.base-path:}")
public class BooksApiController implements BooksApi {

    private final BookService bookService;

    @Override
    public ResponseEntity<Void> deleteBooksId(Integer id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @Override
    public ResponseEntity<Book> getBooksId(Integer id) {
        return ResponseEntity.ok(bookService.getBook(id));
    }

    @Override
    public ResponseEntity<Void> postBooks(Book book) {
        URI location = bookService.addBookWithRandomGenre(book);
        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<Void> putBooksId(Integer id, Book book) {
        bookService.editBook(id, book);
        return ResponseEntity.ok().build();
    }
}

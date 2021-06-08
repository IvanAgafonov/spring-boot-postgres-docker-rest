package org.openapitools.api;

import org.openapitools.model.Book;
import org.openapitools.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-06-08T15:10:05.181404+03:00[Europe/Moscow]")
@Controller
@RequestMapping("${openapi.bookstore.base-path:}")
public class BooksApiController implements BooksApi {

    private final NativeWebRequest request;

    @Autowired
    BookService bookService;

    @org.springframework.beans.factory.annotation.Autowired
    public BooksApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> deleteBooksId(Integer id) {
        if (bookService.getBook(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return bookService.deleteBook(id)
                ? ResponseEntity.status(HttpStatus.OK).body(null)
                : ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(null);
    }

    @Override
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks());
    }

    @Override
    public ResponseEntity<Book> getBooksId(Integer id) {
        Book book = bookService.getBook(id);
        return book != null
                ? ResponseEntity.status(HttpStatus.OK).body(book)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @Override
    public ResponseEntity<Void> postBooks(Book book) {
        return bookService.addBookWithRandomGenre(book)
                ? ResponseEntity.status(HttpStatus.CREATED).body(null)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @Override
    public ResponseEntity<Void> putBooksId(Integer id, Book book) {
        if (bookService.getBook(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return bookService.editBook(id, book)
                ? ResponseEntity.status(HttpStatus.OK).body(null)
                : ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(null);

    }
}

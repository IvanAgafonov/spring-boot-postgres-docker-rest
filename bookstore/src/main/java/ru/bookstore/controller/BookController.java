package ru.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bookstore.model.pojo.Book;
import ru.bookstore.model.repository.representation.BookRepository;

import java.util.List;

@RestController
@RequestMapping("/books")
class BookController {

    @Qualifier("BookRepositoryJpa")
    @Autowired
    BookRepository bookRepository;

    @GetMapping
    public List<Book> getBooks() {
        return bookRepository.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable long id) {
        Book book = bookRepository.get(id);
        return book != null
                ? ResponseEntity.status(HttpStatus.OK).body(book)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping
    public ResponseEntity<Object> postBook(@RequestBody Book book) {
        return bookRepository.add(book)
            ? ResponseEntity.status(HttpStatus.CREATED).body(null)
            : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable long id, @RequestBody Book book) {
        return  bookRepository.edit(id, book)
                ? ResponseEntity.status(HttpStatus.OK).body(null)
                : ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable long id) {
        return  bookRepository.delete(id)
                ? ResponseEntity.status(HttpStatus.OK).body(null)
                : ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(null);
    }
}

package ru.bookstore.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.bookstore.entity.BookEntity;
import ru.bookstore.exception.NotFoundBookException;
import ru.bookstore.model.Book;
import ru.bookstore.repository.CustomizedBookCrudRepository;
import ru.bookstore.service.BookService;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BooksApiController.class)
@AutoConfigureWebClient
public class BooksApiControllerTest {

    public static final String URL_BOOKS = "/books";
    public static final String URL_BOOK = "/books/{id}";
    private static final String BOOK_ID = "1";

    @MockBean
    private BookService bookService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetBooks() throws Exception {
        //when
        when(bookService.getAllBooks()).thenReturn(new ArrayList<>());
        //then
        this.mockMvc.perform(get(URL_BOOKS).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetBooksIdNotFound() throws Exception {
        when(bookService.getBook(2L)).thenThrow(new NotFoundBookException(2L));
        this.mockMvc.perform(get(URL_BOOK, "2").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetBooksId() throws Exception {
        //when
        when(bookService.getBook(anyInt())).thenReturn(new Book());
        //then
        this.mockMvc.perform(get(URL_BOOK, BOOK_ID).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteBooksId() throws Exception {
        //when
        when(bookService.getBook(anyInt())).thenReturn(new Book());
        //then
        this.mockMvc.perform(delete(URL_BOOK, BOOK_ID).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testPostBooks() throws Exception {
        this.mockMvc.perform(post(URL_BOOKS).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void testPutBooksId() throws Exception {
        //when
        when(bookService.getBook(anyInt())).thenReturn(new Book());
        //then
        this.mockMvc.perform(put(URL_BOOK, BOOK_ID).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}

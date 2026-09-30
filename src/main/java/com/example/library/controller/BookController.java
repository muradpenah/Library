package com.example.library.controller;

import com.example.library.dto.BookRequestDto;
import com.example.library.dto.BookResponseDto;
import com.example.library.service.BookService;
import com.example.library.service.impl.BookServiceImpl;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@Controller
public class BookController {
    private final BookServiceImpl bookService;
    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @MutationMapping(name = "createBook")
    public BookResponseDto  post(@Argument BookRequestDto bookRequestDto) {
        return bookService.createBook(bookRequestDto);

    }

    @QueryMapping(name = "kitabIdUchun")
    public BookResponseDto getById(@Argument Long id) {
        return bookService.getBookById(id);
    }

    @QueryMapping(name = "books")
    @GetMapping("/getAll")
    public List<BookResponseDto> getAll() {
     return   bookService.getAllBooks();
    }

    @MutationMapping(name = "updateBook")
    public BookResponseDto update(@Argument Long id, @Argument BookRequestDto bookRequestDto) {
        return bookService.updateBook(id, bookRequestDto);
    }

    @MutationMapping(name = "deleteBook")
    public void delete(@Argument Long id) {
        bookService.deleteBook(id);

    }
}

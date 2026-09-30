package com.example.library.service;

import com.example.library.dto.BookRequestDto;
import com.example.library.dto.BookResponseDto;

import java.util.List;

public interface BookService {

     BookResponseDto createBook(BookRequestDto bookRequestDto);
     BookResponseDto getBookById(Long id);
     List<BookResponseDto> getAllBooks();
     BookResponseDto updateBook(Long id, BookRequestDto bookRequestDto);
     void deleteBook(Long id);

}

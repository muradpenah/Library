package com.example.library.service.impl;

import com.example.library.dto.BookRequestDto;
import com.example.library.dto.BookResponseDto;
import com.example.library.entity.Book;
import com.example.library.repo.BookRepo;
import com.example.library.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;
    public BookServiceImpl(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public BookResponseDto createBook(BookRequestDto bookRequestDto) {
        Book book = new Book();
         book.setTitle(bookRequestDto.getTitle());
         book.setAuthor(bookRequestDto.getAuthor());
         book.setPrice(bookRequestDto.getPrice());

        Book savedBook = bookRepo.save(book);

        BookResponseDto bookResponseDto = new BookResponseDto();

        bookResponseDto.setId(savedBook.getId());
        bookResponseDto.setTitle(savedBook.getTitle());
        bookResponseDto.setAuthor(savedBook.getAuthor());
        bookResponseDto.setPrice(savedBook.getPrice());

        return bookResponseDto;

    }

    @Override
    public BookResponseDto getBookById(Long id) {
        Book book = bookRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Book not found")
        );
        BookResponseDto bookResponseDto = new BookResponseDto();
        bookResponseDto.setId(book.getId());
        bookResponseDto.setTitle(book.getTitle());
        bookResponseDto.setAuthor(book.getAuthor());
        bookResponseDto.setPrice(book.getPrice());
        return bookResponseDto;
    }

    @Override
    public List<BookResponseDto> getAllBooks() {
        return bookRepo.findAll().stream().map(
                b -> {
                    BookResponseDto bookResponseDto = new BookResponseDto();

                    bookResponseDto.setId(b.getId());
                    bookResponseDto.setTitle(b.getTitle());
                    bookResponseDto.setAuthor(b.getAuthor());
                    bookResponseDto.setPrice(b.getPrice());

                    return bookResponseDto;
                }
        ).toList();
    }

    @Override
    public BookResponseDto updateBook(Long id, BookRequestDto bookRequestDto) {
        Book book = bookRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Book not found")
        );
        book.setTitle(bookRequestDto.getTitle());
        book.setAuthor(bookRequestDto.getAuthor());
        book.setPrice(bookRequestDto.getPrice());
        Book savedBook = bookRepo.save(book);
        BookResponseDto bookResponseDto = new BookResponseDto();
        bookResponseDto.setId(savedBook.getId());
        bookResponseDto.setTitle(savedBook.getTitle());
        bookResponseDto.setAuthor(savedBook.getAuthor());
        bookResponseDto.setPrice(savedBook.getPrice());
        return bookResponseDto;
    }

    @Override
    public void deleteBook(Long id) {
        if(!bookRepo.existsById(id)){
            throw new RuntimeException("Book not found");
        }

        bookRepo.deleteById(id);
    }
}

package com.public_library.services.impl;

import com.public_library.dto.BookDTO;
import com.public_library.exception.BadRequestException;
import com.public_library.exception.EntityNotFoundException;
import com.public_library.model.Book;
import com.public_library.repository.BookRepository;
import com.public_library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class BookServiceImplementation implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImplementation(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookDTO> findAllBooks() {
        return convertToDTOs(bookRepository.findAll());
    }

    @Override
    public BookDTO createBook(BookDTO book) {
        Book bookToSave = convertToEntity(book);
        return convertToDTO(bookRepository.save(bookToSave));
    }

    @Override
    public BookDTO updateBook(BookDTO book) {
        Book bookToSave = convertToEntity(book);
        bookRepository.findById(bookToSave.getId()).orElseThrow(() -> new BadRequestException("A book with the specified title does not exist | title = " + bookToSave.getTitle()));
        return convertToDTO(bookRepository.save(bookToSave));
    }

    @Override
    public BookDTO findBookByTitle(String title) {
        Book book = bookRepository.findByTitle(title).orElseThrow(() -> new EntityNotFoundException("A book with the specified TITLE does not exist | title = " + title));
        return convertToDTO(book);
    }

    @Override
    public void deleteBook(String title) {
        Book book = bookRepository.findByTitle(title).orElseThrow(() -> new BadRequestException("A book with the specified TITLE does not exist | title = " + title));
        bookRepository.delete(book);
    }

    private Book convertToEntity(BookDTO dto) {
        Book book = new Book();
        book.setTitle(dto.title());
        book.setAuthor(dto.author());
        book.setGenre(dto.genre());

        return book;
    }

    public List<BookDTO> convertToDTOs(List<Book> books) {
        if (CollectionUtils.isEmpty(books)) return Collections.emptyList();

        List<BookDTO> result = new ArrayList<>();
        for (Book book : books) {
            result.add(convertToDTO(book));
        }
        return result;
    }

    public BookDTO convertToDTO(Book book) {
        return new BookDTO (
                book.getTitle(),
                book.getAuthor(),
                book.getGenre());
    }
}

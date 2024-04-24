package com.public_library.services;

import com.public_library.dto.BookDTO;
import com.public_library.model.Book;

import java.util.List;

public interface BookService {

    BookDTO createBook(BookDTO book);

    BookDTO updateBook(BookDTO book);

    void deleteBook(String title);

    List<BookDTO> findAllBooks();

    BookDTO findBookByTitle(String title);

}

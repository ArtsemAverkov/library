package com.example.library.services.books;

import com.example.library.dto.books.BooksDto;
import com.example.library.entities.books.Books;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface BooksService {
    long create(BooksDto booksDto);
    Books read (long id);
    boolean update (BooksDto booksDto, Long id);
    boolean delete (Long id);
    List<Books> readAll (Pageable pageable);


}

package com.example.library.services.books;

import com.example.library.dto.books.BooksDto;
import com.example.library.entities.books.Books;
import com.example.library.entities.books.BooksAuthor;
import com.example.library.entities.books.BooksGenre;
import com.example.library.repositories.avatarBooks.AvatarBooksRepository;
import com.example.library.repositories.books.BooksRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor

public class BooksApiService implements BooksService{
    private final BooksRepository booksRepository;
    private final AvatarBooksRepository avatarBooksRepository;


    @Override
    public long create(BooksDto booksDto) {
        Books books = buildBooks(booksDto);
        return booksRepository.save(books).getId();
    }

    @Override
    public Books read(long id) {
        return booksRepository.findById(id).get();
    }

    @Override
    @Transactional
    public boolean update(BooksDto booksDto, Long id) {
        Books read = read(id);
        if (Objects.nonNull(read)){
            Books buildBooks = buildBooks(booksDto);
            buildBooks.setId(id);
            booksRepository.save(buildBooks);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        Books read = read(id);
        if (Objects.nonNull(read)){
            booksRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Books> readAll(Pageable pageable) {
        return booksRepository.findAll(pageable).toList();
    }

    private Books buildBooks(BooksDto booksDto){
        return Books.builder()
                .name(booksDto.getName())
                .booksAuthors(new BooksAuthor(booksDto.getAuthorBooks()))
                .booksGenres(new BooksGenre(booksDto.getGenresBooks()))
                .build();
    }
}

package com.example.library.common.controllers;


import com.example.library.common.extensions.user.InvalidParameterResolverBooks;
import com.example.library.common.extensions.user.ValidParameterResolverBooks;
import com.example.library.dto.books.BooksDto;
import com.example.library.entities.books.Books;
import com.example.library.entities.books.BooksAuthor;
import com.example.library.entities.books.BooksGenre;
import com.example.library.repositories.books.BooksRepository;
import com.example.library.services.books.BooksApiService;
import com.example.library.services.books.BooksService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Pageable;


import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Slf4j
@DisplayName("Testing Books")

public class BooksServiceImplTest {
    @Nested
    @ExtendWith(MockitoExtension.class)
    @ExtendWith(ValidParameterResolverBooks.class)

    public class ValidData {

        @Mock
        private BooksService booksService;

        @Mock
        private BooksRepository booksRepository;

        @RepeatedTest(1)
        void shouldGetBooksWhenBooksIsValid(BooksDto books) {
            Books buildBooks = buildBooks(books);
            Mockito.when(booksRepository.findById(books.getId())).thenReturn(Optional.of(buildBooks));
            Assertions.assertEquals(Optional.of(books),
                    (booksService.read(books.getId())));
        }

        @RepeatedTest(1)
        void shouldDeleteWhenBooksIsValid(BooksDto booksDto) {
            Books buildBooks = buildBooks(booksDto);
            Mockito.when(booksRepository.findById(buildBooks.getId())).thenReturn(Optional.of(buildBooks));
            Mockito.verify(booksRepository, Mockito.timeout(1)).deleteById(buildBooks.getId());
        }

        @RepeatedTest(1)
        void shouldUpdateBooksWhenBooksIsValid(BooksDto booksDto){
            Books buildBooks = buildBooks(booksDto);
            Mockito.when(booksRepository.findById(buildBooks.getId())).thenReturn(Optional.ofNullable(buildBooks));
            Assertions.assertEquals(Objects.nonNull(buildBooks), booksService.update(booksDto, buildBooks.getId()));
            Mockito.verify(booksRepository, Mockito.times(1)).save(buildBooks);
        }

        @RepeatedTest(1)
        void shouldCreateBooksWhenBooksIsValid(BooksDto booksDto){
            Books buildBooks = buildBooks(booksDto);
            Mockito.when(booksRepository.save(buildBooks)).thenReturn(buildBooks);
            Assertions.assertEquals(buildBooks.getId(), booksService.create(booksDto));
            Mockito.verify(booksRepository, Mockito.times(1)).save(buildBooks);
        }

        private Books buildBooks(BooksDto booksDto){
            return Books.builder()
                    .id(booksDto.getId())
                    .name(booksDto.getName())
                    .booksAuthors(new BooksAuthor(booksDto.getAuthorBooks()))
                    .booksGenres(new BooksGenre(booksDto.getGenresBooks()))
                    .build();
        }
    }


    @Nested
    @ExtendWith(MockitoExtension.class)
    @ExtendWith(InvalidParameterResolverBooks.class)
    public class InvalidData{
        @Mock
        private BooksService booksService;
        @RepeatedTest(1)
        void shouldUpdateBooksWheBooksIsInvalid (BooksDto booksDto){
            Assertions.assertThrows(EntityNotFoundException.class,
                    () -> booksService.update(booksDto, booksDto.getId()));
        }

        @RepeatedTest(1)
        void shouldCreateBooksWheBooksIsInvalid (BooksDto booksDto){
            Assertions.assertThrows(EntityNotFoundException.class,
                    () -> booksService.create(booksDto));

        }
        @RepeatedTest(1)
        void shouldDeleteBooksWheBooksIsInvalid (BooksDto booksDto){
            Assertions.assertThrows(EntityNotFoundException.class,
                    () -> booksService.delete(booksDto.getId()));

        }
        @RepeatedTest(1)
        void shouldGetBooksWheBooksIsInvalid (BooksDto booksDto){
            Assertions.assertThrows(EntityNotFoundException.class,
                    () -> booksService.read(booksDto.getId()));
        }
    }
}

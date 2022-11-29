package com.example.library.repositories.books;

import com.example.library.entities.books.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BooksRepository extends JpaRepository<Books, Long> {
    @Query(value = "select count(name) from books where name =:name", nativeQuery = true)
    int existActiveBooksName(String name);

}

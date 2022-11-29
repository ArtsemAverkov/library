package com.example.library.controllers.books;

import com.example.library.dto.books.BooksDto;
import com.example.library.entities.books.Books;
import com.example.library.services.books.BooksService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BooksController {
    public final BooksService booksService;

    /**
     * this method creates a new product
     * @param books get from server
     * @return the long id of the created book
     */

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Long createBooks(@RequestBody @Valid BooksDto books){
        return booksService.create(books);
    }

    /**
     * this method searches the books database
     * @param id get from server
     * @return book
     */

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Books readBooks(@PathVariable Long id){
        return booksService.read(id);
    }

    /**
     * this method updates books by id
     * @param booksDto get from server
     * @param id get from server
     * @returт successful and unsuccessful update
     */

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE, path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean updateBooks(@PathVariable Long id, @RequestBody @Valid BooksDto booksDto){
        return booksService.update(booksDto, id);
    }

    /**
     * this method removes the book from the database
     * @param id get from server
     * @returт successful and unsuccessful delete
     */

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteBooks(@PathVariable Long id){
        return booksService.delete(id);
    }

    /**
     * this method returns a collection of all books in the database
     * @return collection of all books
     */

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Books> readBooks(@PageableDefault(page = 0)
                                 @SortDefault(sort = "name")Pageable pageable){
        return booksService.readAll(pageable);
    }
}

package com.example.library.common.controllers;

import com.example.library.controllers.books.BooksController;
import com.example.library.dto.books.BooksDto;
import com.example.library.entities.books.Books;
import com.example.library.entities.books.BooksAuthor;
import com.example.library.entities.books.BooksGenre;
import com.example.library.services.books.BooksService;

import com.google.gson.Gson;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.org.hamcrest.Matchers;


import java.util.Random;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BooksController.class)
@RunWith(SpringRunner.class)
public class BooksControllerTest {
@Autowired
   private  MockMvc mockMvc;
    @MockBean
   private  BooksService booksService;
    @Autowired
   private  Gson gson;

    @Test
    void  getBooks() throws Exception {
        Random random = new Random();
        long id = random.nextLong();
        BooksDto booksDto = new BooksDto();
        booksDto.setId(id);
        booksDto.setName("Sun");
        booksDto.setAuthorBooks("Gogol");
        booksDto.setGenresBooks("Fan");
        Books books = buildBooks(booksDto);
        Mockito.when(booksService.read(id)).thenReturn(books);

        mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", id)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher)MockMvcResultMatchers.
                        jsonPath("$.id", Matchers.is(books.getId().toString())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void createBooks() throws Exception {
        BooksDto booksDto = new BooksDto();
        booksDto.setId(1L);
        booksDto.setName("Sun");
        booksDto.setAuthorBooks("Gogol");
        booksDto.setGenresBooks("Fan");
        Books books = buildBooks(booksDto);
        Mockito.when(booksService.create(booksDto)).thenReturn(books.getId());

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher)MockMvcResultMatchers.jsonPath("$.id",
                        Matchers.is(booksDto.getId()).toString()))
                .andExpect((ResultMatcher)MockMvcResultMatchers.jsonPath("$.name",
                        Matchers.is(booksDto.getName())))
                .andExpect((ResultMatcher)MockMvcResultMatchers.jsonPath("$.authorBooks",
                Matchers.is(booksDto.getAuthorBooks())))
                .andExpect((ResultMatcher)MockMvcResultMatchers.jsonPath("$.genresBooks",
                        Matchers.is(booksDto.getGenresBooks())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void deleteBooks() throws Exception {
        Random random = new Random();
        long id = random.nextLong();
        BooksDto booksDto = new BooksDto();
        booksDto.setId(id);
        booksDto.setName("Sun");
        booksDto.setAuthorBooks("Gogol");
        booksDto.setGenresBooks("Fan");
        Books books = buildBooks(booksDto);
        Mockito.when(booksService.delete(id)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/user/{id}", id)
                .header(String.valueOf(HttpStatus.OK)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    void updateBooks() throws Exception {
        Random random = new Random();
        long id = random.nextLong();
        BooksDto booksDto = new BooksDto();
        booksDto.setId(1L);
        booksDto.setName("Sun");
        booksDto.setAuthorBooks("Gogol");
        booksDto.setGenresBooks("Fan");
        Books books = buildBooks(booksDto);
        Mockito.when(booksService.update(booksDto, id)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher)MockMvcResultMatchers.jsonPath("$.id",
                        Matchers.is(booksDto.getId()).toString()))
                .andExpect((ResultMatcher)MockMvcResultMatchers.jsonPath("$.name",
                        Matchers.is(booksDto.getName())))
                .andExpect((ResultMatcher)MockMvcResultMatchers.jsonPath("$.authorBooks",
                        Matchers.is(booksDto.getAuthorBooks())))
                .andExpect((ResultMatcher)MockMvcResultMatchers.jsonPath("$.genresBooks",
                        Matchers.is(booksDto.getGenresBooks())))
                .andDo(MockMvcResultHandlers.print());
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

package com.example.library.dto.books;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BooksDto {

    private long id;
    @NotBlank
    private String name;
    @NotBlank
    private String authorBooks;
    @NotBlank
    private String genresBooks;

    private LocalDate dateInserting = LocalDate.now();

}
